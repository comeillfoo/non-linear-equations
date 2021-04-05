package nonlinear_root.initial_approximation_providers;

import java.util.function.Function;

public interface InitialApproximationProvider {
    double init(double a, double b, Function<Double, Double> fun );
}
