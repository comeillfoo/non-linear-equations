package nonlinear_root.refiners.log;

import nonlinear_root.deviations.newton.AbsoluteFunDiffFunDeviator;
import nonlinear_root.refiners.LogRefiner;
import nonlinear_root.refiners.just.NewtonRefiner;

import java.util.function.Function;

public final class LogNewtonRefiner extends NewtonRefiner implements LogRefiner {
  protected final StringBuilder log = new StringBuilder( "" );

  public LogNewtonRefiner( Function<Double, Double> fun, Function<Double, Double> derivate ) {
    super( fun, derivate );
    log.append( "Уточнение корня уравнения методом Ньютона\n" );
    log.append( "+------+-----------------+-----------------+-----------------+-----------------+-----------------+\n" );
    log.append( "| No   | x_k             | f(x_k)          | f'(x_k)         | x_{k + 1}       | |x_k - x_{k+1}| |\n" );
  }

  @Override
  public double refine( double prex, double EPS ) {
    if ( iterations >= getThreshold() )
      return prex;
    double x = prex - FUN.apply( prex ) / DERIVATE.apply( prex );
    log.append( "+------+-----------------+-----------------+-----------------+-----------------+-----------------+\n" );
    log.append( String.format( "| %1$04d | %2$15.4e | %3$15.4e | %4$15.4e | %5$15.4e | %6$15.4e |\n", iterations, prex, DERIVATE.apply( prex ), x, FUN.apply( prex ), Math.abs( prex - x ) ) );
    iterations++;
    if ( new AbsoluteFunDiffFunDeviator( x, FUN, DERIVATE ).deviation() <= EPS )
      return x;
    else return refine( x, EPS );
  }

  @Override
  public String getRefineLog( ) {
    log.append( "+------+-----------------+-----------------+-----------------+-----------------+-----------------+\n" );
    return log.toString();
  }
}
