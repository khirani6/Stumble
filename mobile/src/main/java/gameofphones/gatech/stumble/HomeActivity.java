package gameofphones.gatech.stumble;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private ImageButton mECButton;
    private ImageButton mERButton;
    private ImageButton mAlertButton;
    private ImageButton mSettingsButton;

    private TextView mFallDetectedTextView;
    private Button mResetButton;
    private Button mStartServiceButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mECButton = findViewById(R.id.edit_ec_button);
        mERButton = findViewById(R.id.er_button);
        mAlertButton = findViewById(R.id.alert_button);
        mSettingsButton = findViewById(R.id.settings_button);
        mFallDetectedTextView = findViewById(R.id.fall_detected_textview);
        mResetButton = findViewById(R.id.reset_button);
        mStartServiceButton = findViewById(R.id.start_service);

        mStartServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent serviceIntent = new Intent(HomeActivity.this,
                        FallDetectionService.class);
                startService(serviceIntent);
            }
        });

        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFallDetectedTextView != null) {
                    mFallDetectedTextView.setVisibility(View.INVISIBLE);
                }
            }
        });

        mECButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ECActivity.class);
                startActivity(intent);

            }

        });
        mAlertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, AlertWhoActivity.class);
                intent.putExtra("previous", "home");
                startActivity(intent);
            }
        });

        mSettingsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        this.getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                        | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                        | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
    }
}
