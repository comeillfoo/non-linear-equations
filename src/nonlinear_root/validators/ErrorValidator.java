package nonlinear_root.validators;

public class ErrorValidator implements Validator {
  public double getError( ) {
    return err;
  }

  private double err;

  public ErrorValidator( double err ) {
    this.err = err;
    validate();
  }

  @Override
  public void validate( ) {
    if ( !( Math.abs( err ) > Double.MIN_VALUE ) ) {
      err = 0.01;
    }
  }
}
