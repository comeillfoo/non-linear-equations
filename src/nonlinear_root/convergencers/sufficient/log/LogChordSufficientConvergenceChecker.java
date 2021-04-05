package nonlinear_root.convergencers.sufficient.log;

import nonlinear_root.convergencers.sufficient.ChordSufficientConvergenceChecker;
import nonlinear_root.utilities.NonLinearShell;

import java.util.function.Function;

public final class LogChordSufficientConvergenceChecker extends ChordSufficientConvergenceChecker {
    public LogChordSufficientConvergenceChecker(double a, double b, Function<Double, Double> fun, Function<Double, Double> dfun, Function<Double, Double> ddfun) {
        super(a, b, fun, dfun, ddfun);
    }

    @Override
    public boolean convergence() {
        NonLinearShell.stdOutPrintLn( "\nПроверка теоремы о достаточной сходимости метода хорд:" );
        NonLinearShell.stdOutPrintLn( "Функция f(x) должна быть определена и непрерывна на отрезке [ a; b ]" );
        NonLinearShell.stdOutPrintLn( "Функция должна удовлетворять условию f(a)f(b) < 0," );
        NonLinearShell.stdOutPrintLn( "т.е. функция на концах отрезка [ a; b ] функция имеет разные знаки." );
        NonLinearShell.stdOutPrintLn( "производные f'(x) и f''(x) сохраняют знак на отрезке [ a; b ].\n" );
        boolean result = super.convergence();
        if ( result )
            NonLinearShell.stdOutPrintLn( "Достаточные условия сходимости метода хорд выполнены:\n" );
        else NonLinearShell.stdOutPrintLn( "Достаточные условия сходимости метода хорд не выполнены:\n" );
        NonLinearShell.stdOutPrintLn( "f(a) = " + FUN.apply( A ) + " f(b) = " + FUN.apply( B ) );
        NonLinearShell.stdOutPrintLn( "f'(a) = " + DERIVATE_OF_FUN.apply( A ) + " f'(b) = " + DERIVATE_OF_FUN.apply( B ) );
        NonLinearShell.stdOutPrintLn( "f''(a) = " + DERIVATE_OF_DERIVATE_OF_FUN.apply( A ) + " f''(b) = " + DERIVATE_OF_DERIVATE_OF_FUN.apply( B ) );
        return result;
    }
}
