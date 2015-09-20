/*
 * Mister Lim
 * ICS4U1-1
 * 19 December 2013
 * Liu_Max_Project3
 */
package project3;

import java.util.Scanner;

/**
 * This project constructs a virtual rubik's cube and allows you to perform
 * operations on it.
 */
public class Project3 {

    /**
     * see code lookup table for explanation of rubik's cube face co-ordinates,
     * instructions for input for a sample rubik's cube in net form.
     */
    public static void main(String[] args) {
        //The cube will be a three dimensional array; one dimension for each face, and two dimensions for the rows and columns on a face.
        Cube.cube = new char[6][3][3];
        Scanner s = new Scanner(System.in);
//        This is the way the cube has been set up. The origin of the net is the top, and the other sides are folded outwards.
        System.out.println("Please enter your cube colours in net form:");
        System.out.println("      1 2 3");
        System.out.println("      4 5 6");
        System.out.println("      7 8 9");
        System.out.println("1 2 3 1 2 3 1 2 3");
        System.out.println("4 5 6 4 5 6 4 5 6");
        System.out.println("7 8 9 7 8 9 7 8 9");
        System.out.println("      1 2 3");
        System.out.println("      4 5 6");
        System.out.println("      7 8 9");
        System.out.println("      1 2 3");
        System.out.println("      4 5 6");
        System.out.println("      7 8 9");
        System.out.println("Enter the face according to the diagram using left-right-down notation.");

//        read the first face of input
        for (int b = 0; b < 3; b++) {
            for (int c = 0; c < 3; c++) {
                Cube.input(new Coordinate(0, b, c), s.next().toCharArray()[0]);
            }
        }

//        read the next three faces of input. This must be done all at once because the scanner reads left to right, and there are three faces in a row here.
        for (int b = 0; b < 3; b++) {
            for (int c = 0; c < 3; c++) {
                Cube.input(new Coordinate(1, b, c), s.next().toCharArray()[0]);
            }
            for (int c = 0; c < 3; c++) {
                Cube.input(new Coordinate(2, b, c), s.next().toCharArray()[0]);
            }
            for (int c = 0; c < 3; c++) {
                Cube.input(new Coordinate(3, b, c), s.next().toCharArray()[0]);
            }
        }

//        read the 5th face
        for (int b = 0; b < 3; b++) {
            for (int c = 0; c < 3; c++) {
                Cube.input(new Coordinate(4, b, c), s.next().toCharArray()[0]);
            }
        }

//        read the last face
        for (int b = 0; b < 3; b++) {
            for (int c = 0; c < 3; c++) {
                Cube.input(new Coordinate(5, b, c), s.next().toCharArray()[0]);
            }
        }

//        enter into the main loop; print out the cube after every action.
        while (true) {
            Cube.move(s.next().charAt(0));
            Cube.print();
        }
    }
}
