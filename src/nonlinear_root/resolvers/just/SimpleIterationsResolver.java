package nonlinear_root.resolvers.just;

import nonlinear_root.convergencers.sufficient.SimpleIterationsSufficientConvergenceChecker;
import nonlinear_root.initial_approximation_providers.SimpleIterationsInitialApproximationProvider;
import nonlinear_root.refiners.just.SimpleIterationsRefiner;
import nonlinear_root.resolvers.Resolver;

import java.util.function.Function;

public class SimpleIterationsResolver extends Resolver {
    protected final double Q;
    protected final double LAMBDA;
    protected final Function<Double, Double> PHI_FUN;
    protected final Function<Double, Double> DERIVATE_OF_PHI_FUN;


    public SimpleIterationsResolver(double a, double b, double eps, Function<Double, Double> fun, Function<Double, Double> dfun ) {
        super(a, b, eps, fun);
        LAMBDA = -1 / Math.max( dfun.apply( a ), dfun.apply( b ) );
        PHI_FUN = ( x )->( x + LAMBDA * fun.apply( x ) );
        DERIVATE_OF_PHI_FUN = ( x )->( 1 + LAMBDA * dfun.apply( x ) );
        Q = Math.max( Math.abs( DERIVATE_OF_PHI_FUN.apply( a ) ), Math.abs( DERIVATE_OF_PHI_FUN.apply( b ) ) );
        CHECKER = new SimpleIterationsSufficientConvergenceChecker( a, b, Q, LAMBDA, PHI_FUN, DERIVATE_OF_PHI_FUN );
        INIT = new SimpleIterationsInitialApproximationProvider( PHI_FUN );
        REFINER = new SimpleIterationsRefiner( Q, PHI_FUN );
    }
}
