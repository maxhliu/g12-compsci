/*
 * A wrapper class for a three-dimensional co-ordinate. This could have been replaced with a three-digit number, but I felt that it was more clear this way.
 */
package project3;

/**
 *
 * @author Max
 */
public class Coordinate {

    //have three integer variables to store the x,y,and z values.
    public int a;
    public int b;
    public int c;

    Coordinate(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * @return the a
     */
    public int getA() {
        return a;
    }

    /**
     * @param a the a to set
     */
    public void setA(int a) {
        this.a = a;
    }

    /**
     * @return the b
     */
    public int getB() {
        return b;
    }

    /**
     * @param b the b to set
     */
    public void setB(int b) {
        this.b = b;
    }

    /**
     * @return the c
     */
    public int getC() {
        return c;
    }

    /**
     * @param c the c to set
     */
    public void setC(int c) {
        this.c = c;
    }
}
