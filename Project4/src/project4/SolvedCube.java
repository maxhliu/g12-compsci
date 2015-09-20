


//this is a slighty modified copycat class of Cube
//it is used to access a solved version of the cube for finding the correct location of pieces




package project4;

import java.util.ArrayList;
import java.util.Scanner;

public class SolvedCube {

    static char[][][] cube = solvedCube();

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
            case 'F':
                rotate(new Coordinate(1, 2, 0), new Coordinate(2, 2, 0), new Coordinate(3, 2, 0), new Coordinate(5, 0, 2));
                rotate(new Coordinate(1, 2, 1), new Coordinate(2, 2, 1), new Coordinate(3, 2, 1), new Coordinate(5, 0, 1));
                rotate(new Coordinate(1, 2, 2), new Coordinate(2, 2, 2), new Coordinate(3, 2, 2), new Coordinate(5, 0, 0));
                rotateFace(4);
                rotate(new Coordinate(1, 2, 0), new Coordinate(2, 2, 0), new Coordinate(3, 2, 0), new Coordinate(5, 0, 2));
                rotate(new Coordinate(1, 2, 1), new Coordinate(2, 2, 1), new Coordinate(3, 2, 1), new Coordinate(5, 0, 1));
                rotate(new Coordinate(1, 2, 2), new Coordinate(2, 2, 2), new Coordinate(3, 2, 2), new Coordinate(5, 0, 0));
                rotateFace(4);
            case 'f':
                rotate(new Coordinate(1, 2, 0), new Coordinate(2, 2, 0), new Coordinate(3, 2, 0), new Coordinate(5, 0, 2));
                rotate(new Coordinate(1, 2, 1), new Coordinate(2, 2, 1), new Coordinate(3, 2, 1), new Coordinate(5, 0, 1));
                rotate(new Coordinate(1, 2, 2), new Coordinate(2, 2, 2), new Coordinate(3, 2, 2), new Coordinate(5, 0, 0));
                rotateFace(4);
                break;
            case 'b':
                rotate(new Coordinate(1, 0, 0), new Coordinate(2, 0, 0), new Coordinate(3, 0, 0), new Coordinate(5, 2, 2));
                rotate(new Coordinate(1, 0, 1), new Coordinate(2, 0, 1), new Coordinate(3, 0, 1), new Coordinate(5, 2, 1));
                rotate(new Coordinate(1, 0, 2), new Coordinate(2, 0, 2), new Coordinate(3, 0, 2), new Coordinate(5, 2, 0));
                antirotateFace(0);
                rotate(new Coordinate(1, 0, 0), new Coordinate(2, 0, 0), new Coordinate(3, 0, 0), new Coordinate(5, 2, 2));
                rotate(new Coordinate(1, 0, 1), new Coordinate(2, 0, 1), new Coordinate(3, 0, 1), new Coordinate(5, 2, 1));
                rotate(new Coordinate(1, 0, 2), new Coordinate(2, 0, 2), new Coordinate(3, 0, 2), new Coordinate(5, 2, 0));
                antirotateFace(0);
            case 'B':
                rotate(new Coordinate(1, 0, 0), new Coordinate(2, 0, 0), new Coordinate(3, 0, 0), new Coordinate(5, 2, 2));
                rotate(new Coordinate(1, 0, 1), new Coordinate(2, 0, 1), new Coordinate(3, 0, 1), new Coordinate(5, 2, 1));
                rotate(new Coordinate(1, 0, 2), new Coordinate(2, 0, 2), new Coordinate(3, 0, 2), new Coordinate(5, 2, 0));
                antirotateFace(0);
                break;
            case 'M':
                rotate(new Coordinate(0, 0, 1), new Coordinate(2, 0, 1), new Coordinate(4, 0, 1), new Coordinate(5, 0, 1));
                rotate(new Coordinate(0, 1, 1), new Coordinate(2, 1, 1), new Coordinate(4, 1, 1), new Coordinate(5, 1, 1));
                rotate(new Coordinate(0, 2, 1), new Coordinate(2, 2, 1), new Coordinate(4, 2, 1), new Coordinate(5, 2, 1));
                rotate(new Coordinate(0, 0, 1), new Coordinate(2, 0, 1), new Coordinate(4, 0, 1), new Coordinate(5, 0, 1));
                rotate(new Coordinate(0, 1, 1), new Coordinate(2, 1, 1), new Coordinate(4, 1, 1), new Coordinate(5, 1, 1));
                rotate(new Coordinate(0, 2, 1), new Coordinate(2, 2, 1), new Coordinate(4, 2, 1), new Coordinate(5, 2, 1));
            case 'm':
                rotate(new Coordinate(0, 0, 1), new Coordinate(2, 0, 1), new Coordinate(4, 0, 1), new Coordinate(5, 0, 1));
                rotate(new Coordinate(0, 1, 1), new Coordinate(2, 1, 1), new Coordinate(4, 1, 1), new Coordinate(5, 1, 1));
                rotate(new Coordinate(0, 2, 1), new Coordinate(2, 2, 1), new Coordinate(4, 2, 1), new Coordinate(5, 2, 1));
                break;
            case 'E':
                rotate(new Coordinate(0, 1, 0), new Coordinate(1, 2, 1), new Coordinate(4, 1, 2), new Coordinate(3, 0, 1));
                rotate(new Coordinate(0, 1, 1), new Coordinate(1, 1, 1), new Coordinate(4, 1, 1), new Coordinate(3, 1, 1));
                rotate(new Coordinate(0, 1, 2), new Coordinate(1, 0, 1), new Coordinate(4, 1, 0), new Coordinate(3, 2, 1));
                rotate(new Coordinate(0, 1, 0), new Coordinate(1, 2, 1), new Coordinate(4, 1, 2), new Coordinate(3, 0, 1));
                rotate(new Coordinate(0, 1, 1), new Coordinate(1, 1, 1), new Coordinate(4, 1, 1), new Coordinate(3, 1, 1));
                rotate(new Coordinate(0, 1, 2), new Coordinate(1, 0, 1), new Coordinate(4, 1, 0), new Coordinate(3, 2, 1));
            case 'e':
                rotate(new Coordinate(0, 1, 0), new Coordinate(1, 2, 1), new Coordinate(4, 1, 2), new Coordinate(3, 0, 1));
                rotate(new Coordinate(0, 1, 1), new Coordinate(1, 1, 1), new Coordinate(4, 1, 1), new Coordinate(3, 1, 1));
                rotate(new Coordinate(0, 1, 2), new Coordinate(1, 0, 1), new Coordinate(4, 1, 0), new Coordinate(3, 2, 1));
                break;
            case 'S':
                rotate(new Coordinate(1, 1, 0), new Coordinate(2, 1, 0), new Coordinate(3, 1, 0), new Coordinate(5, 1, 2));
                rotate(new Coordinate(1, 1, 1), new Coordinate(2, 1, 1), new Coordinate(3, 1, 1), new Coordinate(5, 1, 1));
                rotate(new Coordinate(1, 1, 2), new Coordinate(2, 1, 2), new Coordinate(3, 1, 2), new Coordinate(5, 1, 0));
                rotate(new Coordinate(1, 1, 0), new Coordinate(2, 1, 0), new Coordinate(3, 1, 0), new Coordinate(5, 1, 2));
                rotate(new Coordinate(1, 1, 1), new Coordinate(2, 1, 1), new Coordinate(3, 1, 1), new Coordinate(5, 1, 1));
                rotate(new Coordinate(1, 1, 2), new Coordinate(2, 1, 2), new Coordinate(3, 1, 2), new Coordinate(5, 1, 0));
            case 's':
                rotate(new Coordinate(1, 1, 0), new Coordinate(2, 1, 0), new Coordinate(3, 1, 0), new Coordinate(5, 1, 2));
                rotate(new Coordinate(1, 1, 1), new Coordinate(2, 1, 1), new Coordinate(3, 1, 1), new Coordinate(5, 1, 1));
                rotate(new Coordinate(1, 1, 2), new Coordinate(2, 1, 2), new Coordinate(3, 1, 2), new Coordinate(5, 1, 0));
                break;
            case 'x':
                move('m');
                move('l');
                move('R');
                move('m');
                move('l');
                move('R');
            case 'X':
                move('m');
                move('l');
                move('R');
                break;
            case 'y':
                move('U');
                move('e');
                move('d');
                move('U');
                move('e');
                move('d');
            case 'Y':
                move('U');
                move('e');
                move('d');
                break;
            case 'z':
                move('F');
                move('S');
                move('b');
                move('F');
                move('S');
                move('b');
            case 'Z':
                move('F');
                move('S');
                move('b');
                break;
            case '0':
                for (int i = 0; i < 200; i++) {
                    scramble((int) (Math.random() * 11) - 5);
                }
                break;
            case '1':
                solveEdges();
                break;
            case '2':
                solveCorners();
                break;
            default:
                break;
        }
    }

    static void scramble(int i) {
        switch (i) {
            case 1:
                Cube.move('L');
                break;
            case -1:
                Cube.move('l');
                break;
            case 2:
                Cube.move('R');
                break;
            case -2:
                Cube.move('r');
                break;
            case 3:
                Cube.move('U');
                break;
            case -3:
                Cube.move('u');
                break;
            case 4:
                Cube.move('D');
                break;
            case -4:
                Cube.move('d');
                break;
            case 5:
                Cube.move('F');
                break;
            case -5:
                Cube.move('f');
                break;
            default:
                break;
        }
    }

    static void moveSequence(String alg) {
        char[] moves = alg.toCharArray();
        for (char c : moves) {
            move(c);
        }
    }

    static Corner getCorner(Coordinate piece) {
        Scanner s = new Scanner(Constants.cornerSets);
        Corner theCorner = null;
        for (int i = 0; i < 8; i++) {
            String input = s.nextLine();
            char[] nums = input.toCharArray();
            Coordinate a = new Coordinate(nums[0] - '0', nums[1] - '0', nums[2] - '0');
            Coordinate b = new Coordinate(nums[3] - '0', nums[4] - '0', nums[5] - '0');
            Coordinate c = new Coordinate(nums[6] - '0', nums[7] - '0', nums[8] - '0');
            if (piece.compare(a) || piece.compare(b) || piece.compare(c)) {
                theCorner = new Corner(new Sticker(a, getValue(a)), new Sticker(b, getValue(b)), new Sticker(c, getValue(c)));
            }
        }
        return theCorner;
    }

    static Edge getEdge(Coordinate piece) {
        Scanner s = new Scanner(Constants.edgePairs);
        Edge theEdge = null;
        for (int i = 0; i < 12; i++) {
            String input = s.nextLine();
            char[] nums = input.toCharArray();
            Coordinate a = new Coordinate(nums[0] - '0', nums[1] - '0', nums[2] - '0');
            Coordinate b = new Coordinate(nums[3] - '0', nums[4] - '0', nums[5] - '0');
            if (piece.compare(a) || piece.compare(b)) {
                theEdge = new Edge(new Sticker(a, getValue(a)), new Sticker(b, getValue(b)));
            }
        }
        return theEdge;
    }

    static Corner getCorner(Sticker s) {
        return getCorner(s.getLocation());
    }

    static Edge getEdge(Sticker s) {
        return getEdge(s.getLocation());
    }

    static ArrayList<Corner> getWhiteCorners() {
        ArrayList<Sticker> stickers = new ArrayList<>(0);
        for (int a = 0; a < 6; a++) {
            for (int b = 0; b < 3; b += 2) {
                for (int c = 0; c < 3; c += 2) {
                    if (cube[a][b][c] == 'w') {
                        stickers.add(new Sticker(new Coordinate(a, b, c), 'w'));
                    }
                }
            }
        }
        ArrayList<Corner> corners = new ArrayList<>(0);
        for (Sticker s : stickers) {
            corners.add(getCorner(s));
        }
        return corners;
    }

    static ArrayList<Edge> getWhiteEdges() {
        ArrayList<Sticker> stickers = new ArrayList<>(0);
        for (int a = 0; a < 6; a++) {
            if (cube[a][1][0] == 'w') {
                stickers.add(new Sticker(new Coordinate(a, 1, 0), 'w'));
            }
            if (cube[a][1][2] == 'w') {
                stickers.add(new Sticker(new Coordinate(a, 1, 2), 'w'));
            }
            if (cube[a][0][1] == 'w') {
                stickers.add(new Sticker(new Coordinate(a, 0, 1), 'w'));
            }
            if (cube[a][2][1] == 'w') {
                stickers.add(new Sticker(new Coordinate(a, 2, 1), 'w'));
            }
        }
        ArrayList<Edge> edges = new ArrayList<>(0);
        for (Sticker s : stickers) {
            edges.add(getEdge(s));
        }
        return edges;
    }

    static void solveCorners() {
        ArrayList<Corner> corners = SolvedCube.getWhiteCorners();
        for (Corner corner : corners) {
            solveCornersAlgs(corner);
        }
    }

    static void solveCornersAlgs(Corner corner) {
//        Coordinate stickerLocation = corner.update().findValue('w');
        switch (corner.update().findValue('w').layer()) {
            case 1:
                while (!(corner.update().compare(getCorner(new Coordinate(2, 0, 2))))) {
                    move('y');
                }
                moveSequence("rDR");
                solveCornersAlgs(corner.update());
                break;
            case 2:
                throw new IllegalArgumentException("You cannot have corners on the second layer.");
            //no break because netbeans throws an error
            case 3:
                spinPositionSide(corner.update());
                while (!(corner.update().compare(getCorner(new Coordinate(2, 2, 2))))) {
                    move('y');
                }
                if (corner.update().findValue('w').getA() == 5) {
                    //bottom face
                    moveSequence("rDRdFdf");
                } else {
                    if (corner.update().findValue('w').getA() == 4) {
                        //front face
                        moveSequence("drDR");
                    } else {
                        //right face
                        moveSequence("rdRD");
                    }
                }
                break;
        }
    }

    static void solveEdges() {
        ArrayList<Edge> edges = SolvedCube.getWhiteEdges();
        for (Edge edge : edges) {
            solveEdgesAlgs(edge);
        }
    }

    static void solveEdgesAlgs(Edge edge) {
//        Coordinate stickerLocation = edge.update().findValue('w');
        switch (edge.update().findValue('w').layer()) {
            case 1:
                while (!(edge.update().compare(getEdge(new Coordinate(2, 2, 1))))) {
                    move('y');
                }
                move('f');
                solveEdgesAlgs(edge.update());
                break;
            case 2:
                while (!(edge.update().compare(getEdge(new Coordinate(4, 1, 2))))) {
                    move('y');
                }
                spinPositionSideEdge(edge.update());
                break;
            case 3:
                while (!(edge.update().compare(getEdge(new Coordinate(4, 2, 1))))) {
                    move('y');
                }
                char[][][] currentCube = cube;
                cube = solvedCube();
                if (edge.compare(getEdge(new Coordinate(2, 0, 1)))) {
                    moveSequence("DD");
                    cube = currentCube;
                } else if (edge.compare(getEdge(new Coordinate(2, 1, 0)))) {
                    move('d');
                    cube = currentCube;
                } else if (edge.compare(getEdge(new Coordinate(2, 1, 2)))) {
                    move('D');
                    cube = currentCube;
                } else if (edge.compare(getEdge(new Coordinate(2, 2, 1)))) {
                    cube = currentCube;
                    //do nothing; it is in the right position
                } else {
                    cube = currentCube;
                    throw new IllegalArgumentException("This is not a valid edge.");
                }
                while (!(edge.update().compare(getEdge(new Coordinate(4, 2, 1))))) {
                    move('y');
                }
                if (edge.update().findValue('w').getA() == 4) {
                    moveSequence("FUlu");
                } else {
                    moveSequence("FF");
                }
                break;
        }
    }

    static void spinPositionSideEdge(Edge edge) {
        char[][][] currentCube = cube;
        cube = solvedCube();
        //201 210 212 221
        int spins = Integer.MAX_VALUE;
        if (edge.compare(getEdge(new Coordinate(2, 0, 1)))) {
            cube = currentCube;
            spins = 2;
//            moveSequence("UU");
        } else if (edge.compare(getEdge(new Coordinate(2, 1, 0)))) {
            cube = currentCube;
            spins = -1;
//            move('u');
        } else if (edge.compare(getEdge(new Coordinate(2, 1, 2)))) {
            cube = currentCube;
            spins = 1;
//            move('U');
        } else if (edge.compare(getEdge(new Coordinate(2, 2, 1)))) {
            cube = currentCube;
            spins = 0;
            //do nothing; it is in the right position
        } else {
            cube = currentCube;
            throw new IllegalArgumentException("This is not a valid edge.");
        }
        if (edge.update().findValue('w').getA() == 4) {
            spins--;
        }
        switch (spins) {
            case 2:
            case -2:
                moveSequence("UU");
                break;
            case -1:
                move('u');
                break;
            case 0:
                break;
            case 1:
                move('U');
        }
        if (edge.update().findValue('w').getA() == 4) {
            move('R');
        } else {
            move('f');
        }
        switch (spins) {
            case 2:
            case -2:
                moveSequence("uu");
                break;
            case -1:
                move('U');
                break;
            case 0:
                break;
            case 1:
                move('u');
        }
    }

    static void spinPositionSide(Corner corner) {
        char[][][] currentCube = cube;
        cube = solvedCube();
        
        if (corner.compare(getCorner(new Coordinate(2, 0, 0)))) {
            cube = currentCube;
            moveSequence("ddyy");
        } else if (corner.compare(getCorner(new Coordinate(2, 2, 0)))) {
            cube = currentCube;
            moveSequence("dy");
        } else if (corner.compare(getCorner(new Coordinate(2, 2, 2)))) {
            cube = currentCube;
            //do nothing, it is in the correct position
        } else if (corner.compare(getCorner(new Coordinate(2, 0, 2)))) {
            cube = currentCube;
            moveSequence("DY");
        } else {
            cube = currentCube;
            throw new IllegalArgumentException("This is not a valid corner.");
        }

    }

    static char getValue(Coordinate a) {
        return SolvedCube.cube[a.getA()][a.getB()][a.getC()];
    }

    static char[][][] solvedCube() {
        //create a solved cube
        char[][][] solvedCube = new char[6][3][3];
        String colorSequence = "rbwgoy";
        for (int a = 0; a < 6; a++) {
            for (int b = 0; b < 3; b++) {
                for (int c = 0; c < 3; c++) {
                    solvedCube[a][b][c] = colorSequence.charAt(a);
                }
            }
        }
        return solvedCube;
    }
}
