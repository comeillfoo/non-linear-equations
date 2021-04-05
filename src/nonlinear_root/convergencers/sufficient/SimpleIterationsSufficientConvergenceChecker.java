package nonlinear_root.convergencers.sufficient;

import nonlinear_root.convergencers.Convergencer;

import java.util.function.Function;

public class SimpleIterationsSufficientConvergenceChecker implements Convergencer {
    protected final Function<Double, Double> PHI_FUN;
    protected final Function<Double, Double> DERIVATIVE_OF_PHI_FUN;
    protected final double LAMBDA;
    protected final double Q;
    protected final double A;
    protected final double B;

    public SimpleIterationsSufficientConvergenceChecker( double a, double b, double q, double lambda, Function<Double, Double> phiFun, Function<Double, Double> dphiFun ) {
        Q = q;
        LAMBDA = lambda;
        PHI_FUN = phiFun;
        DERIVATIVE_OF_PHI_FUN = dphiFun;
        A = a;
        B = b;
    }

    @Override
    public boolean convergence() {
        return ( Q < 1 )
                && ( Math.abs( DERIVATIVE_OF_PHI_FUN.apply( A ) ) <= Q )
                && ( Math.abs( DERIVATIVE_OF_PHI_FUN.apply( B ) ) <= Q )
                && ( Math.abs( DERIVATIVE_OF_PHI_FUN.apply( ( A + B ) / 2 ) ) <= Q );
    }
}
