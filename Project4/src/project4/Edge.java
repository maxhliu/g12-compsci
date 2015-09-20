/*
 * This is essentially a clone of the corner class with two stickers instead of three. see the corner class for comments
 * the update method is somewhat modified, though
 */
package project4;

import java.util.ArrayList;

/**
 *
 * @author Max
 */
public class Edge {

    private Sticker a;
    private Sticker b;

    Edge(Sticker a, Sticker b) {
        this.a = a;
        this.b = b;
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

    public Coordinate findValue(char v) {
        if (a.getValue() == v) {
            return a.getLocation();
        } else if (b.getValue() == v) {
            return b.getLocation();
        } else {
            return null;
        }
    }

    public Coordinate getOthers1(char v) {
        if (a.getValue() == v) {
            return b.getLocation();
        } else if (b.getValue() == v) {
            return a.getLocation();
        } else {
            return null;
        }
    }

    public boolean compare(Edge comparing) {
        ArrayList<Character> comparator = new ArrayList();
        comparator.add(comparing.getA().getValue());
        comparator.add(comparing.getB().getValue());
        ArrayList<Character> edge = new ArrayList();
        edge.add(this.getA().getValue());
        edge.add(this.getB().getValue());
        return edge.containsAll(comparator);
    }

    public void set(Edge edge) {
        this.a.set(edge.a);
        this.b.set(edge.b);
    }

    //the edge class needs a somewhat different update method since edges are distributed differently on the cube but the concept is the same
    public Edge update() {
        for (int a = 0; a < 6; a++) {
            //10 12 01 21
            if (Cube.getEdge(new Coordinate(a, 1, 0)).compare(this)) {
                this.set(Cube.getEdge(new Coordinate(a, 1, 0)));    
            }
            if (Cube.getEdge(new Coordinate(a, 1, 2)).compare(this)) {
                this.set(Cube.getEdge(new Coordinate(a, 1, 2)));
            }
            if (Cube.getEdge(new Coordinate(a, 0, 1)).compare(this)) {
                this.set(Cube.getEdge(new Coordinate(a, 0, 1)));
            }
            if (Cube.getEdge(new Coordinate(a, 2, 1)).compare(this)) {
                this.set(Cube.getEdge(new Coordinate(a, 2, 1)));
            }
        }
        return this;
    }
}
