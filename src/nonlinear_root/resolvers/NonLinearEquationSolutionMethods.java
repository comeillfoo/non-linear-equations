package nonlinear_root.resolvers;

import java.util.Arrays;

public enum NonLinearEquationSolutionMethods {
  NLESM_BOTH_CHORDS( "хорд ( общая )", 0 ),
  NLESM_LEFT_CHORDS( "хорд ( фиксированный левый конец )", 1 ),
  NLESM_RIGHT_CHORDS( "хорд ( фиксированный правый конец )", 2 ),
  NLESM_NEWTON( "Ньютона", 3 ),
  NLESM_SIMPLE_ITERATIONS( "простых итераций", 4 );

  private final String NAME;
  public final int IDX;

  NonLinearEquationSolutionMethods( String name, int index ) {
    NAME = name; IDX = index;
  }

  @Override
  public String toString() {
    return NAME;
  }

  public static String[] methods() {
    return Arrays.stream( NonLinearEquationSolutionMethods.values( ) ).map( ( m )->( "Метод " + m.NAME ) ).toArray( String[]::new );
  }
}
