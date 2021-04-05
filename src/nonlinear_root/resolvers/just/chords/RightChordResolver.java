package nonlinear_root.resolvers.just.chords;

import nonlinear_root.initial_approximation_providers.chords.RightChordInitialApproximationProvider;
import nonlinear_root.refiners.just.chords.RightChordRefiner;

import java.util.function.Function;

public class RightChordResolver extends ChordResolver {
    public RightChordResolver(double a, double b, double eps, Function<Double, Double> fun, Function<Double, Double> dfun, Function<Double, Double> ddfun ) {
        super(a, b, eps, fun, dfun, ddfun );
        INIT = new RightChordInitialApproximationProvider();
        REFINER = new RightChordRefiner( a, b, fun );
    }
}
