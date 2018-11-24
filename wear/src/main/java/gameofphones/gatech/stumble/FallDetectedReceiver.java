package gameofphones.gatech.stumble;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class FallDetectedReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        if (intent.getAction().equals("gameofphones.stumble.action.FALL_DETECTED")) {
            context.startActivity(new Intent(context, NotificationActivity.class));
        }
    }
}
