package nonlinear_root.utilities;

import nonlinear_root.equations.NonLinearEquations;
import nonlinear_root.resolvers.NonLinearEquationSolutionMethods;
import utilities.Shell;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.Optional;

public final class NonLinearShell extends Shell {
  public NonLinearShell( InputStream in, OutputStream out, PrintStream err ) {
    super( in, out, err );
  }

  public static NonLinearEquations readEquation() {
    Integer equation = readMenu("Выберите нелинейное уравнения для поиска корней:", NonLinearEquations.equations() );
    return NonLinearEquations.values()[ equation ];
  }

  public static String readFilePath() {
    Optional<String> filePath = readLine();
    return filePath.get();
  }

  public static InputStream readInStream() {
    Integer strNum = readMenu("Выберите поток данных ввода:", "Стандартный поток ввода ( stdin )", "Пользовательский файл" );
    if ( strNum == 0 )
      return System.in;
    else {
      String path = null;
      FileInputStream fin = null;
      try {
        while ( fin == null ) {
          stdOutPrintLn( "Введите путь до файла:" );
          path = readFilePath();
          fin = new FileInputStream( path );
        }
      } catch ( FileNotFoundException e ) {
        stdErrPrintLn( String.format( "Файл по пути [ %s ] не найден", path ) );
      }
      return fin;
    }
  }

  public static OutputStream readOutStream() {
    Integer strNum = readMenu("Выберите поток данных вывода:", "Стандартный поток вывода ( stdout )", "Пользовательский файл" );
    if ( strNum == 0 )
      return System.out;
    else {
      String path = null;
      FileOutputStream fout = null;
      try {
        while ( fout == null ) {
          stdOutPrintLn( "Введите путь до файла:" );
          path = readFilePath();
          fout = new FileOutputStream( path );
        }
      } catch ( FileNotFoundException e ) {
        stdErrPrintLn( String.format( "Файл по пути [ %s ] не найден", path ) );
      }
      return fout;
    }
  }

  public static NonLinearEquationSolutionMethods readMethod() {
    Integer method = readMenu("Выберите метод решения нелинейного уравнения:", NonLinearEquationSolutionMethods.methods() );
    return NonLinearEquationSolutionMethods.values()[ method ];
  }

  public Double[] readCustomDoubles( String header, String... parameters ) {
    Double[] doubles = null;
    int number = parameters.length;
    try {
      String line;
      while ( doubles == null ) {
        stdOutPrint( header + "[ " );
        for ( String param : parameters )
          stdOutPrint( param + ", " );
        stdOutPrint( "]: " );
        line = ctmin.nextLine( );
        if ( !line.isEmpty( ) ) {
          String[] numbers = line.split( " " );
          if ( numbers.length >= number ) {
            Double[] maybe = new Double[ number ];
            for ( int i = 0; i < number; ++i ) {
              try {
                maybe[ i ] = Double.parseDouble( numbers[ i ] );
              } catch ( NumberFormatException e ) {
                stdOutPrintLn( "Формат не соответствует заданному типу" );
                maybe = null;
                break;
              }
            }
            doubles = maybe;
          }
        }
      }
      return doubles;
    } catch ( NoSuchElementException e ) {
      stdErrPrintLn( "Поток данных поврежден." );
      stdOutPrintLn( "Осуществляем выход." );
      System.exit( 1 );
      return new Double[ 0 ];
    }
  }

  public Double[] readCustomLimitsAndError() {
    Double[] parameters = readCustomDoubles( "Введите параметры", "a", "b", "ε" );
    return parameters;
  }
}
