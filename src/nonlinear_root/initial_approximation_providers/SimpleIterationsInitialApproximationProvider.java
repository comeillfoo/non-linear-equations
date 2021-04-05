package nonlinear_root.initial_approximation_providers;

import java.util.function.Function;

public final class SimpleIterationsInitialApproximationProvider implements InitialApproximationProvider {
    protected final Function<Double, Double> PHI_FUN;

    public SimpleIterationsInitialApproximationProvider( Function<Double, Double> phiFun ) {
        PHI_FUN = phiFun;
    }

    @Override
    public double init(double a, double b, Function<Double, Double> fun) {
        return PHI_FUN.apply( ( a + b ) / 2 );
    }
}
