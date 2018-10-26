package gameofphones.gatech.stumble;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class HomeActivity extends AppCompatActivity {

    private ImageButton mECButton;
    private ImageButton mERButton;
    private ImageButton mAlertButton;
    private ImageButton mSettingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mECButton = findViewById(R.id.edit_ec_button);
        mERButton = findViewById(R.id.er_button);
        mAlertButton = findViewById(R.id.alert_button);
        mSettingsButton = findViewById(R.id.settings_button);

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
    }

}
