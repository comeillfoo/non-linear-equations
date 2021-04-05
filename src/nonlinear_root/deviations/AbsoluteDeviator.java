package nonlinear_root.deviations;

public final class AbsoluteDeviator implements Deviator {
    protected final double X;
    protected final double PREVIOUS_X;

    public AbsoluteDeviator(double x, double prex ) {
        X = x;
        PREVIOUS_X = prex;
    }

    @Override
    public double deviation() {
        return Math.abs( X - PREVIOUS_X );
    }
}
