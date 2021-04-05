package nonlinear_root.resolvers.latex;

import nonlinear_root.convergencers.sufficient.log.LogSimpleIterationsSufficientConvergenceChecker;
import nonlinear_root.refiners.latex.LatexLogSimpleIterationsRefiner;
import nonlinear_root.refiners.LogRefiner;
import nonlinear_root.resolvers.just.SimpleIterationsResolver;

import java.util.function.Function;

public final class LatexLogSimpleIterationsResolver extends SimpleIterationsResolver implements LogResolver {
    public LatexLogSimpleIterationsResolver(double a, double b, double eps, Function<Double, Double> fun, Function<Double, Double> dfun) {
        super(a, b, eps, fun, dfun);
        CHECKER = new LogSimpleIterationsSufficientConvergenceChecker( a, b, Q, LAMBDA, PHI_FUN, DERIVATE_OF_PHI_FUN );
        REFINER = new LatexLogSimpleIterationsRefiner( Q, PHI_FUN, fun );
    }

    @Override
    public String getLog() {
        return ( ( LogRefiner ) REFINER ).getRefineLog();
    }
}
