package nonlinear_root.deviations.newton;

import nonlinear_root.deviations.Deviator;

import java.util.function.Function;

public final class AbsoluteFunDiffFunDeviator implements Deviator {
    protected final Function<Double, Double> FUN;
    protected final Function<Double, Double> DFUN;
    protected final double X;

    public AbsoluteFunDiffFunDeviator( double x, Function<Double, Double> fun, Function<Double, Double> dfun ) {
        X = x;
        FUN = fun;
        DFUN = dfun;
    }

    @Override
    public double deviation() {
        return Math.abs( FUN.apply( X ) / DFUN.apply( X ) );
    }
}
