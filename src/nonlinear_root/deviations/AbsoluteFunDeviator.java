package nonlinear_root.deviations;

import java.util.function.Function;

public final class AbsoluteFunDeviator implements Deviator {
    protected final Function<Double, Double> FUN;
    protected final double X;

    public AbsoluteFunDeviator(double x, Function<Double, Double> fun ) {
        FUN = fun;
        X = x;
    }

    @Override
    public double deviation() {
        return Math.abs( FUN.apply( X ) );
    }
}
