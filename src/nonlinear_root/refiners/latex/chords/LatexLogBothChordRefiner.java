package nonlinear_root.refiners.latex.chords;

import nonlinear_root.deviations.AbsoluteFunDeviator;
import nonlinear_root.refiners.LogRefiner;
import nonlinear_root.refiners.just.chords.BothChordRefiner;

import java.util.function.Function;

public final class LatexLogBothChordRefiner extends BothChordRefiner implements LogRefiner {
    private final StringBuilder log = new StringBuilder( "" );

    public LatexLogBothChordRefiner( double a, double b, Function<Double, Double> fun ) {
        super( a, b, fun );
        log.append( "\\begin{table}[H]\n" );
        log.append( "  \\centering\n" );
        log.append( "  \\caption{ Уточнение корня уравнения методом половинного деления ( хорд ) }\n" );
        log.append( "  \\begin{tabular}{|c|c|c|c|c|c|c|c|}\n" ); // 8: 0:7
        log.append( "  \\hline\n" );
        log.append( "  \\No\\ шага & a & b & x & $ f(a) $ & $ f(b) $ & $ f(x) $ & $ |a - b| $\\\\\\hline\n" );
    }

    @Override
    public double refine( double prex, double EPS ) {
        if ( iterations >= getThreshold() )
            return prex;
        iterations++;
        double x = ( a * FUN.apply( b ) - b * FUN.apply( a ) ) / ( FUN.apply( b ) - FUN.apply( a ) );
        log.append( String.format( "    $ %1$04d $ & $ %2$15.4e $ & $ %3$15.4e $ & $ %4$15.4e $ & $ %5$15.4e $ & $ %6$15.4e $ & $ %7$15.4e $ & $ %8$15.4e $\\\\\\hline\n", iterations, a, b, x, FUN.apply( a ), FUN.apply( b ), FUN.apply( x ), Math.abs( a - b ) ) );
        if ( new AbsoluteFunDeviator( x, FUN ).deviation() <= EPS )
            return x;
        else {
            if ( FUN.apply( a ) * FUN.apply( x ) < 0 ) b = x;
            else a = x;
            return refine( x, EPS );
        }
    }

    @Override
    public String getRefineLog() {
        log.append( "  \\end{tabular}\n" );
        log.append( "\\end{table}\n" );
        return log.toString();
    }
}
