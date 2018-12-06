package gameofphones.gatech.stumble;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddECActivity extends AppCompatActivity {

    private Button mSaveButton;
    private EditText firstNameText;
    private EditText lastNameText;
    private EditText phoneNumText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ec);

        mSaveButton = findViewById(R.id.saveButton);
        firstNameText = findViewById(R.id.firstNameText);
        lastNameText = findViewById(R.id.lastNameText);
        phoneNumText = findViewById(R.id.phoneNumID);

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(AddECActivity.this, ECActivity.class);
                //startActivity(intent);
                finish();
            }
        });
    }
}
