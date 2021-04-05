package nonlinear_root.refiners.just.chords;

import nonlinear_root.refiners.Refiner;

import java.util.function.Function;

public abstract class ChordRefiner implements Refiner {
    protected final Function<Double, Double> FUN;
    protected double a;
    protected double b;
    protected int iterations = 0;
    
    public ChordRefiner( double a, double b, Function<Double, Double> fun ) {
        FUN = fun;
        this.a = a;
        this.b = b;
    }

    @Override
    public int getIterations() {
        return iterations;
    }
}
