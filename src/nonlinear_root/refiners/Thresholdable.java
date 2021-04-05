package nonlinear_root.refiners;

public interface Thresholdable {
  default int getThreshold( ) {
    return 0x8000;
  }
}
