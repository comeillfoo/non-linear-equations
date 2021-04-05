package nonlinear_root.refiners.latex;

import nonlinear_root.deviations.simpleiters.AbsoluteSimpleIterationsDeviator;
import nonlinear_root.refiners.LogRefiner;
import nonlinear_root.refiners.just.SimpleIterationsRefiner;

import java.util.function.Function;

public final class LatexLogSimpleIterationsRefiner extends SimpleIterationsRefiner implements LogRefiner {
    protected final StringBuilder log = new StringBuilder( "" );
    protected final Function<Double, Double> FUN;

    public LatexLogSimpleIterationsRefiner(double q, Function<Double, Double> phi_fun, Function<Double, Double> fun ) {
        super( q, phi_fun );
        FUN = fun;
        log.append( "\\begin{table}[H]\n" );
        log.append( "  \\centering\n" );
        log.append( "  \\caption{ Уточнение корня уравнения методом простой итерации }\n" );
        log.append( "  \\begin{tabular}{|c|c|c|c|c|c|}\n" ); // 6: 0:5
        log.append( "  \\hline\n" );
        log.append( "  \\No\\ шага & $ x_k $ & $ f(x_k) $ & $ x_{k + 1} $ & $ \\varphi(x_k) $ & $ |x_k - x_{k + 1}| $\\\\\\hline\n" );
    }

    @Override
    public double refine(double prex, double EPS) {
        double x = PHI_FUN.apply( prex );
        log.append( String.format( "    $ %1$04d $ & $ %2$15.4e $ & $ %3$15.4e $ & $ %4$15.4e $ & $ %5$15.4e $ & $ %6$15.4e $\\\\\\hline\n", iterations, prex, FUN.apply( prex ), x, PHI_FUN.apply( prex ), Math.abs( prex - x ) ) );
        iterations++;
        if ( new AbsoluteSimpleIterationsDeviator( x, prex, Q ).deviation() <= EPS )
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
