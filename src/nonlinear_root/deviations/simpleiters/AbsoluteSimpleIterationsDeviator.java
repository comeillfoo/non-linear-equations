package nonlinear_root.deviations.simpleiters;

import nonlinear_root.deviations.Deviator;

public final class AbsoluteSimpleIterationsDeviator implements Deviator {
    protected final double X;
    protected final double PREVIOUS_X;
    protected final double Q;

    public AbsoluteSimpleIterationsDeviator( double x, double prex, double q ) {
        X = x;
        PREVIOUS_X = prex;
        Q = q;
    }

    @Override
    public double deviation() {
        if ( 0.5 < Q && Q < 1 )
            return Math.abs( X - PREVIOUS_X ) * Q / ( 1 - Q );
        else return Math.abs( X - PREVIOUS_X );
    }
}
