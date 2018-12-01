package gameofphones.gatech.stumble;

import android.app.PendingIntent;
import android.content.Intent;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;


public class DataLayerListenerService extends WearableListenerService {

    private static final String SMS_PATH = "/send_sms";

    public DataLayerListenerService() {
        super();
    }


    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        Log.i("DataLayerListenerService", "Message Received");
        if (messageEvent.getPath().equals(SMS_PATH)) {
             sendAlert();
        }
    }

    private void sendAlert() {
        User currentUser = UserTracker.getInstance().getCurrentUser();
        if (currentUser == null) {
            currentUser = new User("Clayton", "", "");
        }
        String alertMessage = String.format(
                getString(R.string.alert_text), currentUser.getFirstName());
        Log.d("Alert Message", alertMessage);
        //contact would change of course
        String contactNumber = getString(R.string.demo_phone_number);
        //Remove whitespace and non-numeric characters
        contactNumber = contactNumber.trim();
        contactNumber = contactNumber.replaceAll("\\W", "");
        if (contactNumber.length() < 10) {
            Toast.makeText(getApplicationContext(),
                    "Contact phone number is invalid",
                    Toast.LENGTH_SHORT).show();
        } else {

            SmsManager sms = SmsManager.getDefault();
            PendingIntent sentIntent = PendingIntent.getBroadcast(
                    this,
                    0,
                    new Intent("SMS_SENT"),
                    0);
            PendingIntent deliveredIntent = PendingIntent.getBroadcast(
                    this,
                    0, new Intent(
                            "SMS_DELIVERED"),
                    0);
            sms.sendTextMessage(contactNumber,
                    null,
                    alertMessage,
                    sentIntent,
                    deliveredIntent);
        }

    }

}
