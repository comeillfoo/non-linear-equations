package nonlinear_root.resolvers.just;

import nonlinear_root.convergencers.sufficient.NewtonSufficientConvergenceChecker;
import nonlinear_root.initial_approximation_providers.FastConvergenceInitialApproximationProvider;
import nonlinear_root.refiners.just.NewtonRefiner;
import nonlinear_root.resolvers.Resolver;

import java.util.function.Function;

public class NewtonResolver extends Resolver {

    public NewtonResolver( double a, double b, double eps, Function<Double, Double> fun, Function<Double, Double> dfun, Function<Double, Double> ddfun ) {
        super(a, b, eps, fun);
        CHECKER = new NewtonSufficientConvergenceChecker( a, b, fun, dfun, ddfun );
        INIT = new FastConvergenceInitialApproximationProvider( ddfun );
        REFINER = new NewtonRefiner( fun, dfun );
    }
}
