package gameofphones.gatech.stumble;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InitialECActivity extends AppCompatActivity {

    private EditText firstNameField;
    private EditText lastNameField;
    private EditText phoneNumField;
    private EditText smsNumField;


    private Button mEnterButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_ec);

        firstNameField = findViewById(R.id.first_name_field);
        lastNameField = findViewById(R.id.last_name_field);
        phoneNumField = findViewById(R.id.callPhoneNumber);
        smsNumField = findViewById(R.id.smsPhoneNumber);

        mEnterButton = findViewById(R.id.enter_button);
        mEnterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEmpty(firstNameField) || isEmpty(lastNameField) || isEmpty(phoneNumField) || isEmpty(smsNumField)) {
                    Context context = getApplicationContext();
                    CharSequence text = "Please fill in all fields";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } else {
                    Intent intent = new Intent(InitialECActivity.this, LoginActivity.class);
                    startActivity(intent);
                }

            }

        });
    }

    private boolean isEmpty(EditText text) {
        if (text.getText().toString().trim().length() > 0) {
            return false;
        }
        return true;
    }
}
