/*  E/17/312
    S.A.I.U. Sangarasekara
    CO225 - Project1
 */
import java.awt.*;

public class Julia extends Mandelbrot {

    private double constantX;
    private double constantY;
    private static final double DEFAULT_JULIA_C_X = -0.4;
    private static final double DEFAULT_JULIA_C_Y = 0.6;

    //constructor for Julia
    public Julia(double constant_xValue, double constant_yValue, int iter) {
        super();
        this.constantX = constant_xValue;
        this.constantY = constant_yValue;
    }

    public Julia() {
        this(DEFAULT_JULIA_C_X, DEFAULT_JULIA_C_Y, DEFAULT_ITER);

    }

    //setting the color of a pixel for given point
    @Override
    public int calculateColor(int x, int y) {

        double xCopy = startX + x * dx;
        double yCopy = startY + y * dy;
        int i;

        for (i = 0; i < iterations; i++) {

            double squareX = xCopy * xCopy;
            double squareY = yCopy * yCopy;


            if (squareX + squareY > 4.0) {
                break;
            }

            double tempY = 2 * xCopy * yCopy;
            xCopy = squareX - squareY + this.constantX;
            yCopy = tempY + this.constantY;
        }
        
        //if abs(z)<=2 give black color and if abs(z)>2 give the relevant color
        if (i == iterations) {
            return 0x00000000;
        } else {
            return Color.HSBtoRGB((float) i*5 / iterations, 0.5f, 1);
        }

    }

}
