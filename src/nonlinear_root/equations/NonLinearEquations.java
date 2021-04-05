package nonlinear_root.equations;

import java.util.Arrays;
import java.util.function.Function;
import static java.lang.Math.*;

public enum NonLinearEquations {
    EXAMPLE( ( x )->( -2.4 * x * x * x + 1.27 * x * x + 8.63 * x + 2.31 ),
            ( x )->( -7.2 * x * x + 2.54 * x + 8.63 ),
            ( x )->( -14.4 * x + 2.54 ),
            "-2.4 * x^3 + 1.27 * x^2 + 8.63 * x + 2.31", 0
    ),
    TRANSCENDENT( ( x )->( ( 4 * sqrt(3) + 3 ) * pow( cos( x ), 2 ) - 2 * sqrt(3) * cos(x) ),
            ( x )->(  -( 4 * sqrt(3) + 3 ) * sin( 2 * x ) + 2 * sqrt(3) * sin(x) ),
            ( x )->( -2 * ( 4 * sqrt(3) + 3 ) * cos( 2 * x ) + 2 * sqrt(3) * cos(x) ),
            "( 4√3 + 3 )cos^2(x) - 2√3cos(x)", 1
    ),
    MIXED( ( x )->( pow( exp( x ) * x, 4 ) - 0.01 ),
            ( x )->( 4 * exp( 4 * x ) * pow( x, 3 ) * ( x + 1 ) ),
            ( x )->( 4 * exp( 4 * x ) * pow( x, 2 ) * ( 4 * x * x + 8 * x + 3 ) ),
            "(x * e^x)^4 - 0.01", 2
    );

    public final Function<Double, Double> FUN;
    public final Function<Double, Double> DERIVATE_OF_FUN;
    public final Function<Double, Double> DERIVATE_OF_DERIVATE_OF_FUN;
    private final String EQUATION;
    public final int IDX;

    NonLinearEquations(Function<Double, Double> fun, Function<Double, Double> derivate_of_fun, Function<Double, Double> derivate_of_derivate_of_fun, String equation, int idx ) {
        FUN = fun;
        DERIVATE_OF_FUN = derivate_of_fun;
        DERIVATE_OF_DERIVATE_OF_FUN = derivate_of_derivate_of_fun;
        EQUATION = equation;
        IDX = idx;
    }

    @Override
    public String toString() {
        return EQUATION;
    }

    public static String[] equations() {
        return Arrays.stream( NonLinearEquations.values( ) ).map( ( eq )->( eq.EQUATION ) ).toArray( String[]::new );
    }
}
