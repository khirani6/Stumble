package gameofphones.gatech.stumble;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText emailField;
    private EditText passwordField;
    private Button mLoginButton;
    private Button mSignUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailField = findViewById(R.id.email_field);
        passwordField = findViewById(R.id.password_field);
        mLoginButton = findViewById(R.id.log_in_button);
        mSignUpButton = findViewById(R.id.sign_up_button);

        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);

            }

        });


    }
}
