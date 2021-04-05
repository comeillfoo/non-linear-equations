package nonlinear_root.refiners.just.chords;

import nonlinear_root.deviations.AbsoluteFunDeviator;
import nonlinear_root.refiners.Thresholdable;

import java.util.function.Function;

public class BothChordRefiner extends ChordRefiner implements Thresholdable {

    public BothChordRefiner( double a, double b, Function<Double, Double> fun ) {
        super( a, b, fun );
    }

    @Override
    public double refine( double prex, double EPS ) {
        if ( iterations >= getThreshold() )
            return prex;
        iterations++;
        double x = ( a * FUN.apply( b ) - b * FUN.apply( a ) ) / ( FUN.apply( b ) - FUN.apply( a ) );
        if ( new AbsoluteFunDeviator( x, FUN ).deviation() <= EPS )
            return x;
        else {
            if ( FUN.apply( a ) * FUN.apply( x ) < 0 ) b = x;
            else a = x;
            return refine( x, EPS );
        }
    }
}
