package nonlinear_root.resolvers.just.chords;

import nonlinear_root.initial_approximation_providers.chords.LeftChordInitialApproximationProvider;
import nonlinear_root.refiners.just.chords.LeftChordRefiner;

import java.util.function.Function;

public class LeftChordResolver extends ChordResolver {

    public LeftChordResolver(double a, double b, double eps, Function<Double, Double> fun, Function<Double, Double> dfun, Function<Double, Double> ddfun ) {
        super(a, b, eps, fun, dfun, ddfun );
        INIT = new LeftChordInitialApproximationProvider();
        REFINER = new LeftChordRefiner( a, b, fun );
    }
}
