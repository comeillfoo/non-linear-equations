package nonlinear_root.resolvers;

import nonlinear_root.convergencers.Convergencer;
import nonlinear_root.initial_approximation_providers.InitialApproximationProvider;
import nonlinear_root.refiners.Refiner;

import java.util.function.Function;

public class Solver {
    protected final double A;
    protected final double B;
    protected final Function<Double, Double> FUN;
    protected final double EPS;

    protected Solver( double a, double b, double eps, Function<Double, Double> fun ) {
        A = a;
        B = b;
        FUN = fun;
        EPS = eps;
    }


    public double solve( Convergencer checker, InitialApproximationProvider initializer, Refiner refiner ) {
        if ( !checker.convergence() )
            return Double.NaN;
        double init = initializer.init( A, B, FUN );
        return refiner.refine( init, EPS );
    }
}
