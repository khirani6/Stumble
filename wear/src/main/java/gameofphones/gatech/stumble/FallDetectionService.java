package gameofphones.gatech.stumble;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.NotificationCompat.WearableExtender;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.util.Log;

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

        if (z < 0.0f && z > -7.4f) {
            Log.d("FallDetectionService", "Fall! Z = " + Float.toString(z));

            int notificationId = 001;
            // The channel ID of the notification.
            String id = "my_channel_01";
            // Build intent for notification content
            Intent viewIntent = new Intent(this, NotificationActivity.class);
            /*viewIntent.putExtra(EXTRA_EVENT_ID, eventId); */
            PendingIntent viewPendingIntent =
                    PendingIntent.getActivity(this, 0, viewIntent, 0);

            // Notification channel ID is ignored for Android 7.1.1
            // (API level 25) and lower.
            NotificationCompat.Builder notificationBuilder =
                    new NotificationCompat.Builder(this, id)
                            .setSmallIcon(R.drawable.stumble_logo_mobile)
                            .setContentTitle(getString(R.string.fall_detected))
                            .setContentText(getString(R.string.is_user_okay))
                            .setContentIntent(viewPendingIntent);

            // Get an instance of the NotificationManager service
            NotificationManagerCompat notificationManager =
                    NotificationManagerCompat.from(this);

            // Issue the notification with notification manager.
            notificationManager.notify(notificationId, notificationBuilder.build());
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // START_NOT_STICKY: User manually triggers the service to start after it has been killed
        // START_STICKY: Service automatically restarts on its own if it's killed
        // START_REDELIVER_INTENT: Service automatically starts when user re-opens app (if it had been killed)

        // TODO: Does START_STICKY drain the battery?
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_UI);
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.d("FallDetectionService", "Service Destroyed");
        mSensorManager.unregisterListener(this);
        super.onDestroy();
    }
}
