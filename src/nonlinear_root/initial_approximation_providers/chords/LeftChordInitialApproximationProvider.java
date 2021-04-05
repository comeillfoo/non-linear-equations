package nonlinear_root.initial_approximation_providers.chords;

import nonlinear_root.initial_approximation_providers.InitialApproximationProvider;

import java.util.function.Function;

public final class LeftChordInitialApproximationProvider implements InitialApproximationProvider {
    @Override
    public double init(double a, double b, Function<Double, Double> fun ) {
        return b;
    }
}