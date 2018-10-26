package gameofphones.gatech.stumble;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.util.Log;
import android.view.View;

import java.util.List;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class FallDetectionService extends IntentService implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mSensor;
    private Context mContext;

    public FallDetectionService() {
        super("FallDetectionService");
    }

    @Override
    public void onCreate() {
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        List<Sensor> deviceSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        mContext = getApplicationContext();
        Log.d("SensorNames", Integer.toString(deviceSensors.size()));
        for (Sensor s : deviceSensors) {
            Log.d("SensorNames", s.getName());
        }
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            Log.d("FallDetectionService", "Started");
        } else {
            Log.d("FallDetectionService", "Null intent!");
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

        // where x,y,z are in linear_acceleration
        float x = linear_acceleration[0];
        float y = linear_acceleration[1];
        float z = linear_acceleration[2];
        //Log.d("SensorValues", "X Value: " + Float.toString(x));
        //Log.d("SensorValues", "Y Value: " + Float.toString(y));
        Log.d("SensorValues", "Z Value: " + Float.toString(z));

        if (z < -1.0f && z > -7.4f) {
            Log.d("FallDetectionService", "Fall! Z = " + Float.toString(z));
            Intent intent = new Intent(Intent.ACTION_MAIN, null);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_USER_ACTION | Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setClass(mContext, HomeActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 1, intent, 0);

            // Build the notification as an ongoing high priority item; this ensures it will show as
            // a heads up notification which slides down over top of the current content.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel notificationChannel = new NotificationChannel(
                        "gameofphones.gatech.stumble",
                        "Stumble",
                        NotificationManager.IMPORTANCE_HIGH);
                NotificationManager notificationManager = getSystemService(NotificationManager.class);
                notificationManager.createNotificationChannel(notificationChannel);
                final Notification.Builder builder =
                        new Notification.Builder(mContext, notificationChannel.getId());
                builder.setOngoing(true);
//                builder.setPriority(Notification.PRIORITY_HIGH); TODO: Might need this for older versions

                // Set notification content intent to take user to fullscreen UI if user taps on the
                // notification body.
                builder.setContentIntent(pendingIntent);
                // Set full screen intent to trigger display of the fullscreen UI when the notification
                // manager deems it appropriate.
                builder.setFullScreenIntent(pendingIntent, true);

                // Setup notification content.
                builder.setSmallIcon(R.drawable.ic_notification_stumble);
                builder.setContentTitle(getString(R.string.fall_detected));
                builder.setContentText(getString(R.string.is_user_okay));

                // Use builder.addAction(..) to add buttons to answer or reject the call.
                // TODO: Add buttons to trigger alert or dismiss timer

                builder.setAutoCancel(true);
                notificationManager.notify("FallDetectedTag", 4261, builder.build());
            }
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // START_NOT_STICKY: User manually triggers the service to start after it has been killed
        // START_STICKY: Service automatically restarts on its own if it's killed
        // START_REDELIVER_INTENT: Service automatically starts when user re-opens app (if it had been killed)

        // TODO: Does START_STICKY drain the battery?
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_UI);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.d("FallDetectionService", "Service Destroyed");
        mSensorManager.unregisterListener(this);
        super.onDestroy();
    }
}
