package roberteng.multitool;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class DisplayMessageActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        Log.d("DisplayMessageActivity", "In onCreate\n");

        /* Get the Message from intent */
        Intent intent = getIntent();
        String message = intent.getStringExtra(MyActivity.EXTRA_MESSAGE);

        /* Make the textView and put it in the layout */

        TextView dmTextView = (TextView)findViewById(R.id.display_message);
        dmTextView.setText(message);

        /* Get ip address from entered host name using an asynchronous task (AsyncTask) since
         * network tasks apparently must be done on another thread. */
        HostIPTask hipt = new HostIPTask(this);
        hipt.execute(message);
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
}
