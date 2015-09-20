/*
 * The corner class is a collection of three "stickers"; These move together as a group
 */
package project4;

import java.util.ArrayList;

/**
 *
 * @author Max
 */
public class Corner {

    private Sticker a;
    private Sticker b;
    private Sticker c;

    Corner(Sticker a, Sticker b, Sticker c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * @return the a
     */
    public Sticker getA() {
        return a;
    }

    /**
     * @param a the a to set
     */
    public void setA(Sticker a) {
        this.a = a;
    }

    /**
     * @return the b
     */
    public Sticker getB() {
        return b;
    }

    /**
     * @param b the b to set
     */
    public void setB(Sticker b) {
        this.b = b;
    }

    /**
     * @return the c
     */
    public Sticker getC() {
        return c;
    }

    /**
     * @param c the c to set
     */
    public void setC(Sticker c) {
        this.c = c;
    }

    //this method finds where in the corner a color is located
    //for example, if I wanted to find which of the three faces of the corner was white, I would enter 'w' as the parameter and 
    //receive a coordinate of its location
    public Coordinate findValue(char v) {
        //check to see if the value matches with any of the sticker values
        if (a.getValue() == v) {
            return a.getLocation();
        } else if (b.getValue() == v) {
            return b.getLocation();
        } else if (c.getValue() == v) {
            return c.getLocation();
        } else {
            //throw an exception if it doesn't match. This should not happen.
            throw new IllegalArgumentException("Value is not in this corner."); 
        }
    }

    //get the first value which is not the value given
    //this method, along with getOthers2() gives information about where the corner belongs
    public Coordinate getOthers1(char v) {
        if (a.getValue() == v) {
            return b.getLocation();
        } else if (b.getValue() == v) {
            return c.getLocation();
        } else if (c.getValue() == v) {
            return a.getLocation();
        } else {
            return null;
        }
    }

    public Coordinate getOthers2(char v) {
        if (a.getValue() == v) {
            return c.getLocation();
        } else if (b.getValue() == v) {
            return a.getLocation();
        } else if (c.getValue() == v) {
            return b.getLocation();
        } else {
            return null;
        }
    }

    //a custom comparison method to see if two corners are the same piece. 
    //this is used to compare corners from a solved cube and the current cube to move the corner into position
    //the == operator doesn't work properly because this is an object
    public boolean compare(Corner comparing) {
        //this is done by creating two arraylists with the color values and seeing if one contains the other
        //since we know that both have 3 elements, this is a handy way to compare regardless of order
        ArrayList<Character> comparator = new ArrayList();
        comparator.add(comparing.getA().getValue());
        comparator.add(comparing.getB().getValue());
        comparator.add(comparing.getC().getValue());
        ArrayList<Character> corner = new ArrayList();
        corner.add(this.getA().getValue());
        corner.add(this.getB().getValue());
        corner.add(this.getC().getValue());
        return corner.containsAll(comparator);
    }
    //a custom set method. It sets the values of one corner onto this one
    public void set(Corner corner) {
        this.a.set(corner.a);
        this.b.set(corner.b);
        this.c.set(corner.c);
    }

    //update the location of a corner.
    //when a corner's info is stored in an object to be used, it may move in the meantime
    //this method updates the location of the corner to the current one by comparing the corner to all other corners and
    //setting this corner's location to the correct one
    public Corner update() {
        for (int a = 0; a < 6; a++) {
            for (int b = 0; b < 3; b+=2) {
                for (int c = 0; c < 3; c+=2) {
                    if (Cube.getCorner(new Coordinate(a, b, c)).compare(this)) {
                        this.set(Cube.getCorner(new Coordinate(a, b, c)));
                    }
                }
            }
        }
        return this;
    }
}
