package gameofphones.gatech.stumble;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.wearable.MessageEvent;


public class AlertActivity extends AppCompatActivity {

    private CountDownTimer countDownTimer;
    private TextView textTimer;
    private Button mOKButton;
    private Button mNotOKButton;

    private long timeCountInMilliSeconds = 1 * 1000;

    private BroadcastReceiver sentStatusReceiver, deliveredStatusReceiver;
    private Ringtone mRingtone;

    private static final int REQUEST_SMS = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);
        this.getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                        | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                        | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

        try {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
            mRingtone = RingtoneManager.getRingtone(getApplicationContext(), notification);
        } catch (Exception e) {
            e.printStackTrace();
        }

        textTimer = findViewById(R.id.timerText);
        mOKButton = findViewById(R.id.okButton);
        mNotOKButton = findViewById(R.id.notOKButton);

        mOKButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRingtone != null) {
                    mRingtone.stop();
                }
                Intent intent = new Intent(AlertActivity.this, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        mNotOKButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRingtone != null) {
                    mRingtone.stop();
                }
                Intent intent = new Intent(AlertActivity.this, AlertWhoActivity.class);
                intent.putExtra("previous", "alert");
                startActivity(intent);
            }
        });
        //will have to be changed once we get user's preferred duration set up
        int minuti = SettingsActivity.duration;
        if (minuti == 0) {
            minuti = 10;
        }
        startTimer(minuti);
    }

    private void startTimer(final int minuti) {
        mRingtone.play();
        countDownTimer = new CountDownTimer(timeCountInMilliSeconds*minuti, 1000) {
            // 500 means, onTick function will be called at every 500 milliseconds

            @Override
            public void onTick(long leftTimeInMilliseconds) {
                long seconds = leftTimeInMilliseconds / 1000;
                //barTimer.setProgress((int)seconds);
                textTimer.setText(String.format("%02d", seconds/60) + ":" + String.format("%02d", seconds%60));
                // format the textview to show the easily readable format

            }
            @Override
            public void onFinish() {
                //add functionality to automatically send alert to all ECs
                if(textTimer.getText().equals("00:00")){
                    sendAlert();
                    textTimer.setText("0:00");
                    mRingtone.stop();

                }
                else{
                    textTimer.setText("0:10");
                }
            }
        }.start();
    }

    private void sendAlert() {
        User currentUser = UserTracker.getInstance().getCurrentUser();
        String alertMessage = String.format(
                getString(R.string.alert_text), currentUser.getFirstName());
        Log.d("Alert Message", alertMessage);
        //contact would change of course
        String contactNumber = getString(R.string.demo_phone_number);
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
