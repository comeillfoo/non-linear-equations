package nonlinear_root.refiners.just;

import nonlinear_root.deviations.simpleiters.AbsoluteSimpleIterationsDeviator;
import nonlinear_root.refiners.Refiner;

import java.util.function.Function;

public class SimpleIterationsRefiner implements Refiner {

    protected final double Q;
    protected final Function<Double, Double> PHI_FUN;
    protected int iterations = 0;

    public SimpleIterationsRefiner(double q, Function<Double, Double> phi_fun) {
        Q = q;
        PHI_FUN = phi_fun;
    }

    @Override
    public double refine( double prex, double EPS ) {
        iterations++;
        double x = PHI_FUN.apply( prex );
        if ( new AbsoluteSimpleIterationsDeviator( x, prex, Q ).deviation() <= EPS )
            return x;
        else return refine( x, EPS );
    }

    @Override
    public int getIterations() {
        return iterations;
    }
}
