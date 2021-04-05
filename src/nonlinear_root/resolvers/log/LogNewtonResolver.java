package nonlinear_root.resolvers.log;

import nonlinear_root.convergencers.sufficient.log.LogNewtonSufficientConvergenceChecker;
import nonlinear_root.refiners.LogRefiner;
import nonlinear_root.refiners.log.LogNewtonRefiner;
import nonlinear_root.resolvers.just.NewtonResolver;
import nonlinear_root.resolvers.latex.LogResolver;

import java.util.function.Function;

public final class LogNewtonResolver extends NewtonResolver implements LogResolver {
    public LogNewtonResolver(double a, double b, double eps, Function<Double, Double> fun, Function<Double, Double> dfun, Function<Double, Double> ddfun) {
        super(a, b, eps, fun, dfun, ddfun);
        CHECKER = new LogNewtonSufficientConvergenceChecker( a , b, fun, dfun, ddfun );
        REFINER = new LogNewtonRefiner( fun, dfun );
    }

    @Override
    public String getLog( ) {
        return ( ( LogRefiner ) REFINER ).getRefineLog();
    }
}
