package ajmin.Liu_Max_Final_Project;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Max Liu
 * May 23, 2014
 * Liu_Max_Final_Project
 * ICS4U1-1
 * Mister Lim
 */


/**
 * Overview.
 *
 */

public class HomeScreen extends Activity {

    //Have a constant variable to use as the tag in Log calls.
    private static final String TAG = "HomeScreen.java";
    //make sure not to start another warning service when one already exists
    private boolean isWarning = false;

    //code that is called when the program first starts up
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //initialize the activity and display the content
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        try {
            //run the ASyncTak which does pretty much all of the work
            new DownloadXmlTask().execute("ttc");
            //find the button which sets the warning
            Button button = (Button) findViewById(R.id.setWarning);
            //add a listener
            //when it is clicked get the currently selected stop and route configuration
            //and set the warning
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //find the stop spinner
                    //we only need one spinner since the Stop object contains a Route instance
                    Spinner stopSpinner = (Spinner) findViewById(R.id.stopSpinner);
                    //set the warning, passing the currently selected stop
                    setWarning((Stop) stopSpinner.getSelectedItem());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //this method starts an IntentService which runs in the background and will
    //alert us when our bus is here
    public void setWarning(Stop stop) {
        //only create a new service if one doesn't already exist
        if (!isWarning) {
            //there is a warning set up
            isWarning = true;
            //create an explicit intent to start the service
            Intent intent = new Intent(this, RefreshService.class);
            String dataString = "";
            //pass in the route and stop info of the bus stop we are watching
            //so that the activity can access the XML prediction feed
            dataString = stop.getRoute().getTag() + " " + stop.getTag();
            //set the data to the intent
            intent.setData(Uri.parse(dataString));
            //start the service
            startService(intent);
        }
    }


    //this method populates the Route Spinner
    //when it is defined in XML the spinner is empty and needs to be filled
    //with values by an ArrayAdapter
    public void populateRouteSpinner(List list) {
        //find the spinner
        Spinner routeSpinner = (Spinner) findViewById(R.id.routeSpinner);
        //create an arrayadapter from a default android XML style of spinner item
        //and populate it with the passed-in list which should be Routes
        ArrayAdapter<String> aa = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        //set the XML configuration of the dropdown items
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //set the adapter to the spinner
        routeSpinner.setAdapter(aa);
        //set a listener to detect when an item is selected
        routeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //find out which route was selected
                Route selectedItem = (Route) adapterView.getSelectedItem();
                //pass the route to a new AsyncTask to find its stops and populate the
                //stop spinner
                new DownloadXmlTask().execute(selectedItem);
            }

            //it doesn't matter if nothing is selected
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //do nothing
            }
        });
    }

    //this method populates the stop spinner similarly to the route spinner
    public void populateStopSpinner(List list) {
        Spinner stopSpinner = (Spinner) findViewById(R.id.stopSpinner);
        ArrayAdapter<String> aa = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stopSpinner.setAdapter(aa);
        stopSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Stop selectedItem = (Stop) adapterView.getSelectedItem();
                //create yet another asynctask to get the route predictions from nextbus
                new DownloadXmlTask().execute(selectedItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //do nothing
            }
        });
    }

    //show the predictions by setting the textview to the predicted bus times
    public void displayPredictions(List list) {
        //find the textview
        TextView tv = (TextView) findViewById(R.id.predictions);
        //add all the predicted times into the textview string
        String text = "";
        for (Object o : list) {
            text += (Integer) o + " minutes\r\n";
        }
        //if no predictions are available, say so
        if (text.isEmpty()) {
            text = "N/A";
        }
        //set the textview's text to this text
        tv.setText(text);
    }

    //this is the AsyncTask which downloads the XML and also does a lot of the processing work
    //so that the main UI thread is responsive.
    private class DownloadXmlTask extends AsyncTask<Object, Object, List> {

        //this method runs on the UI thread and can be called during the run process
        //this is important because the Spinner Views can only be accessed from the Main thread
        //Although the onExecutePost method also runs on the UI thread, onProgressUpdate is
        //more flexible because it can be called more than once.
        @Override
        protected void onProgressUpdate(Object... values) {
            //populate and display predictions here
            //use the given function number to determine what progress to update
            //although the parameters are shown as Object...
            //the actual underlying parameters are Integer, ArrayList
            switch ((Integer) values[0]) {
                //in the first run populate the route spinners
                //publishProgress (which calls this method) is called in doInBackground after the XML data has been downloaded
                case NextBusParser.READ_ROUTES:
                    populateRouteSpinner((List) values[1]);
                    break;
                //this will be called during the second instance of DownloadXmlTask and will populate the stops
                case NextBusParser.READ_STOPS:
                    populateStopSpinner((List) values[1]);
                    break;
                //this displays the predictions
                case NextBusParser.READ_PREDICTIONS:
                    displayPredictions((List) values[1]);
                    break;
            }
        }

        //this method is called when the AsyncTask is instantiated and runs in a separate thread.
        @Override
        protected List doInBackground(Object... object) {
            //this int stores the function of this method
            //depending on the function number, different actions are executed.
            int function = 0;
            //since the method's parameters are Object... many different combinations of
            //data can be passed in depending on the function
            //if a String is passed in, read the routes. this string will be the bus agency tag.
            if (object[0] instanceof String) {
                function = NextBusParser.READ_ROUTES;
                //if a Route is passed in, read the stops belonging to this route
            } else if (object[0] instanceof Route) {
                function = NextBusParser.READ_STOPS;
                //if a stop is passed in, read the predictions for this stop.
            } else if (object[0] instanceof Stop) {
                function = NextBusParser.READ_PREDICTIONS;
            } else {
                //make sure the arguments are of the proper types.
                throw new IllegalArgumentException("Pass in an agency, route, or stop.");
            }
            try {
                URL url;
                //again, do different things depending on the function variable
                switch (function) {
                    //append just the agency name to the url to read routes
                    case NextBusParser.READ_ROUTES:
                        url = new URL(getString(R.string.routes_url) + "&a=ttc");
                        break;
                    //append the agency name and route number to read stops
                    case NextBusParser.READ_STOPS:
                        url = new URL(getString(R.string.stops_url) + "&a=ttc&r=" + ((Route) object[0]).getTag());
                        break;
                    //append the agency name, route name, and stop id to the url to read predictions
                    case NextBusParser.READ_PREDICTIONS:
                        url = new URL(getString(R.string.predictions_url)
                                + "&a=ttc&r=" + ((Stop) object[0]).getRoute().getTag()
                                + "&s=" + ((Stop) object[0]).getTag());
                        break;
                    default:
                        throw new IllegalArgumentException("Use class constants to specify function.");
                }
                //download the inputstream from the appropriate url using a helper function
                InputStream is = downloadXml(url);
                //parse the downloaded xml and put the needed values into a list
                final List result = NextBusParser.parse(is, function);
                //call onProgressUpdate via publishProgress to populate spinners and textviews
                switch (function) {
                    case NextBusParser.READ_ROUTES:
                        //populate route spinner
                        publishProgress(function, result);
                        break;
                    case NextBusParser.READ_STOPS:
                        //populate stop spinner
                        publishProgress(function, result);
                        break;
                    case NextBusParser.READ_PREDICTIONS:
                        //set textview to prediction values
                        publishProgress(function, result);
                        break;
                    default:
                        throw new IllegalArgumentException("Use class constants.");
                }

                //return
                //this does not mattter
                return result;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        //the onPostExecute method is called when the AsyncTask completes
        //in this implementation this method is not needed
//        @Override
//        protected void onPostExecute(List result) {
//            populateRouteSpinner(result);
//        }

        //this method downloads xml from an url
        private InputStream downloadXml(URL url) {
            InputStream is = null;
            try {
                //set up a new http connection to download the xml
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                //set timeouts so that it doesn't take too long and freeze
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(10000);
                //set the request to get so that it receives the xml data
                conn.setRequestMethod("GET");
                //connect to the url
                conn.connect();
                //set the inputstream as whatever is returned by the url, which should be the XML raw data
                is = conn.getInputStream();
            } catch (Exception e) {
                e.printStackTrace();
            }
            //when all is done return the inputstream
            return is;
        }

    }

}
