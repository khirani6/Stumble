package gameofphones.gatech.stumble;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.concurrent.TimeUnit;


public class AlertActivity extends AppCompatActivity {

    private CountDownTimer countDownTimer;
    private TextView textTimer;
    private Button mOKButton;
    private Button mNotOKButton;

    private long timeCountInMilliSeconds = 1 * 60000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);
        textTimer = findViewById(R.id.timerText);
        mOKButton = findViewById(R.id.okButton);
        mNotOKButton = findViewById(R.id.notOKButton);

        mOKButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlertActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        mNotOKButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlertActivity.this, AlertWhoActivity.class);
                startActivity(intent);
            }
        });


        startTimer(1);
    }

    private void startTimer(final int minuti) {
        countDownTimer = new CountDownTimer(timeCountInMilliSeconds, 1000) {
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
                if(textTimer.getText().equals("00:00")){
                    textTimer.setText("0:00");
                }
                else{
                    textTimer.setText("1:00");
                }
            }
        }.start();
        countDownTimer.start();

    }

}
