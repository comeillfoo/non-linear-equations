package nonlinear_root.refiners.log.chords;

import nonlinear_root.deviations.AbsoluteFunDeviator;
import nonlinear_root.refiners.LogRefiner;
import nonlinear_root.refiners.just.chords.RightChordRefiner;

import java.util.function.Function;

public final class LogRightChordRefiner extends RightChordRefiner implements LogRefiner {
  private final StringBuilder log = new StringBuilder( "" );

  public LogRightChordRefiner( double a, double b, Function<Double, Double> fun ) {
    super( a, b, fun );
    log.append( "Уточнение корня уравнения методом половинного деления ( хорд )\n" );
    log.append( "+------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+\n" );
    log.append( "| No   | a               | b               | x               | f(a)            | f(b)            | f(x)            | |a - b|         |\n" );
  }

  @Override
  public double refine( double prex, double EPS ) {
    iterations++;
    double x = prex - FUN.apply( prex ) * ( b - prex ) / ( FUN.apply( b ) - FUN.apply( prex ) );
    log.append( "+------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+\n" );
    log.append( String.format( "| %1$04d | %2$15.4e | %3$15.4e | %4$15.4e | %5$15.4e | %6$15.4e | %7$15.4e | %8$15.4e |\n", iterations, x, b, x, FUN.apply( x ), FUN.apply( b ), FUN.apply( x ), Math.abs( x - b ) ) );
    if ( new AbsoluteFunDeviator( x, FUN ).deviation() <= EPS )
      return x;
    else return refine( x, EPS );
  }

  @Override
  public String getRefineLog( ) {
    log.append( "+------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+\n" );
    return log.toString();
  }
}
