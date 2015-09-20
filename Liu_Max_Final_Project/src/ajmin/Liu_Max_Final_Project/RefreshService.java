package ajmin.Liu_Max_Final_Project;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

/**
 * Max Liu
 * May 23, 2014
 * Liu_Max_Final_Project
 * ICS4U1-1
 * Mister Lim
 */

//this class creates a service to refresh the prediction data and check to see if the bus is close
public class RefreshService extends IntentService {

    public RefreshService() {
        super("RefreshService");
    }

    //this helper method downloads the xml feed
    //it is essentially the same as downloadxml in downloadxmltask defined in HomeScreen.java
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
            is = conn.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return is;
    }

    //this method checks to see if the bus is within 5 minutes of the stop
    public boolean isBusHere(URL url, String bus) {
        try {
            //get the predictions
            InputStream is = downloadXml(url);
            //parse the predictions
            List predictions = NextBusParser.parse(is, NextBusParser.READ_PREDICTIONS);
            //if the closest bus is within 5 minutes
            if( (Integer) predictions.get(0) <= 5) {
                //notify the user that the bus is here
                notifyBus(bus, (Integer)predictions.get(0));
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //this method creates a notification which wakes the screen, vibrates,
    //blinks the led, and plays a sound
    public void notifyBus(String bus, int minutes) {
        //get the power manager
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        //create a wake lock which wakes the screen even if it is not awake
        PowerManager.WakeLock w = pm.newWakeLock(
                PowerManager.SCREEN_DIM_WAKE_LOCK |
                        PowerManager.ACQUIRE_CAUSES_WAKEUP, "no"
        );
        //hold it for a sec
        w.acquire(1000);
        //vibrate for half a sec with 0 sec delay
        long pattern[] = {0, 500};
        //define the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setContentTitle("The " + bus + " bus is here.")
                .setContentText("Your bus arrives in " + minutes + " minutes.")
                .setSmallIcon(R.drawable.ic_launcher)
                .setLights(Color.CYAN, 1000, 1000) //blink the led blue
                .setVibrate(pattern) //set the vibration
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)); //play a sound
        //get the notification manager so we can actually send the notification
        NotificationManager nf = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //notify using the built notification
        nf.notify(123, builder.build());
    }

    //this method is called when the intent is handled,
    // in other words, when the service starts
    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            //get the data
            String data = intent.getDataString();
            //extract the route and stop data
            Scanner sc = new Scanner(data);
            String route = sc.next();
            String stop = sc.next();
            //build the url
            URL url = new URL(getString(R.string.predictions_url)
                    + "&a=ttc"
                    + "&r=" + route
                    + "&s=" + stop);
            //check if the bus is here, if not, freeze the object and wait 30s then check again.
            //this does not affect the main ui thread since this is a separate thread
            while (!isBusHere(url, route)) {
                synchronized (this) {
                    wait(30000);
                }
            }
            //the service automatically self-destructs
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
