package gameofphones.gatech.stumble;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.wearable.activity.WearableActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.android.gms.wearable.CapabilityClient;
import com.google.android.gms.wearable.CapabilityInfo;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.Wearable;

import java.util.Set;

public class NotificationActivity extends WearableActivity  {

    private TextView textTimer;
    private Button mOKButton;
    private Button mNotOKButton;
    private CountDownTimer countDownTimer;
    private long timeCountInMilliSeconds = 1 * 1000;
    private static final String SMS_CAPABILITY = "send_sms";
    private static final String SMS_PATH = "/send_sms";
    private static String mSmsNodeId;
    private Vibrator mVibrator;
    private NotificationCompat.Builder mNotificationBuilder;
    private static int NOTIFICATION_ID = 001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        this.getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                        | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                        | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

        mVibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        long[] vibrationPattern = {0, 1000, 50, 300};
        //-1 - don't repeat
        final int indexInPatternToRepeat = 0;
        mVibrator.vibrate(vibrationPattern, indexInPatternToRepeat);

        textTimer = findViewById(R.id.timer_textview);

        mOKButton = findViewById(R.id.wear_ok_button);
        mOKButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownTimer.cancel();
                mVibrator.cancel();
                Intent intent = new Intent( NotificationActivity.this, HomeWearActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        mNotOKButton = findViewById(R.id.wear_not_ok_button);
        mNotOKButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SetupSmsTask(getApplicationContext()).execute();
                countDownTimer.cancel();
                mVibrator.cancel();
                Intent intent = new Intent( NotificationActivity.this, AlertSentActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        // Enables Always-on
        setAmbientEnabled();
        startTimer(10);

        // The channel ID of the notification.
        String id = "gameofphones.gatech.stumble";
        // Build intent for notification content
        Intent viewIntent = new Intent(Intent.ACTION_MAIN, null);
        viewIntent.setFlags(Intent.FLAG_ACTIVITY_NO_USER_ACTION | Intent.FLAG_ACTIVITY_NEW_TASK);
        viewIntent.setClass(getApplicationContext(), NotificationActivity.class);

        PendingIntent viewPendingIntent =
                PendingIntent.getActivity(getApplicationContext(), 1, viewIntent, 0);


        // Notification channel ID is ignored for Android 7.1.1
        // (API level 25) and lower.
        mNotificationBuilder =
                new NotificationCompat.Builder(getApplicationContext(), id)
                        .setSmallIcon(R.drawable.stumble_logo_mobile)
                        .setContentTitle(getString(R.string.fall_detected))
                        .setContentText(getString(R.string.alert_sent))
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setDefaults(NotificationCompat.DEFAULT_ALL)
                        .setAutoCancel(true);
    }

    private void startTimer(final int minuti) {
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
                mVibrator.cancel();
                new SetupSmsTask(getApplicationContext()).execute();

                // Get an instance of the NotificationManager service
                NotificationManagerCompat notificationManager =
                        NotificationManagerCompat.from(NotificationActivity.this);
                // Issue the notification with notification manager. Acts as a "missed call"
                // notification letting the user know the emergency message automatically sent
                notificationManager.notify(NOTIFICATION_ID, mNotificationBuilder.build());

                Intent intent = new Intent( NotificationActivity.this, AlertSentActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        }.start();
    }

    private static class SetupSmsTask extends AsyncTask<Object, Void, Void> {

        private Context mContext;

        public SetupSmsTask(Context context) {
            mContext = context;
        }

        @Override
        protected Void doInBackground(Object... params) {
             setupSMSCapability();
             requestSMS("Test".getBytes());
             return null;
        }

        private void setupSMSCapability() {
            try {
                CapabilityInfo capabilityInfo = Tasks.await(
                        Wearable.getCapabilityClient(mContext).getCapability(
                                SMS_CAPABILITY, CapabilityClient.FILTER_REACHABLE));
                updateSMSCapability(capabilityInfo);
            } catch (Exception e) {
                Log.e("NotificationActivity", e.getMessage());
            }

        }

        private void updateSMSCapability(CapabilityInfo capabilityInfo) {
            Set<Node> connectedNodes = capabilityInfo.getNodes();
            mSmsNodeId = pickBestNodeId(connectedNodes);
        }

        private String pickBestNodeId(Set<Node> nodes) {
            String bestNodeId = null;
            // Find a nearby node or pick one arbitrarily
            for (Node node : nodes) {
                if (node.isNearby()) {
                    return node.getId();
                }
                bestNodeId = node.getId();
            }
            return bestNodeId;
        }

        private void requestSMS(byte[] messageData) {
            if (mSmsNodeId != null) {
                Task<Integer> sendTask =
                        Wearable.getMessageClient(mContext).sendMessage(
                                mSmsNodeId, SMS_PATH, messageData);
                sendTask.addOnSuccessListener(new OnSuccessListener<Integer>() {
                    @Override
                    public void onSuccess(Integer integer) {
                        Log.i("NotificationActivity", "Success");
                    }
                });

                sendTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("NotificationActivity", e.getMessage());
                    }
                });
            } else {
                Log.i("NotificationActivity", "Unable to retrieve node");
            }
        }
    }
}
