package nonlinear_root.refiners;

public interface Refiner {
    double refine(double prex, double EPS );

    int getIterations();
}
