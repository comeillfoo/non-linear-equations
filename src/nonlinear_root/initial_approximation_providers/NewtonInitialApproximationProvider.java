package nonlinear_root.initial_approximation_providers;

import java.util.function.Function;

public final class NewtonInitialApproximationProvider implements InitialApproximationProvider {
    @Override
    public double init( double a, double b, Function<Double, Double> fun ) {
        return ( a + b ) / 2;
    }
}
