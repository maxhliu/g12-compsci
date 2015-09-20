/*
 * Mister Lim
 * ICS4U1-1
 * 7 March 2014
 * Liu_Max_Project5
 */
package project5;

import java.util.Scanner;

/**
 * S3
 */
public class S3 {
//    pentant = 1/5 of something; similar to quadrant or sextant
//    for reference: these are the pentants where there either are or might be crystal
//    int[][] crystal = {{1, 0}, {2, 0}, {3, 0}, {2, 1}};
//    int[][] maybeCrystal = {{1, 1}, {2, 2}, {3, 1}};

    static boolean search(int m, int x, int y) {
        //determine the pentant of each if m > 1
        //the Math.round is necessary to prevent floating point precision errors
        //this works even when m == 1 because Math.pow(5, 1-1) = 1
        //int this case xPent = x and yPent = y
        int xPent = (int) (x / Math.round(Math.pow(5, m - 1)));
        int yPent = (int) (y / Math.round(Math.pow(5, m - 1)));
        //check if the co-ordinates match one of these pentants guaranteed to have crystal: {{1, 0}, {2, 0}, {3, 0}, {2, 1}}
        if ((yPent == 0 && xPent >= 1 && xPent <= 3) || (xPent == 2 && yPent == 1)) {
            //return true; there is definitely crystal here.
            return true;
        }
        //check if the co-ordinates match one of these pentants which may contain crystal: {{1, 1}, {2, 2}, {3, 1}}
        if (((xPent == yPent && xPent >= 1 && yPent <= 2) || (xPent == 3 && yPent == 1)) && m > 1) {
            //if so, get the co-ordinates of the co-ordinate within its pentant and use a recursive call to model it
            return search(m - 1, x - xPent * (int) (Math.round(Math.pow(5, m - 1))), y - yPent * (int) (Math.round(Math.pow(5, m - 1))));
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int lines = sc.nextInt();
        //print out crystal or empty
        for (int i = 0; i < lines; i++) {
            if (search(sc.nextInt(), sc.nextInt(), sc.nextInt())) {
                System.out.println("crystal");
            } else {
                System.out.println("empty");
            }
        }
    }
}
