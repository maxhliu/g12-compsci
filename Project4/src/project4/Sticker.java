/*
 * A "sticker" is a value (color) and a location
 */
package project4;

/**
 *
 * @author Max
 */
public class Sticker {
    private Coordinate location;
    private char value;
    Sticker(Coordinate a, char b) {
        location = a;
        value = b;
    }

    /**
     * @return the location
     */
    public Coordinate getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(Coordinate location) {
        this.location = location;
    }

    /**
     * @return the value
     */
    public char getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(char value) {
        this.value = value;
    }
    
    //set the location and value with a custom method since they are objects
    public void set(Sticker sticker) {
        this.location.set(sticker.getLocation());
        this.value = sticker.value;
    }
}
