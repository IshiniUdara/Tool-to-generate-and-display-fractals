/*  E/17/312
    S.A.I.U. Sangarasekara
    CO225 - Project1
 */

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Mandelbrot extends JPanel {


    protected double startX;
    protected double startY;
    protected double dx, dy;
    public int iterations;
    private BufferedImage buffer;
    private static final double DEFAULT_START_X = -1.0;
    private static final double DEFAULT_END_X = 1.0;
    private static final double DEFAULT_START_Y = -1.0;
    private static final double DEFAULT_END_Y = 1.0;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    public static final int DEFAULT_ITER = 1000;

    //constructor for Mandelbrot
    public Mandelbrot(double startX, double endX,
                      double startY, double endY, int iter) {
        this.iterations = iter;
        this.startX = startX;
        this.startY = startY;
        double xRange = Math.abs(endX - startX);
        double yRange = Math.abs(endY - startY);
        this.dx = xRange / (WIDTH - 1);
        this.dy = yRange / (HEIGHT - 1);


        buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);


    }


    public Mandelbrot(double startX, double endX,
                      double startY, double endY) {
        this(startX, endX, startY, endY, DEFAULT_ITER);
    }

    public Mandelbrot() {
        this(DEFAULT_START_X, DEFAULT_END_X,
                DEFAULT_START_Y, DEFAULT_END_Y, DEFAULT_ITER);
    }


    @Override
    public void addNotify() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    //performing the paint operation
    @Override
    public void paint(Graphics g) {
        
    
        g.drawImage(buffer, 0, 0, null);
        
    }

    //performing setting the colour and position of each pixel
    private void colorPixels(int xGridStart, int xGridEnd, int yGridStart, int yGridEnd) {

        for (int x = xGridStart; x <= xGridEnd; x++) {
            for (int y = yGridStart; y <= yGridEnd; y++) {
                int color = this.calculateColor(x, y);
                buffer.setRGB(x, y, color);
            }
        }
    }

    //colouring the pixels using threads 
    public void render(){

        Thread thread1 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        colorPixels(0, WIDTH/2 - 1, 0, HEIGHT/2 - 1);
                    }
                }
        );
        thread1.start();

        Thread thread2 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        colorPixels(WIDTH/2, WIDTH-1, 0, HEIGHT/2 - 1);
                    }
                }
        );
        thread2.start();

        Thread thread3 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        colorPixels(0, WIDTH/2 - 1, HEIGHT/2, HEIGHT-1);
                    }
                }
        );
        thread3.start();

        Thread thread4 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        colorPixels(WIDTH/2, WIDTH-1, HEIGHT/2, HEIGHT-1);
                    }
                }
        );
        thread4.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    //calculating the color of a pixel for given point
    public int calculateColor(int x, int y) {

        double xPart = startX + x * dx;
        double yPart = startY + y * dy;
        double xCopy = xPart;
        double yCopy = yPart;
        int i;
        for (i = 0; i < iterations; i++) {

            double squareX = xCopy * xCopy;
            double squareY = yCopy * yCopy;

            if (squareX + squareY > 4.0) {
                break;
            }

            double tempY = 2 * xCopy * yCopy;

            xCopy = squareX - squareY + xPart;
            yCopy = tempY + yPart;

        }
 
        //if abs(z)<=2 give black color and if abs(z)>2 give the relevant color
        if (i == iterations) {
            return 0x00000000;
        } else {
            return Color.HSBtoRGB((float) i*5/ iterations, 0.5f, 1.0f);
        }

    }
}