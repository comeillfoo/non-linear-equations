package nonlinear_root;

import nonlinear_root.equations.NonLinearEquations;
import nonlinear_root.resolvers.*;
import nonlinear_root.resolvers.latex.LogResolver;
import nonlinear_root.resolvers.log.*;
import nonlinear_root.resolvers.log.chords.LogBothChordResolver;
import nonlinear_root.resolvers.log.chords.LogLeftChordResolver;
import nonlinear_root.resolvers.log.chords.LogRightChordResolver;
import nonlinear_root.utilities.NonLinearShell;
import nonlinear_root.validators.ErrorValidator;
import nonlinear_root.validators.LimitsValidator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;
import java.util.function.Function;

public class NonLinearEquationResolver {

  public static void main( String[] args ) {
    // считываем решаемое уравнение
    final NonLinearEquations eq = NonLinearShell.readEquation( );
    NonLinearShell.stdOutPrintLn( "Решаем уравнение: " + eq );
    final Function<Double, Double> fun = eq.FUN;
    final Function<Double, Double> dfun = eq.DERIVATE_OF_FUN;
    final Function<Double, Double> ddfun = eq.DERIVATE_OF_DERIVATE_OF_FUN;

    // считываем метод решения уравнения
    final NonLinearEquationSolutionMethods method = NonLinearShell.readMethod( );

    // считываем потоки ввода/вывода
    final InputStream streamIn = NonLinearShell.readInStream( );
    final OutputStream streamOut = NonLinearShell.readOutStream( );

    try ( NonLinearShell sh = new NonLinearShell( streamIn, streamOut, System.err ) ) {
      // забираем параметры решения из потока ввода
      Double[] params = sh.readCustomLimitsAndError( );
      double a = params[ 0 ];
      double b = params[ 1 ];
      double eps = params[ 2 ];

      // валидируем параметры
      final LimitsValidator limits = new LimitsValidator( a, b );
      if ( Math.abs( a - limits.getLeftLimit() ) >= Double.MIN_VALUE ) {
        a = limits.getLeftLimit();
        NonLinearShell.stdOutPrintLn( "Обнаружен оптимизируемый параметр a. Автоматически заменен на " + a );
      }
      if ( Math.abs( b - limits.getRightLimit() ) >= Double.MIN_VALUE ) {
        b = limits.getRightLimit();
        NonLinearShell.stdOutPrintLn( "Обнаружен оптимизируемый параметр b. Автоматически заменен на " + b );
      }

      final ErrorValidator error = new ErrorValidator( eps );
      if ( Math.abs( eps - error.getError() ) >= Double.MIN_VALUE ) {
        eps = error.getError();
        NonLinearShell.stdOutPrintLn( "Обнаружен оптимизируемый параметр eps. Автоматически заменен на " + eps );
      }

      // определяем метод решения
      Resolver solver;
      switch ( method ) {
        case NLESM_BOTH_CHORDS: solver = new LogBothChordResolver( a, b, eps, fun, dfun, ddfun ); break;
        case NLESM_LEFT_CHORDS: solver = new LogLeftChordResolver( a, b, eps, fun, dfun, ddfun ); break;
        case NLESM_RIGHT_CHORDS: solver = new LogRightChordResolver( a, b, eps, fun, dfun, ddfun ); break;
        case NLESM_NEWTON: solver = new LogNewtonResolver( a, b, eps, fun, dfun, ddfun );  break;
        case NLESM_SIMPLE_ITERATIONS: solver = new LogSimpleIterationsResolver( a, b, eps, fun, dfun ); break;
        default:
          throw new IllegalStateException( "Неожиданное значение: " + method );
      }

      // решаем и выводим результат в поток вывода
      double answer = solver.solve();
      sh.println( ( ( LogResolver ) solver ).getLog() );
      sh.println( String.format( "%s = ( %f ± %f ) [ k = %d ]", eq, answer, eps, solver.getIterations() ) );
      // выведем данные для визуализации в файл
      try ( NonLinearShell graphShell = new NonLinearShell( System.in, new FileOutputStream( "answer.out" ), System.err ) ) {
        graphShell.print( String.format( Locale.ROOT, "%d %d %f %f %f\n", eq.IDX, method.IDX, answer, a, b ) );
      }
    } catch ( IOException | ClassCastException | IllegalStateException e ) {
      System.err.println( "Произошла непредвиденная ошибка: " + e.getMessage( ) );
      System.exit( 1 );
    }
  }
}
