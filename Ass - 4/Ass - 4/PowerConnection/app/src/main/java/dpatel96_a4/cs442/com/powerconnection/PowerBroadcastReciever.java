package dpatel96_a4.cs442.com.powerconnection;

/**
 * Created by darpa on 02-10-2015.
 */import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;


// This class is Broadcast Receiver class. It is used to show what kind of action performed on ACTION occurred by receiver


public class PowerBroadcastReciever extends BroadcastReceiver {
    @Override

    // This method is used automatically called when ACTION is performed by intent.
    public void onReceive(Context context, Intent intent) {

        String mes = "Power USB connection:  " + intent.getAction();

        Toast.makeText(context,mes, Toast.LENGTH_LONG).show();
    }
}