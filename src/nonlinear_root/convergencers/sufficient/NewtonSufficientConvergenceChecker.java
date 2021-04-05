package nonlinear_root.convergencers.sufficient;

import java.util.function.Function;

public class NewtonSufficientConvergenceChecker extends ChordSufficientConvergenceChecker {

    public NewtonSufficientConvergenceChecker(double a, double b, Function<Double, Double> fun, Function<Double, Double> dfun, Function<Double, Double> ddfun) {
        super(a, b, fun, dfun, ddfun);
    }

    @Override
    public boolean convergence() {
        return super.convergence() 
        && ( DERIVATE_OF_FUN.apply( A ) != 0 ) 
        && ( DERIVATE_OF_FUN.apply( B ) != 0 ) 
        && ( DERIVATE_OF_FUN.apply( ( A + B ) / 2 ) != 0 );
    }
}
