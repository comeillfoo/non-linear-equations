package nonlinear_root.convergencers.sufficient.log;

import nonlinear_root.convergencers.sufficient.NewtonSufficientConvergenceChecker;
import nonlinear_root.utilities.NonLinearShell;

import java.util.function.Function;

public final class LogNewtonSufficientConvergenceChecker extends NewtonSufficientConvergenceChecker {
    @Override
    public boolean convergence() {
        NonLinearShell.stdOutPrintLn( "\nПроверка теоремы о достаточной сходимости метода Ньютона:" );
        NonLinearShell.stdOutPrintLn( "Функция f(x) должна быть определена и непрерывна на отрезке [ a; b ]" );
        NonLinearShell.stdOutPrintLn( "Функция должна удовлетворять условию f(a)f(b) < 0," );
        NonLinearShell.stdOutPrintLn( "т.е. функция на концах отрезка [ a; b ] функция имеет разные знаки." );
        NonLinearShell.stdOutPrintLn( "производные f'(x) и f''(x) сохраняют знак на отрезке [ a; b ]." );
        NonLinearShell.stdOutPrintLn( "Производная f'(x) не равна нулю.\n" );
        boolean result = super.convergence();
        if ( result )
            NonLinearShell.stdOutPrintLn( "Достаточные условия сходимости метода Ньютона выполнены.\nКорректность всех дальнейших вычислений гарантируется.\n" );
        else NonLinearShell.stdOutPrintLn( "Достаточные условия сходимости метода Ньютона не выполнены. Корректность всех дальнейших вычислений не гарантируется.\n" );
        NonLinearShell.stdOutPrintLn( "f(a) = " + FUN.apply( A ) + " f(b) = " + FUN.apply( B ) );
        NonLinearShell.stdOutPrintLn( "f'(a) = " + DERIVATE_OF_FUN.apply( A ) + " f'(b) = " + DERIVATE_OF_FUN.apply( B ) );
        NonLinearShell.stdOutPrintLn( "f''(a) = " + DERIVATE_OF_DERIVATE_OF_FUN.apply( A ) + " f''(b) = " + DERIVATE_OF_DERIVATE_OF_FUN.apply( B ) );
        return true;
    }

    public LogNewtonSufficientConvergenceChecker(double a, double b, Function<Double, Double> fun, Function<Double, Double> dfun, Function<Double, Double> ddfun) {
        super(a, b, fun, dfun, ddfun);
    }
}
