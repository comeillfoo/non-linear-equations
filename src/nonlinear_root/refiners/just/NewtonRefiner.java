package nonlinear_root.refiners.just;

import nonlinear_root.deviations.newton.AbsoluteFunDiffFunDeviator;
import nonlinear_root.refiners.Refiner;
import nonlinear_root.refiners.Thresholdable;

import java.util.function.Function;

public class NewtonRefiner implements Refiner, Thresholdable {
    protected final Function<Double, Double> FUN;
    protected final Function<Double, Double> DERIVATE;
    protected int iterations = 0;

    public NewtonRefiner( Function<Double, Double> fun, Function<Double, Double> derivate ) {
        FUN = fun;
        DERIVATE = derivate;
    }

    @Override
    public double refine( double prex, double EPS ) {
        if ( iterations >= getThreshold() )
            return prex;
        iterations++;
        double x = prex - FUN.apply( prex ) / DERIVATE.apply( prex );
        if ( new AbsoluteFunDiffFunDeviator( x, FUN, DERIVATE ).deviation() <= EPS )
            return x;
        else return refine( x, EPS );
    }

    @Override
    public int getIterations() {
        return iterations;
    }
}
