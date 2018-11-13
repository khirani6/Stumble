package gameofphones.gatech.stumble;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class AlertWhoActivity extends AppCompatActivity {

    String[] templateNamesArr = {"Komal Hirani"};

    final String alertString = "This is a test alert sent by User";

    private boolean isTest = false;

    private BroadcastReceiver sentStatusReceiver, deliveredStatusReceiver;

    private String phoneNumber;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_who);

        AlertWhoAdapter alertWho = new AlertWhoAdapter(this, templateNamesArr);
        listView = findViewById(R.id.alert_who_list);
        listView.setAdapter(alertWho);

        Intent intent = getIntent();
        String previous = intent.getStringExtra("previous");

        if (previous == "home") {
            isTest = true;
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                sendFall();
                Intent intent = new Intent(AlertWhoActivity.this, AlertSentActivity.class);
                String message = templateNamesArr[position];
                intent.putExtra("name", message);
                startActivity(intent);
            }
        });
    }

    public void sendFall() {
        //will allow text message to be sent to a contact
        //will all be replaced once backend is connected to frontend
        User currentUser = UserTracker.getInstance().getCurrentUser();
        String alertMessage = String.format(
                getString(R.string.alert_text), currentUser.getFirstName());
        if (isTest) {
            alertMessage = alertString;
        }
        Log.d("Alert Message", alertMessage);
        String contactNumber = "8322473858";

        //Remove whitespace and non-numeric characters
        contactNumber = contactNumber.trim();
        contactNumber = contactNumber.replaceAll("\\W", "");
        if (contactNumber.length() < 10) {
            Toast.makeText(getApplicationContext(), "Contact phone number is invalid", Toast.LENGTH_SHORT).show();
        } else {

            SmsManager sms = SmsManager.getDefault();
            PendingIntent sentIntent = PendingIntent.getBroadcast(this, 0, new Intent("SMS_SENT"), 0);
            PendingIntent deliveredIntent = PendingIntent.getBroadcast(this, 0, new Intent("SMS_DELIVERED"), 0);
            sms.sendTextMessage(contactNumber, null, alertMessage, sentIntent, deliveredIntent);
        }
    }

    public void onResume() {
        super.onResume();
        //setFullName(firstName, lastName);
        sentStatusReceiver= new BroadcastReceiver() {

            @Override
            public void onReceive(Context arg0, Intent arg1) {
                String s = "Unknown Error";
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        s = "Message Sent Successfully !!";
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        s = "Generic Failure Error";
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        s = "Error : No Service Available";
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        s = "Error : Null PDU";
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        s = "Error : Radio is off";
                        break;
                    default:
                        break;
                }
            }
        };
        deliveredStatusReceiver=new BroadcastReceiver() {

            @Override
            public void onReceive(Context arg0, Intent arg1) {
                String s = "Message Not Delivered";
                switch(getResultCode()) {
                    case Activity.RESULT_OK:
                        s = "Message Delivered Successfully";
                        break;
                    case Activity.RESULT_CANCELED:
                        break;
                }
            }
        };
        registerReceiver(sentStatusReceiver, new IntentFilter("SMS_SENT"));
        registerReceiver(deliveredStatusReceiver, new IntentFilter("SMS_DELIVERED"));
    }

    public void onPause() {
        super.onPause();
        unregisterReceiver(sentStatusReceiver);
        unregisterReceiver(deliveredStatusReceiver);
    }
}
