package ajmin.Liu_Max_Final_Project;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Max Liu
 * May 23, 2014
 * Liu_Max_Final_Project
 * ICS4U1-1
 * Mister Lim
 */

//this class parses the various XML documents used in this program.
public class NextBusParser {
    //Constant class variables allow easy branching
    public static final int READ_ROUTES = 1;
    public static final int READ_STOPS = 2;
    public static final int READ_PREDICTIONS = 3;
    //there are no namespaces used in the nextbus APIs
    private static final String nameSpace = null;

    //this is the main function which is used to parse XML documents
    //it has several helper functions which parse specific types of documents
    //the XML document is passed in using an InputStream and a function defines what
    //action the parser should do.
    public static List parse(InputStream is, int function) throws XmlPullParserException, IOException {
        try {
            //get an instance of an XmlPullParser using an XmlPullParserFactory.
            XmlPullParserFactory f = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = f.newPullParser();
            //the nextbus api does not use namespaces, so disable this feature
            xpp.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            //set the input and automatically detect the encoding
            xpp.setInput(is, null);
            //start the parser with a call to nextTag to enter the document
            xpp.nextTag();
            //collect different data depending on what is needed
            switch (function) {
                case NextBusParser.READ_ROUTES:
                    return readRoutes(xpp);
                case NextBusParser.READ_STOPS:
                    return readStops(xpp);
                case NextBusParser.READ_PREDICTIONS:
                    return readPredictions(xpp);
                default:
                    //make sure that the function variable is valid
                    throw new IllegalArgumentException("Use class constants to specify function.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //this method is used when the XML document is a list of routes
    private static List readRoutes(XmlPullParser xpp) throws IOException, XmlPullParserException {
        //create a list of routes to store the routes in
        List routes = new ArrayList();
        //make sure that the xml parser starts on a start tag or else throw an exception
        xpp.require(XmlPullParser.START_TAG, nameSpace, null);
        //go into the body tag
        xpp.next();
        //iterate through the each of the route tags
        while (xpp.next() != XmlPullParser.END_DOCUMENT) {
            //if the parser is not at the beginning of the tag; skip this iteration and move on
            //so that the xml parser is at the beginning of a tag
            if (xpp.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            //get the route info and add the route to the list of routes
            Route theRoute = new Route(xpp.getAttributeValue(null, "tag"), xpp.getAttributeValue(null, "title"));
            routes.add(theRoute);
        }
        return routes;
    }

    private static List readStops(XmlPullParser xpp) throws IOException, XmlPullParserException {
        //create a list to store the stops
        List stops = new ArrayList();
        //make sure that the first tag is a start tag
        xpp.require(XmlPullParser.START_TAG, nameSpace, null);
        //go into the body tag
        xpp.next();
        //iterate through the document
        Route route = null;
        //iterate through the document
        while (xpp.next() != XmlPullParser.END_DOCUMENT) {
            //make sure that the parser is on a start tag
            //also, if a tag is the header or the body, do not skip it; go inside it
            if (xpp.getEventType() != XmlPullParser.START_TAG
                    || xpp.getName().equals("header")
                    || xpp.getName().equals("body")) {
                continue;
                //if it is a route tag record the route as a Route object
            } else if (xpp.getName().equals("route")) {
                //the nextbus api uses attributes to store the tag and title info so retrieve it
                route = new Route(xpp.getAttributeValue(null, "tag"),
                        xpp.getAttributeValue(null, "title"));
                //if it is a stop tag create a Stop object and add it to the results List
            } else if (xpp.getName().equals("stop")) {
                //Get the tag attribute
                String tag = xpp.getAttributeValue(null, "tag");
                //advance the parser to the text value stored inside the tag
                xpp.next();
                //the name of the stop is not stored as an attribute
                String text = xpp.getText();
                //create the stop object and add it to the array
                Stop theStop = new Stop(tag, text, route);
                stops.add(theStop);
            } else {
                //there are many undesirable nested tags in this xml document which
                //define timed stops and such irrelevant matters
                //make sure to skip them
                skip(xpp);
            }
        }
        return stops;
    }

    private static List readPredictions(XmlPullParser xpp) throws IOException, XmlPullParserException {
        List times = new ArrayList();
        //make sure that the parser is starting on a start tag
        xpp.require(XmlPullParser.START_TAG, nameSpace, null);
        xpp.nextTag();
        //iterate through the document
        while (xpp.next() != XmlPullParser.END_DOCUMENT) {
            //make sure that the parser is on a start tag
            if (xpp.getEventType() != XmlPullParser.START_TAG) {
                continue;
                //if it is a prediction tag
            } else if (xpp.getName().equals("prediction")) {
                //get the prediction values in minutes and store it in the List
                times.add(new Integer(xpp.getAttributeValue(null, "minutes")));
            }
        }
        return times;
    }

    //this method skips undesirable tags (which may have nested tags inside them)
    private static void skip(XmlPullParser xpp) throws IOException, XmlPullParserException {
        //a depth variable keeps track of how many levels down you have entered to make sure
        //that the last tag is the same as the first tag
        //it is initialized to 1 because the initial call to xpp.next() advances the depth by 1
        int depth = 1;
        while (depth != 0) {
            switch (xpp.next()) {
                //if it is a start tag it means we are going deeper
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
                //if it is an end tag it means we are exiting
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                default:
                    break;
            }
        }
    }
}
