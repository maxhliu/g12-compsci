/*
 * This class contains info about the cube, mostly which coordinates are part of a single piece or layer
 */
package project4;

/**
 *
 * @author Max
 */
public class Constants {

    //each pair of coordinates is part of an edge piece and therefore move together
    public static final String edgePairs = "001521 \n"
            + "012301 \n"
            + "021201 \n"
            + "010101 \n"
            + "110510 \n"
            + "112210 \n"
            + "212310 \n"
            + "312512 \n"
            + "121410 \n"
            + "221401 \n"
            + "321412 \n"
            + "421501 \n";
    //same with these trios; they form a corner and move together, this will be important in determining where to move pieces
    public static final String cornerSets = "102200020\n"
            + "202022300\n"
            + "222320402\n"
            + "220400122\n"
            + "420500120\n"
            + "422502322\n"
            + "100520000\n"
            + "002302522";
    //the default cube the usage of this will be explained in the main class
    public static final String defCube = "      r r r\n"
            + "      r r r\n"
            + "      r r r\n"
            + "b b b w w w g g g\n"
            + "b b b w w w g g g\n"
            + "b b b w w w g g g\n"
            + "      o o o\n"
            + "      o o o\n"
            + "      o o o\n"
            + "      y y y\n"
            + "      y y y\n"
            + "      y y y";
    //each of these strings contains all the coordinates in a single layer
    //they are used to determine how to move pieces
    public static final String topLayer = "020021022300310320402401400122112102200201202210211212220221222";
    public static final String middleLayer = "010011012301311321410411412101111121";
    public static final String bottomLayer = "000001002302312322420421422100110120500501502510511512520521522";
}
