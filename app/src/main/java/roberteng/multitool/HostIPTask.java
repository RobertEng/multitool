package roberteng.multitool;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Robert on 6/16/2015.
 */
public class HostIPTask extends AsyncTask<String, Integer, String> {
    private Context context;

    public HostIPTask(Context context) {
        this.context = context;
    }

    protected void onPreExecute() {

    }

    protected String doInBackground(String... params) {
        Log.v("HostIPTask", "Getting ip address from entered host name");
        try {
            String hosts = "";
            InetAddress[] iaarr = InetAddress.getAllByName(params[0]);
            Log.v("HostIPTask", "After getAllByName");
            for(int a = 0; a < iaarr.length; a++) {
                hosts += iaarr[a].toString();
            }

        } catch(UnknownHostException exc) {
            Log.e("HostIPTask", "Caught Socket Exception: " + exc.getMessage());

        }
    }

    protected void onProgressUpdate(Integer... params) {

    }

    protected void onPostExecute(String ips) {
        Log.v("HostIPTask", "in onPostExecute");
        Log.v("HostIPTask", "Try to access context and change UI??? context = " + context);
//        TextView hostTextView = (TextView) findViewById(R.id.display_ips);
//        if(hostTextView != null && ips != null) {
//            hostTextView.setText(ips);
//        }
    }

    protected void onCancelled() {

    }
}
