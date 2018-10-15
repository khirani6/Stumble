package gameofphones.gatech.stumble;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URL;

public class SignUpActivity extends AppCompatActivity {

    private EditText emailField;
    private EditText passwordField;
    private EditText firstNameField;
    private EditText lastNameField;
    private Button mSignUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        emailField = findViewById(R.id.email_field);
        passwordField = findViewById(R.id.password_field);
        firstNameField = findViewById(R.id.first_name_field);
        lastNameField = findViewById(R.id.last_name_field);

        mSignUpButton = findViewById(R.id.sign_up_button);

        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEmpty(emailField) || isEmpty(passwordField) || isEmpty(firstNameField) || isEmpty(lastNameField)) {
                    Context context = getApplicationContext();
                    CharSequence text = getString(R.string.empty_fields_error);
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } else {
                    String firstName = firstNameField.getText().toString();
                    String lastName = lastNameField.getText().toString();
                    String email = emailField.getText().toString();
                    User user = new User(firstName, lastName, email);
                    String userJson = new Gson().toJson(user);
                    new SignUpTask().execute(NetworkConnection.CREATE_USER, userJson);
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

    private class SignUpTask extends AsyncTask<String, Void, SignUpTask.Result> {
        /**
         * Wrapper class that serves as a union of a result value and an exception. When the download
         * task has completed, either the result value or exception can be a non-null value.
         * This allows you to pass exceptions to the UI thread that were thrown during doInBackground().
         */
        class Result {
            public String mResultValue;
            public Exception mException;
            public Result(String resultValue) {
                mResultValue = resultValue;
            }
            public Result(Exception exception) {
                mException = exception;
            }
        }

        /**
         * Defines work to perform on the background thread.
         * 0 - URL
         * 1 - request body
         */
        @Override
        protected SignUpTask.Result doInBackground(String... params) {
            Result result = null;

            if (!isCancelled() && params != null && params.length > 0) {
                String urlString = params[0];
                try {
                    URL url = new URL(urlString);
                    String body = params[1];
                    String resultString = NetworkConnection.connect(url, NetworkConnection.POST, body);
                    if (resultString != null) {
                        result = new Result(resultString);
                    } else {
                        throw new IOException("No response received.");
                    }
                } catch(Exception e) {
                    result = new Result(e);
                }
            }
            return result;
        }

        /**
         * Updates the UI with the result.
         */
        @Override
        protected void onPostExecute(Result result) {
            if (result.mResultValue != null) {
                Log.i("SignUpResult", "Success: " +  result.mResultValue);
                Intent intent = new Intent(SignUpActivity.this, InitialECActivity.class);
                startActivity(intent);
            }

            if (result.mException != null) {
                Log.i("SignUpResult", "Error: " + result.mException.getMessage());
                Log.e("SignUpResult", result.mException.toString(), result.mException);

                Toast.makeText(
                        getApplicationContext(),
                        getString(R.string.general_error),
                        Toast.LENGTH_SHORT).show();
            }
        }


    }
}
