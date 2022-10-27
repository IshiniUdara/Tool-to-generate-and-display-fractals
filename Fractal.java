/*  E/17/312
    S.A.I.U. Sangarasekara
    CO225 - Project1
 */
import javax.swing.*;


public class Fractal {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Fractals");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Mandelbrot mandelbrot = checkInput(args);
        //coloring the grid
        mandelbrot.render();

        frame.setContentPane(mandelbrot);
        
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    //argument handeling
    private static Mandelbrot checkInput(String[] arr) {
        try {
            if (arr[0].equals("Mandelbrot") && arr.length == 6) {
                 return new Mandelbrot(Double.valueOf(arr[1]), Double.valueOf(arr[2]),Double.valueOf(arr[3]), Double.valueOf(arr[4]), Integer.valueOf(arr[5]));
            } else if (arr[0].equals("Mandelbrot") && arr.length == 5) {
                return new Mandelbrot(Double.valueOf(arr[1]), Double.valueOf(arr[2]),Double.valueOf(arr[3]), Double.valueOf(arr[4]));
            } else if(arr[0].equals("Mandelbrot") && arr.length == 1){
                    return new Mandelbrot();
            }
            else if (arr[0].equals("Julia") && arr.length == 4) {
                
                    return new Julia(Double.valueOf(arr[1]), Double.valueOf(arr[2]), Integer.valueOf(arr[3]));
            }else if(arr[0].equals("Julia") && arr.length == 1){

                    return new Julia();
            }
            else {
                errorMessage();
                System.exit(1);
            }
        } catch (Exception e) {
            errorMessage();
            System.exit(1);
        }
        return null;
    }

    //error message for invalid arguments and argument counts
    private static void errorMessage() {
        System.out.println("Invalid Input");
        System.out.println(" ");
        System.out.println("-Usage-");
        System.out.println("The first input argument should be the Mandelbrot or Julia");
        System.out.println(" ");
        System.out.println("-Mandelbrot-");
        System.out.println("If the set selected is Mandelbrot the user should give either 0, 4 or 5 arguments");
        System.out.println("Giving 0 argument will use default values for other parameters");
        System.out.println("4 arguments will be the region of interest in the complex plane and the 5th one is the number of iterations to do for a point.");
        System.out.println("Eg:- java Fractal Mandelbrot startReal endReal startComplex endComplex iterations");
        System.out.println("-Julia-");
        System.out.println("For the Julia set the user should give 0 or 3 arguments.");
        System.out.println("Giving 0 argument will use default values for other parameters");
        System.out.println("3 arguments will be the real and complex part for C and the number of iterations ");
        System.out.println("Eg:-java Fractal Julia real_part_of_C img_part_of_C iterations");
        System.out.println(" ");
  
    }

}
