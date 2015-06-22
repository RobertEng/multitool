package roberteng.multitool;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;


public class MyActivity extends ActionBarActivity {
    public final static String EXTRA_MESSAGE = "com.roberteng.multitool.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        /* Print the ip address */
        TextView ipTextView = (TextView) findViewById(R.id.ip_message);
        ipTextView.setText(getIPAddress());

        /* Print the mac address */
        TextView macTextView = (TextView) findViewById(R.id.mac_message);
        macTextView.setText(getMacAddress());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /* Called when user taps on the Send Button */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public static String getIPAddress() {
        try {
            /* Get all the network interfaces to get the inetaddresses to get the IP Address */
            for (Enumeration<NetworkInterface> enni = NetworkInterface.getNetworkInterfaces();
                 enni.hasMoreElements();) {
                NetworkInterface ni = enni.nextElement();
                for(Enumeration<InetAddress> enia = ni.getInetAddresses();
                        enia.hasMoreElements();) {
                    InetAddress ia = enia.nextElement();
                    return "IP: " + ia.getHostAddress();
                }
            }
        } catch(SocketException exc) {
            Log.e("MyActivity", "Caught Socket Exception: " + exc.getMessage());
        }
        return "IP = ???";
    }

    public String getMacAddress() {
        WifiManager wm = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
        String ma = wm.getConnectionInfo().getMacAddress();
        if(ma == null) {
            return "Mac Address: No Mac Address or wifi is disabled";
        }
        return "Mac Address: " + ma;

    }
}
