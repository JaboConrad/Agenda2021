/**
 * COMP 1020
 *
 * INSTRUCTOR    Dr. Heather Matheson
 * ASSIGNMENT    Assignment #3, question #2
 * @author       Jabo Conrad Nzitatira, 7843614
 * @version      2018-10-29
 *
 * PURPOSE: Java program that draws a scatterplot by reading in points of data from a file and displaying them.
 *
 */

import java.io.*;

public class A3Q2Conrad7843614 {
    public static void main(String[] args) {
        char[][] points;
        points = FileReader("b.txt");
        diplay(points);
    }

    public static char[][] FileReader(String fileName){
        /** PURPOSE: This method will take the file name and read the file assigned and process the file to return well
         *  sorted array of charcters.
         *
         *  String line - a string contaning a single line ina file;
         *  String[] tokens - an array of string token split based on " ";
         *  int ycoordinate - y coordinate read from the file;
         *  int validpoints - number of points that have met required standards;
         *  int[] x_coordinates - an array of x - coordinates;
         *  int[] y_coordinates - an array of y - coordinates;
         *  int[] yregression_coordinates - y coordinates of the regression line;
         *  char[][] points =character array of points;
         *  double y_average - average of y coordinates;
         *  double x_average - average of y coordinates;
         *  double meanleastsquare - gradient of the regression lina coordinates;
         *
         **/
        BufferedReader fileIn;
        String line;
        String[] tokens;
        int ycoordinate;
        int validpoints = 0 ;
        int[] x_coordinates = new int[40];
        int[] y_coordinates =  new int[40];
        int[] yregression_coordinates;
        char[][] points = new char[41][21];
        double y_average ;
        double x_average ;
        double meanleastsquare;
        int i = 0;
        int j =0;
        try {
            fileIn = new BufferedReader(new FileReader(fileName));
            line = fileIn.readLine();
            while (line != null) {
                tokens = line.split(" ");
                if(allInts(tokens[0]) && allInts(tokens[1])){  ycoordinate = Integer.parseInt(tokens[1]);
                    if(ycoordinate!=0){
                        x_coordinates[i] = Integer.parseInt(tokens[0]);
                        i++;
                        y_coordinates[j] = Integer.parseInt(tokens[1]);
                        j++;
                        validpoints++;
                    }
                }
                line = fileIn.readLine();
            }
            x_average = average(x_coordinates,validpoints);
            y_average = average(y_coordinates,validpoints);
            meanleastsquare = meanLeastSquare(x_coordinates,y_coordinates,x_average,y_average,validpoints);
            yregression_coordinates = yregressionpoint(y_average,meanleastsquare,x_coordinates,x_average,validpoints);
            points = coordinateFormer(x_coordinates,y_coordinates,yregression_coordinates,validpoints);
        } catch (IOException ioe) {
            System.out.println("The Error is: " + ioe.getMessage());
        } catch (NumberFormatException nfe) {
            System.out.println("The Line is: " + nfe.getMessage());
        }
        return points;
    }

    public static char[][] coordinateFormer(int[] x_coordinates,int[] y_coordinates,int[] yregression_coordinates,int validppints){
        /** PURPOSE: This method will the x coordinates the y regression line points as well as y coordinates and fit
         * them appropriately in the points char[]
         *
         *  int validpoints - number of points that have met required standards;
         *  int[] x_coordinates - an array of x - coordinates;
         *  int[] y_coordinates - an array of y - coordinates;
         *  int[] yregression_coordinates - y coordinates of the regression line;
         *  char[][] points - character array of points;
         **/
        char[][] points = new char[41][41];
        for (int i = 0; i <validppints ; i++) {
            if (x_coordinates[i] > 0 && y_coordinates[i] > 0 && yregression_coordinates[i] > 0) {
                if ( y_coordinates[i] == yregression_coordinates[i]) {
                    points[x_coordinates[i]][y_coordinates[i]] = '*';
                } else {
                    points[x_coordinates[i]][yregression_coordinates[i]] = '-';
                    points[x_coordinates[i]][y_coordinates[i]] = 'X';
                }
            }
        }
        return points;
    }

    public static int[] yregressionpoint(double y_average, double meanleastsquare, int[] x_coordinates, double x_average,int validpoints){
        /** PURPOSE: This method will take the y average, gradient least square line ,the x average and x coordinates and
         * make an appropriate y regressional coordinates array
         *
         *  int validpoints - number of points that have met required standards;
         *  int[] x_coordinates - an array of x - coordinates;
         *  int[] y_coordinates - an array of y - coordinates;
         *  int[] yregression_coordinates - y coordinates of the regression line;
         **/
        int[] yregressionpoints = new int[validpoints];
        for (int i = 0; i < validpoints; i++) {
            yregressionpoints[i] = (int) (y_average + meanleastsquare*x_coordinates[i] - meanleastsquare*x_average);
        }
        return yregressionpoints;
    }

    public static double average(int[] points,int validpoints){
        /** PURPOSE: This method will take an array of points and find their average
         *
         *  int validpoints - number of points that have met required standards;
         *  int[] points - an array of points
         *  **/
        double average;
        int sum = 0;
        for (int i = 0; i < points.length; i++) {
            sum += points[i];
        }
        average = sum / validpoints;
        return average;
    }

    public static double meanLeastSquare(int[] xcoordinates, int[] ycoordinates,double meanx, double meany,int validpoints){
        /** PURPOSE: This method will take the y average , the x average, y coordinates and x coordinates and
         * calculate the gradient of the least square line.
         *
         *  int validpoints - number of points that have met required standards;
         *  int[] x_coordinates - an array of x - coordinates;
         *  int[] y_coordinates - an array of y - coordinates;
         *  double y_average - average of y coordinates;
         *  double x_average - average of y coordinates;
         **/
        double[] numerator = new double[validpoints];
        double[] denominator= new double[validpoints];
        double[] multiples = new double[validpoints];
        double[] multiples2 = new double[validpoints];
        double sumnumerator =0 ;
        double sumdenominator =0 ;
        double meanLeastSquare;

        for (int i = 0; i < validpoints; i++) {
            multiples[i] = (xcoordinates[i] - meanx);
        }
        for (int j = 0; j < validpoints; j++) {
            multiples2[j] = (ycoordinates[j] - meany);
        }
        for (int j = 0; j < validpoints; j++) {
            numerator[j] = multiples[j]*multiples2[j];
        }
        for (int i = 0; i < validpoints; i++) {
            denominator[i] = (int) Math.pow(xcoordinates[i] - meanx,2);
        }
        for (int i = 0; i <validpoints; i++) {
            sumnumerator += numerator[i];
            sumdenominator += denominator[i];
        }
        meanLeastSquare = sumnumerator /sumdenominator;

        return meanLeastSquare;
    }

    public static void diplay(char[][] points) {
        /** PURPOSE: This method will take characters array print them out.
         *
         *  char[][] points - character array of points;
         * */
        for (int j = points.length-1; j >= 0; j--) {
            for (int i = points[0].length-1; i >= 0 ; i--) {
                if (points[j][i] == 'X') {
                    System.out.println("|"+" ".repeat(j*2)+points[j][i]);
                }
                if (points[j][i] == '*') {
                    System.out.println("|"+" ".repeat(j)+"--*--");
                }
                if (points[j][i] == '-') {
                    System.out.println("|"+" ".repeat(j)+"----");
                }

            }

        }
        System.out.print("|"+" _".repeat(points.length));
    }

    public static boolean allInts(String word){
        /** PURPOSE: This method will take string token look for if it contains only numerical characters.
         *
         *  String word- The string being searched.
         *  boolean result - will return true if found.
         * */
        boolean result = false;
        for (int i = 0; i < word.length() ; i++) {
            if (word.charAt(i) >= 48 && word.charAt(i) <= 58) {
                result = true;
            }
        }
        return result;
    }
}
