package gameofphones.gatech.stumble;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.wear.widget.drawer.WearableActionDrawerView;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.drawer.WearableActionDrawer;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class NotificationActivity extends WearableActivity implements MenuItem.OnMenuItemClickListener {

    private TextView mTextView;
    private TextView textTimer;
    private WearableActionDrawerView mWearableActionDrawer;


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

        mTextView = (TextView) findViewById(R.id.text);
        textTimer = findViewById(R.id.alert_text);

        // Enables Always-on
        setAmbientEnabled();
        mWearableActionDrawer = findViewById(R.id.bottom_action_drawer);
        mWearableActionDrawer.getController().peekDrawer();
        mWearableActionDrawer.setOnMenuItemClickListener(this);

        startTimer(60);

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        final int itemId = item.getItemId();
        switch(itemId){
            case R.id.action_ok:
                Intent intent = new Intent(NotificationActivity.this, HomeWearActivity.class);
                startActivity(intent);
                break;
            case R.id.action_not_ok:
                //do notification stuff
                break;
        }
        mWearableActionDrawer.getController().closeDrawer();

        return false;
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
