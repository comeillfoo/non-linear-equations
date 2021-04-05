package nonlinear_root.convergencers;

public final class FalseStubConvergencer implements Convergencer {
  @Override
  public boolean convergence( ) {
    return false;
  }
}
