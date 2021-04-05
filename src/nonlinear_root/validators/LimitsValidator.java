package nonlinear_root.validators;

public class LimitsValidator implements Validator {
  public double getLeftLimit( ) {
    return a;
  }

  public double getRightLimit( ) {
    return b;
  }

  private double a;
  private double b;

  public LimitsValidator( double a, double b ) {
    this.a = a;
    this.b = b;
    validate();
  }

  @Override
  public void validate( ) {
    if ( a > b ) {
      double tmp = a;
      a = b;
      b = tmp;
    }
  }
}
