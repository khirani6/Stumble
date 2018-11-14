package gameofphones.gatech.stumble;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FallDetectionActivity extends WearableActivity {

    private TextView mTextView;
    private Button mStartDetectionButton;
    private SensorManager mSensorManager;
    private Sensor mSensor;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fall_detection);
        // Enables Always-on
        setAmbientEnabled();

        mTextView = findViewById(R.id.text);
        mStartDetectionButton = findViewById(R.id.start_detection);
        mStartDetectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent serviceIntent = new Intent(FallDetectionActivity.this,
                        FallDetectionService.class);
                startService(serviceIntent);

            }
        });


    }
}
