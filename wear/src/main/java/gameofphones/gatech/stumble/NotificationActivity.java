package gameofphones.gatech.stumble;

import android.content.Intent;
import android.os.Bundle;
import android.support.wear.widget.drawer.WearableActionDrawerView;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.drawer.WearableActionDrawer;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class NotificationActivity extends WearableActivity implements MenuItem.OnMenuItemClickListener {

    private TextView mTextView;
    private WearableActionDrawerView mWearableActionDrawer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        this.getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                        | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                        | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

        mTextView = (TextView) findViewById(R.id.text);

        // Enables Always-on
        setAmbientEnabled();
        mWearableActionDrawer = findViewById(R.id.bottom_action_drawer);
        mWearableActionDrawer.getController().peekDrawer();
        mWearableActionDrawer.setOnMenuItemClickListener(this);

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
}
