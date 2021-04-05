package nonlinear_root.resolvers.log;

import nonlinear_root.convergencers.sufficient.log.LogSimpleIterationsSufficientConvergenceChecker;
import nonlinear_root.refiners.LogRefiner;
import nonlinear_root.refiners.log.LogSimpleIterationsRefiner;
import nonlinear_root.resolvers.just.SimpleIterationsResolver;
import nonlinear_root.resolvers.latex.LogResolver;

import java.util.function.Function;

public final class LogSimpleIterationsResolver extends SimpleIterationsResolver implements LogResolver {
    public LogSimpleIterationsResolver(double a, double b, double eps, Function<Double, Double> fun, Function<Double, Double> dfun) {
        super(a, b, eps, fun, dfun);
        CHECKER = new LogSimpleIterationsSufficientConvergenceChecker( a, b, Q, LAMBDA, PHI_FUN, DERIVATE_OF_PHI_FUN );
        REFINER = new LogSimpleIterationsRefiner( Q, PHI_FUN, FUN );
    }

    @Override
    public String getLog( ) {
        return ( ( LogRefiner ) REFINER ).getRefineLog();
    }
}
