package nonlinear_root.resolvers.log.chords;

import nonlinear_root.convergencers.TrueStubConvergencer;
import nonlinear_root.convergencers.sufficient.log.LogChordSufficientConvergenceChecker;
import nonlinear_root.refiners.LogRefiner;
import nonlinear_root.refiners.log.chords.LogBothChordRefiner;
import nonlinear_root.resolvers.just.chords.BothChordResolver;
import nonlinear_root.resolvers.latex.LogResolver;

import java.util.function.Function;

public final class LogBothChordResolver extends BothChordResolver implements LogResolver {
    public LogBothChordResolver(double a, double b, double eps, Function<Double, Double> fun, Function<Double, Double> dfun, Function<Double, Double> ddfun) {
        super(a, b, eps, fun, dfun, ddfun);
        CHECKER = new TrueStubConvergencer();
        REFINER = new LogBothChordRefiner( a, b, fun );
    }

    @Override
    public String getLog() {
        return ( ( LogRefiner ) REFINER ).getRefineLog();
    }
}
