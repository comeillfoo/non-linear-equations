package nonlinear_root.refiners.latex;

import nonlinear_root.deviations.newton.AbsoluteFunDiffFunDeviator;
import nonlinear_root.refiners.LogRefiner;
import nonlinear_root.refiners.Thresholdable;
import nonlinear_root.refiners.just.NewtonRefiner;

import java.util.function.Function;

public final class LatexLogNewtonRefiner extends NewtonRefiner implements LogRefiner, Thresholdable {
    protected final StringBuilder log = new StringBuilder( "" );

    public LatexLogNewtonRefiner(Function<Double, Double> fun, Function<Double, Double> derivate) {
        super( fun, derivate );
        log.append( "\\begin{table}[H]\n" );
        log.append( "  \\centering\n" );
        log.append( "  \\caption{ Уточнение корня уравнения методом Ньютона }\n" );
        log.append( "  \\begin{tabular}{|c|c|c|c|c|c|}\n" ); // 6: 0:5
        log.append( "  \\hline\n" );
        log.append( "  \\No\\ шага & $ x_k $ & $ f(x_k) $ & $ f'(x_k) $ & $ x_{k + 1} $ & $ |x_k - x_{k + 1}| $\\\\\\hline\n" );
    }

    @Override
    public double refine( double prex, double EPS ) {
        if ( iterations >= getThreshold() )
            return prex;
        double x = prex - FUN.apply( prex ) / DERIVATE.apply( prex );
        log.append( String.format( "    $ %1$04d $ & $ %2$15.4e $ & $ %3$15.4e $ & $ %4$15.4e $ & $ %5$15.4e $ & $ %6$15.4e $\\\\\\hline\n", iterations, prex, DERIVATE.apply( prex ), x, FUN.apply( prex ), Math.abs( prex - x ) ) );
        iterations++;
        if ( new AbsoluteFunDiffFunDeviator( x, FUN, DERIVATE ).deviation() <= EPS )
            return x;
        else return refine( x, EPS );
    }

    @Override
    public String getRefineLog() {
        log.append( "  \\end{tabular}\n" );
        log.append( "\\end{table}\n" );
        return log.toString();
    }
}
