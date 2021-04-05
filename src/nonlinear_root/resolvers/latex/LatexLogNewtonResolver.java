package nonlinear_root.resolvers.latex;

import nonlinear_root.convergencers.sufficient.log.LogNewtonSufficientConvergenceChecker;
import nonlinear_root.refiners.latex.LatexLogNewtonRefiner;
import nonlinear_root.refiners.LogRefiner;
import nonlinear_root.resolvers.just.NewtonResolver;

import java.util.function.Function;

public final class LatexLogNewtonResolver extends NewtonResolver implements LogResolver {

    public LatexLogNewtonResolver(double a, double b, double eps, Function<Double, Double> fun, Function<Double, Double> dfun, Function<Double, Double> ddfun) {
        super(a, b, eps, fun, dfun, ddfun);
        CHECKER = new LogNewtonSufficientConvergenceChecker( a , b, fun, dfun, ddfun );
        REFINER = new LatexLogNewtonRefiner( fun, dfun );
    }

    @Override
    public String getLog() {
        return ( ( LogRefiner ) REFINER ).getRefineLog();
    }
}
