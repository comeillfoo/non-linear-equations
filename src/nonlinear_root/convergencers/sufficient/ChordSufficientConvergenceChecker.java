package nonlinear_root.convergencers.sufficient;

import nonlinear_root.convergencers.Convergencer;

import java.util.function.Function;

// Подходит только методов с фиксированной границей
public class ChordSufficientConvergenceChecker implements Convergencer {
    protected final Function<Double, Double> FUN;
    protected final Function<Double, Double> DERIVATE_OF_FUN;
    protected final Function<Double, Double> DERIVATE_OF_DERIVATE_OF_FUN;
    protected final double A;
    protected final double B;

    public ChordSufficientConvergenceChecker( double a, double b, Function<Double, Double> fun, Function<Double, Double> dfun, Function<Double, Double> ddfun ) {
        FUN = fun;
        DERIVATE_OF_FUN = dfun;
        DERIVATE_OF_DERIVATE_OF_FUN = ddfun;
        A = a;
        B = b;
    }

    @Override
    public boolean convergence() {
        return ( FUN.apply( A ) * FUN.apply( B ) < 0 )
                && ( DERIVATE_OF_FUN.apply( A ) * DERIVATE_OF_FUN.apply( B ) > 0 )
                && ( DERIVATE_OF_DERIVATE_OF_FUN.apply( A ) * DERIVATE_OF_DERIVATE_OF_FUN.apply( B ) > 0 );
    }
}
