package gameofphones.gatech.stumble;

import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.widget.TextView;

public class HomeWearActivity extends WearableActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_wear);

        // Enables Always-on
        setAmbientEnabled();
        Intent fallDetectionIntent = new Intent(HomeWearActivity.this, FallDetectionService.class);
        startService(fallDetectionIntent);
    }
}
