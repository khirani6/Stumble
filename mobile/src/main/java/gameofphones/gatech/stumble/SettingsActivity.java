package gameofphones.gatech.stumble;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    public static int duration;
    private TextView timerLengthText;
    private Button mLogoutButton;
    private Button mSaveTimeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        duration = 0;

        timerLengthText = findViewById(R.id.changeTimerLength);
        mLogoutButton = findViewById(R.id.logoutButton);
        mSaveTimeButton = findViewById(R.id.saveTimeButton);


        mLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        mSaveTimeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                timerLengthText.setText(timerLengthText.getText().toString());
                String time = timerLengthText.getText().toString();
                if (time.length() >= 4) {
                    String[] units = time.split(":");
                    int minutes = Integer.parseInt(units[0]);
                    int seconds = Integer.parseInt(units[1]);
                    duration = 60 * minutes + seconds;
                } else {
                    Context context = getApplicationContext();
                    CharSequence text = "Please enter a valid time format (MM:SS)";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });

    }
}
