package nonlinear_root.resolvers.just.chords;

import nonlinear_root.convergencers.sufficient.ChordSufficientConvergenceChecker;
import nonlinear_root.resolvers.Resolver;

import java.util.function.Function;

public abstract class ChordResolver extends Resolver {
    public ChordResolver(double a, double b, double eps, Function<Double, Double> fun, Function<Double, Double> dfun, Function<Double, Double> ddfun ) {
        super(a, b, eps, fun);
        CHECKER = new ChordSufficientConvergenceChecker( a, b, fun, dfun, ddfun );
    }
}
