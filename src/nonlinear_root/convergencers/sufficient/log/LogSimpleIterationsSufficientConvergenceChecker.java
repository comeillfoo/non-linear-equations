package nonlinear_root.convergencers.sufficient.log;

import nonlinear_root.convergencers.sufficient.SimpleIterationsSufficientConvergenceChecker;
import nonlinear_root.utilities.NonLinearShell;

import java.util.function.Function;

public final class LogSimpleIterationsSufficientConvergenceChecker extends SimpleIterationsSufficientConvergenceChecker {


    public LogSimpleIterationsSufficientConvergenceChecker( double a, double b, double q, double lambda, Function<Double, Double> phiFun, Function<Double, Double> dphiFun ) {
        super( a, b, q, lambda, phiFun, dphiFun );
    }

    @Override
    public boolean convergence() {
        NonLinearShell.stdOutPrintLn( "\nПроверка теоремы о достаточной сходимости метода простых итераций:" );
        NonLinearShell.stdOutPrintLn( "Функция |φ'(x)| должна быть меньше или равна q, которая меньше 1," );
        NonLinearShell.stdOutPrintLn( "где q — некоторая константа Липшица или коэффициента сжатия.\n" );
        boolean result = super.convergence();
        if ( result )
            NonLinearShell.stdOutPrintLn( "Достаточные условия сходимости метода простых итераций выполнены.\n" );
        else NonLinearShell.stdOutPrintLn( "Достаточные условия сходимости метода простых итераций не выполнены.\n" );
        NonLinearShell.stdOutPrintLn( "q = " + Q + "; λ = " + LAMBDA );
        NonLinearShell.stdOutPrintLn( "φ(a) = " + PHI_FUN.apply( A ) );
        NonLinearShell.stdOutPrintLn( "φ(b) = " + PHI_FUN.apply( B ) );
        NonLinearShell.stdOutPrintLn( "|φ'(a)| = " + Math.abs( DERIVATIVE_OF_PHI_FUN.apply( A ) ) );
        NonLinearShell.stdOutPrintLn( "|φ'(b)| = " + Math.abs( DERIVATIVE_OF_PHI_FUN.apply( B ) ) );
        NonLinearShell.stdOutPrintLn( "|φ'((a + b) / 2)| = " + Math.abs( DERIVATIVE_OF_PHI_FUN.apply( A / 2 + B / 2 ) ) );
        return result;
    }
}
