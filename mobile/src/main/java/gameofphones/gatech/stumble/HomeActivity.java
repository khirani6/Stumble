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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements SensorEventListener {

    private ImageButton mECButton;
    private ImageButton mERButton;
    private ImageButton mAlertButton;
    private ImageButton mSettingsButton;

    private TextView mFallDetectedTextView;
    private Button mResetButton;

    private SensorManager mSensorManager;
    private Sensor mSensor;

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

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        List<Sensor> deviceSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        Log.d("SensorNames", Integer.toString(deviceSensors.size()));
        for (Sensor s : deviceSensors) {
            Log.d("SensorNames", s.getName());
        }
    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do something here if sensor accuracy changes.
    }

    @Override
    public final void onSensorChanged(SensorEvent event) {
        // Using filters to remove/lessen the effect of gravity
        // See https://developer.android.com/guide/topics/sensors/sensors_motion#java

        final float alpha = 0.8f;
        float[] gravity = new float[3];
        // Isolate the force of gravity with the low-pass filter.
        gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0];
        gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
        gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];

        // Remove the gravity contribution with the high-pass filter.
        float[] linear_acceleration = new float[3];
        linear_acceleration[0] = event.values[0] - gravity[0];
        linear_acceleration[1] = event.values[1] - gravity[1];
        linear_acceleration[2] = event.values[2] - gravity[2];

        // where x,y,z are in linear_acceleration may bhance
        float x = linear_acceleration[0];
        float y = linear_acceleration[1];
        float z = linear_acceleration[2];
        //Log.d("SensorValues", "X Value: " + Float.toString(x));
        //Log.d("SensorValues", "Y Value: " + Float.toString(y));
        Log.d("SensorValues", "Z Value: " + Float.toString(z));

        if (z < 1.0f) {
            Log.d("FallDetected", "fall");
            mFallDetectedTextView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

}
