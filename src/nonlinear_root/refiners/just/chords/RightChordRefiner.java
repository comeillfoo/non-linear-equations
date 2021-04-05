package nonlinear_root.refiners.just.chords;

import nonlinear_root.deviations.AbsoluteFunDeviator;

import java.util.function.Function;

public class RightChordRefiner extends ChordRefiner {

    public RightChordRefiner(double a, double b, Function<Double, Double> fun) {
        super(a, b, fun);
    }

    @Override
    public double refine( double prex, double EPS ) {
        iterations++;
        double x = prex - FUN.apply( prex ) * ( b - prex ) / ( FUN.apply( b ) - FUN.apply( prex ) );
        if ( new AbsoluteFunDeviator( x, FUN ).deviation() <= EPS )
            return x;
        else return refine( x, EPS );
    }
}
