package project3;

public class Cube {

    static char[][][] cube;

    Cube() {
        cube = new char[6][3][3];
    }

//    change the color of a face on the cube
    static void input(Coordinate a, char colour) {
        cube[a.getA()][a.getB()][a.getC()] = colour;
    }
// rotate a set of four points

    static void rotate(Coordinate a, Coordinate b, Coordinate c, Coordinate d) {
        //this is done by storing the first in a a temporary varialbe
        char temp = cube[a.getA()][a.getB()][a.getC()];
        //moving each to the next
        cube[a.getA()][a.getB()][a.getC()] = cube[b.getA()][b.getB()][b.getC()];
        cube[b.getA()][b.getB()][b.getC()] = cube[c.getA()][c.getB()][c.getC()];
        cube[c.getA()][c.getB()][c.getC()] = cube[d.getA()][d.getB()][d.getC()];
        //and finally replacing the last with the value of the temporary variable
        cube[d.getA()][d.getB()][d.getC()] = temp;
    }

    //for simplicity's sake, rotate three times instead of rotating backwards
    //this is the same action because a square has six sides
    static void antirotate(Coordinate a, Coordinate b, Coordinate c, Coordinate d) {
        rotate(a, b, c, d);
        rotate(a, b, c, d);
        rotate(a, b, c, d);
    }

    //rotate a face
    static void rotateFace(int n) {
        //this needs to be done twice because the following code only moves one block
        for (int i = 0; i < 2; i++) {
            //in a similar manner to rotate(), move each face along.
            char temp = cube[n][0][0];
            cube[n][0][0] = cube[n][0][1];
            cube[n][0][1] = cube[n][0][2];
            cube[n][0][2] = cube[n][1][2];
            cube[n][1][2] = cube[n][2][2];
            cube[n][2][2] = cube[n][2][1];
            cube[n][2][1] = cube[n][2][0];
            cube[n][2][0] = cube[n][1][0];
            cube[n][1][0] = temp;
        }
    }

    //similarly, rotate three times instead of rotating the other way
    static void antirotateFace(int n) {
        rotateFace(n);
        rotateFace(n);
        rotateFace(n);
    }

    //print the cube net
    //care must be taken so that the faces 1-3 are printed properly as they are all in a line
    static void print() {
        printFace(0, 6);
        printThreeFaces(1);
        printFace(4, 6);
        printFace(5, 6);
    }

    //print out a single face
    static void printFace(int i, int indent) {
        //print out each row at a time
        for (int b = 0; b < 3; b++) {
            //print out a specified indent before the face so that it aligns properly with the other faces
            for (int a = 0; a < indent; a++) {
                System.out.print(" ");
            }
            //print out the actual face row, inserting spaces as necessary
            for (int c = 0; c < 3; c++) {
                System.out.print(cube[i][b][c] + " ");
            }
            //line break
            System.out.println("");
        }
    }

    //print three faces at once
    //this is different because output stream can only print horizontally; the first row of each must be printed before the second row, etc.
    static void printThreeFaces(int i) {
        //print each row at once
        for (int b = 0; b < 3; b++) {
            //print the row of the first face
            for (int c = 0; c < 3; c++) {
                System.out.print(cube[i][b][c] + " ");
            }
            //print the row of the second face
            for (int c = 0; c < 3; c++) {
                System.out.print(cube[i + 1][b][c] + " ");
            }
            //print the row of the third face
            for (int c = 0; c < 3; c++) {
                System.out.print(cube[i + 2][b][c] + " ");
            }
            System.out.println("");
        }
    }

    //move the cube according to a modified move notation.
    //instead of using a move such as L', it is converted to l so that the move code is only a single character.
    //only the five moves actually used in physical solving of the rubik's cube have been included
    //this is because solving algorithms will not use other moves as they are hard to perform in real life
    //for code reference, see the code reference pdf attached
    //see http://ruwix.com/the-rubiks-cube/notation/ for rubik's cube notation
    static void move(char code) {
        switch (code) {
            //instead of rotating something the other way, I have instead rotated it three times
            //this eliminates chances for mistakes
            case 'L':
                rotate(new Coordinate(0, 0, 0), new Coordinate(2, 0, 0), new Coordinate(4, 0, 0), new Coordinate(5, 0, 0));
                rotate(new Coordinate(0, 1, 0), new Coordinate(2, 1, 0), new Coordinate(4, 1, 0), new Coordinate(5, 1, 0));
                rotate(new Coordinate(0, 2, 0), new Coordinate(2, 2, 0), new Coordinate(4, 2, 0), new Coordinate(5, 2, 0));
                rotateFace(1);
                rotate(new Coordinate(0, 0, 0), new Coordinate(2, 0, 0), new Coordinate(4, 0, 0), new Coordinate(5, 0, 0));
                rotate(new Coordinate(0, 1, 0), new Coordinate(2, 1, 0), new Coordinate(4, 1, 0), new Coordinate(5, 1, 0));
                rotate(new Coordinate(0, 2, 0), new Coordinate(2, 2, 0), new Coordinate(4, 2, 0), new Coordinate(5, 2, 0));
                rotateFace(1);
            case 'l':
                rotate(new Coordinate(0, 0, 0), new Coordinate(2, 0, 0), new Coordinate(4, 0, 0), new Coordinate(5, 0, 0));
                rotate(new Coordinate(0, 1, 0), new Coordinate(2, 1, 0), new Coordinate(4, 1, 0), new Coordinate(5, 1, 0));
                rotate(new Coordinate(0, 2, 0), new Coordinate(2, 2, 0), new Coordinate(4, 2, 0), new Coordinate(5, 2, 0));
                rotateFace(1);
                break;
            case 'r':
                rotate(new Coordinate(0, 0, 2), new Coordinate(2, 0, 2), new Coordinate(4, 0, 2), new Coordinate(5, 0, 2));
                rotate(new Coordinate(0, 1, 2), new Coordinate(2, 1, 2), new Coordinate(4, 1, 2), new Coordinate(5, 1, 2));
                rotate(new Coordinate(0, 2, 2), new Coordinate(2, 2, 2), new Coordinate(4, 2, 2), new Coordinate(5, 2, 2));
                antirotateFace(3);
                rotate(new Coordinate(0, 0, 2), new Coordinate(2, 0, 2), new Coordinate(4, 0, 2), new Coordinate(5, 0, 2));
                rotate(new Coordinate(0, 1, 2), new Coordinate(2, 1, 2), new Coordinate(4, 1, 2), new Coordinate(5, 1, 2));
                rotate(new Coordinate(0, 2, 2), new Coordinate(2, 2, 2), new Coordinate(4, 2, 2), new Coordinate(5, 2, 2));
                antirotateFace(3);
            case 'R':
                rotate(new Coordinate(0, 0, 2), new Coordinate(2, 0, 2), new Coordinate(4, 0, 2), new Coordinate(5, 0, 2));
                rotate(new Coordinate(0, 1, 2), new Coordinate(2, 1, 2), new Coordinate(4, 1, 2), new Coordinate(5, 1, 2));
                rotate(new Coordinate(0, 2, 2), new Coordinate(2, 2, 2), new Coordinate(4, 2, 2), new Coordinate(5, 2, 2));
                antirotateFace(3);
                break;
            case 'u':
                rotate(new Coordinate(0, 2, 0), new Coordinate(1, 2, 2), new Coordinate(4, 0, 2), new Coordinate(3, 0, 0));
                rotate(new Coordinate(0, 2, 1), new Coordinate(1, 1, 2), new Coordinate(4, 0, 1), new Coordinate(3, 1, 0));
                rotate(new Coordinate(0, 2, 2), new Coordinate(1, 0, 2), new Coordinate(4, 0, 0), new Coordinate(3, 2, 0));
                antirotateFace(2);
                rotate(new Coordinate(0, 2, 0), new Coordinate(1, 2, 2), new Coordinate(4, 0, 2), new Coordinate(3, 0, 0));
                rotate(new Coordinate(0, 2, 1), new Coordinate(1, 1, 2), new Coordinate(4, 0, 1), new Coordinate(3, 1, 0));
                rotate(new Coordinate(0, 2, 2), new Coordinate(1, 0, 2), new Coordinate(4, 0, 0), new Coordinate(3, 2, 0));
                antirotateFace(2);
            case 'U':
                rotate(new Coordinate(0, 2, 0), new Coordinate(1, 2, 2), new Coordinate(4, 0, 2), new Coordinate(3, 0, 0));
                rotate(new Coordinate(0, 2, 1), new Coordinate(1, 1, 2), new Coordinate(4, 0, 1), new Coordinate(3, 1, 0));
                rotate(new Coordinate(0, 2, 2), new Coordinate(1, 0, 2), new Coordinate(4, 0, 0), new Coordinate(3, 2, 0));
                antirotateFace(2);
                break;
            case 'D':
                rotate(new Coordinate(1, 2, 0), new Coordinate(4, 2, 2), new Coordinate(3, 0, 2), new Coordinate(0, 0, 0));
                rotate(new Coordinate(1, 1, 0), new Coordinate(4, 2, 1), new Coordinate(3, 1, 2), new Coordinate(0, 0, 1));
                rotate(new Coordinate(1, 0, 0), new Coordinate(4, 2, 0), new Coordinate(3, 2, 2), new Coordinate(0, 0, 2));
                rotateFace(5);
                rotate(new Coordinate(1, 2, 0), new Coordinate(4, 2, 2), new Coordinate(3, 0, 2), new Coordinate(0, 0, 0));
                rotate(new Coordinate(1, 1, 0), new Coordinate(4, 2, 1), new Coordinate(3, 1, 2), new Coordinate(0, 0, 1));
                rotate(new Coordinate(1, 0, 0), new Coordinate(4, 2, 0), new Coordinate(3, 2, 2), new Coordinate(0, 0, 2));
                rotateFace(5);
            case 'd':
                rotate(new Coordinate(1, 2, 0), new Coordinate(4, 2, 2), new Coordinate(3, 0, 2), new Coordinate(0, 0, 0));
                rotate(new Coordinate(1, 1, 0), new Coordinate(4, 2, 1), new Coordinate(3, 1, 2), new Coordinate(0, 0, 1));
                rotate(new Coordinate(1, 0, 0), new Coordinate(4, 2, 0), new Coordinate(3, 2, 2), new Coordinate(0, 0, 2));
                rotateFace(5);
                break;
            case 'f':
                rotate(new Coordinate(1, 2, 0), new Coordinate(2, 2, 0), new Coordinate(3, 2, 0), new Coordinate(5, 0, 2));
                rotate(new Coordinate(1, 2, 1), new Coordinate(2, 2, 1), new Coordinate(3, 2, 1), new Coordinate(5, 0, 1));
                rotate(new Coordinate(1, 2, 2), new Coordinate(2, 2, 2), new Coordinate(3, 2, 2), new Coordinate(5, 0, 0));
                antirotateFace(4);
                rotate(new Coordinate(1, 2, 0), new Coordinate(2, 2, 0), new Coordinate(3, 2, 0), new Coordinate(5, 0, 2));
                rotate(new Coordinate(1, 2, 1), new Coordinate(2, 2, 1), new Coordinate(3, 2, 1), new Coordinate(5, 0, 1));
                rotate(new Coordinate(1, 2, 2), new Coordinate(2, 2, 2), new Coordinate(3, 2, 2), new Coordinate(5, 0, 0));
                antirotateFace(4);
            case 'F':
                rotate(new Coordinate(1, 2, 0), new Coordinate(2, 2, 0), new Coordinate(3, 2, 0), new Coordinate(5, 0, 2));
                rotate(new Coordinate(1, 2, 1), new Coordinate(2, 2, 1), new Coordinate(3, 2, 1), new Coordinate(5, 0, 1));
                rotate(new Coordinate(1, 2, 2), new Coordinate(2, 2, 2), new Coordinate(3, 2, 2), new Coordinate(5, 0, 0));
                antirotateFace(4);
                break;

        }
    }
}
