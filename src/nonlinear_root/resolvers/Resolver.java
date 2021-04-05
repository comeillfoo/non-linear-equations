package nonlinear_root.resolvers;

import nonlinear_root.convergencers.Convergencer;
import nonlinear_root.initial_approximation_providers.InitialApproximationProvider;
import nonlinear_root.refiners.Refiner;

import java.util.function.Function;

public abstract class Resolver extends Solver implements IterativeResolver {
    protected Convergencer CHECKER;
    protected InitialApproximationProvider INIT;
    protected Refiner REFINER;


    public Resolver(double a, double b, double eps, Function<Double, Double> fun) {
        super(a, b, eps, fun);
    }

    public double solve() {
        return super.solve( CHECKER, INIT, REFINER );
    }

    @Override
    public int getIterations() {
        return REFINER.getIterations();
    }
}
