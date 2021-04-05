package nonlinear_root.initial_approximation_providers;

import java.util.function.Function;

public final class FastConvergenceInitialApproximationProvider implements InitialApproximationProvider {
    protected final Function<Double, Double> DERIVATIVE_OF_DERIVATIVE_OF_FUN;

    public FastConvergenceInitialApproximationProvider(Function<Double, Double> ddfun ) {
        DERIVATIVE_OF_DERIVATIVE_OF_FUN = ddfun;
    }

    @Override
    public double init( double a, double b, Function<Double, Double> fun ) {
        return ( fun.apply( a ) * DERIVATIVE_OF_DERIVATIVE_OF_FUN.apply( a ) > 0 )? a : b;
    }
}
