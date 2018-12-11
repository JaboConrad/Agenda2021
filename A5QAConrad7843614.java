/**
 * COMP 1020
 *
 * INSTRUCTOR    Dr. Heather Matheson
 * ASSIGNMENT    Assignment #5, question #A
 * @author       Jabo Conrad Nzitatira, 7843614
 * @version      2018-12-06
 *
 * PURPOSE: Java program that uses recursion to print an input string in the shape of a triangle.
 * where the legs of the triangle are the first and last letters of the string and the base is the complete string;
 * the contents are made up of the letters in between.
 */
public class A5QAConrad7843614 {
    public static void main(String[] args) {
        wordChecker("abcdefghi");
        wordChecker("abcdefghij");
        wordChecker("disestablishmentarianism");
        wordChecker("+-! *# =~ ~= #* !-+");
        System.out.println("\nEnd of Processing");
    }

    public static void wordChecker(String word) {
        /** PURPOSE: This method will take the word and make a char[] array and then pass it ablong with its size to the
         * text drawing method.
         * String word - The array of the tokens generated after tokenization based on ",".
         **/
        char[] array = word.toCharArray();
        int size = array.length;
        TextTriangleDrawer(size, array);
    }//wordChecker

    public static void TextTriangleDrawer(int x, char[] chararray) {
        /** PURPOSE: This method will take the char[] array and its size pass them to the appropriate method evenTriangle
         * for even strings and oddTriangle for odd strings.
         *
         * int x - size of the char[] array.
         * char[] chararray - an array of characters that make uo the word.
         **/
        String word = new String(chararray);
        if (checkEven(x)) {
            System.out.println("\nAn Even Text Triangle for \'" + word + "\':" + " \n ");
            evenTriangle(x, x, chararray);
        } else {
            System.out.println("\nAn Odd Text Triangle for \'" + word + "\':" + " \n");
            oddTriangle(x, x, chararray);
        }
    }///TextTriangleDrawer

    public static void oddTriangle(int x, int org, char[] chararray) {
        /** PURPOSE: This method will take the char[] array and its size and make the appropriate printing to form an odd
         * triangle
         *
         * int x - size of the char[] array.
         * int org - size of the char[] array that never changes.
         * char[] chararray - an array of characters that make uo the word.
         **/
        if (x <= 0) {
            return;
        } else {
            oddTriangle(x - 1, org, chararray);
        }
        for (int i = 0; i < (org - x); i++) {
            System.out.print("  ");
        }
        if (x < chararray.length / 1.5) {
            for (int i = 0; i < x; i++) {
                System.out.print(chararray[i] + " ");
            }
            if (x >= 1)
                printreverse(x - 1, chararray);
            System.out.print("\n");
        }
    }//oddTriangle

    public static void evenTriangle(int x, int org, char[] chararray) {
        /** PURPOSE: This method will take the char[] array and its size and make the appropriate printing to form an even
         * triangle
         *
         * int x - size of the char[] array.
         * int org - size of the char[] array that never changes.
         * char[] chararray - an array of characters that make uo the word.
         **/
        if (x <= 0) {
            return;
        } else {
            evenTriangle(x - 1, org, chararray);
        }
        for (int i = 0; i < (org - x); i++) {
            System.out.print("  ");
        }
        if (x < chararray.length / 1.5) {
            for (int i = 0; i < x; i++) {
                System.out.print(chararray[i] + " ");
            }
            if (x >= 1)
                printreverse(x, chararray);
            System.out.print("\n");
        }
    }//evenTriangle

    public static void printreverse(int x, char[] chararray) {
        /** PURPOSE: This method will take the char[] array and its number of characters to print make an appropriate copy
         * of the needed characters and then print form the copy array.
         *
         * int x - number  of characters to print.
         * char[] chararray - an array of characters that make uo the word.
         **/
        char[] newarray = new char[x];
        int d = x;
        for (int i = 0; i < x; i++) {
            newarray[i] = chararray[chararray.length - d];
            d--;
        }
        for (int i = 0; i < newarray.length; i++) {
            System.out.print(newarray[i] + " ");
        }
    }//printreverse

    public static boolean checkEven(int num) {
        /** PURPOSE: This method will take a number and determine whether its even or odd and will help to choose the
         * appropriate method to choose.
         *
         * int x - number to check.
         **/
        boolean result = false;
        if (num % 2 == 0) {
            result = true;
        }
        return result;
    }//checkEven


}