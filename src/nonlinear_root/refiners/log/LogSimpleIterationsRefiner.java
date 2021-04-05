package nonlinear_root.refiners.log;

import nonlinear_root.deviations.simpleiters.AbsoluteSimpleIterationsDeviator;
import nonlinear_root.refiners.LogRefiner;
import nonlinear_root.refiners.just.SimpleIterationsRefiner;

import java.util.function.Function;

public final class LogSimpleIterationsRefiner extends SimpleIterationsRefiner implements LogRefiner {
  protected final StringBuilder log = new StringBuilder( "" );
  protected final Function<Double, Double> FUN;

  public LogSimpleIterationsRefiner( double q, Function<Double, Double> phi_fun, Function<Double, Double> fun ) {
    super( q, phi_fun );
    FUN = fun;
    log.append( "Уточнение корня уравнения методом простой итерации\n" );
    log.append( "+------+-----------------+-----------------+-----------------+-----------------+-----------------+\n" );
    log.append( "| No   | x_k             | f(x_k)          | x_{k + 1}       | φ(x_k)          | |x_k - x_{k+1}| |\n" );
  }

  @Override
  public double refine( double prex, double EPS ) {
    double x = PHI_FUN.apply( prex );
    log.append( "+------+-----------------+-----------------+-----------------+-----------------+-----------------+\n" );
    log.append( String.format( "| %1$04d | %2$15.4e | %3$15.4e | %4$15.4e | %5$15.4e | %6$15.4e |\n", iterations, prex, FUN.apply( prex ), x, PHI_FUN.apply( prex ), Math.abs( prex - x ) ) );
    iterations++;
    if ( new AbsoluteSimpleIterationsDeviator( x, prex, Q ).deviation() <= EPS )
      return x;
    else return refine( x, EPS );
  }

  @Override
  public String getRefineLog( ) {
    log.append( "+------+-----------------+-----------------+-----------------+-----------------+-----------------+\n" );
    return log.toString();
  }
}
