package gameofphones.gatech.stumble;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.wearable.activity.WearableActivity;
import android.view.WindowManager;
import android.widget.TextView;

public class NotificationActivity extends WearableActivity  {

    private TextView textTimer;
    private CountDownTimer countDownTimer;
    private long timeCountInMilliSeconds = 1 * 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        this.getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                        | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                        | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

        textTimer = findViewById(R.id.alert_text);

        // Enables Always-on
        setAmbientEnabled();
        startTimer(60);

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
                if(textTimer.getText().equals("00:00")){
                    //do sending alert stuff
                    textTimer.setText("0:00");
                }
                else{
                    textTimer.setText("0:10");
                }
            }
        }.start();
        countDownTimer.start();

    }
}
