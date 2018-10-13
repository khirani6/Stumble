package gameofphones.gatech.stumble;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AlertSentActivity extends AppCompatActivity {

    private Button mOKButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_sent);

        mOKButton = findViewById(R.id.ok_button);

        String savedExtra = getIntent().getStringExtra("name");
        TextView myText = findViewById(R.id.nameText);
        myText.setText(savedExtra);

        mOKButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlertSentActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}
