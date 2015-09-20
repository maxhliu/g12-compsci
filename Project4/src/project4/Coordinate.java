/*
 * A wrapper class for a three-dimensional co-ordinate. This could have been replaced with a three-digit number, but I felt that it was more clear this way.
 */
package project4;

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

    Coordinate(int abc) {
        c = abc % 10;
        abc /= 10;
        b = abc % 10;
        abc /= 10;
        a = abc;
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
//set all three values at once
    public void set(int abc) {
        c = abc % 10;
        abc /= 10;
        b = abc % 10;
        abc /= 10;
        a = abc;
    }
//compare this coordinate with another
    public boolean compare(Coordinate a) {
        return (a.getA() == this.a && a.getB() == this.b && a.getC() == this.c);
    }
    //find out which layer of the cube the coordinate is in. Easy for a human, but annoying to program
    public int layer() {
        int layer = 0;
        //1 for top, 2 for middle, 3 for bottom
        //I wrote out all the coordinates in the top layer into a string in the constants class
        char[] topLayer = Constants.topLayer.toCharArray();
        //parse every three characters into a coordinate since i didnt put spaces
        for (int i = 2; i < 63; i += 3) {
            if (this.compare(new Coordinate(topLayer[i - 2] - '0', topLayer[i - 1] - '0', topLayer[i] - '0'))) {
                layer = 1;
            }
        }
        //rinse and repeat for the second layer
        char[] middleLayer = Constants.middleLayer.toCharArray();
        for (int i = 2; i < 36; i += 3) {
            if (this.compare(new Coordinate(middleLayer[i - 2] - '0', middleLayer[i - 1] - '0', middleLayer[i] - '0'))) {
                layer = 2;
            }
        }
        //check for the third layer too
        char[] bottomLayer = Constants.bottomLayer.toCharArray();
        for (int i = 2; i < 63; i += 3) {
            if (this.compare(new Coordinate(bottomLayer[i - 2] - '0', bottomLayer[i - 1] - '0', bottomLayer[i] - '0'))) {
                layer = 3;
            }
        }
        return layer;
    }

    //set the coordinate values as the parameter
    //it is not possible to use the = operator here because that would change the pointer
    public void set(Coordinate code) {
        this.a = code.a;
        this.b = code.b;
        this.c = code.c;
    }
}
