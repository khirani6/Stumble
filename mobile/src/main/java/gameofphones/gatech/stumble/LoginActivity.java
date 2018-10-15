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
import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    private EditText emailField;
    private EditText passwordField;
    private Button mLoginButton;
    private Button mSignUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailField = findViewById(R.id.email_field);
        passwordField = findViewById(R.id.password_field);
        mLoginButton = findViewById(R.id.log_in_button);
        mSignUpButton = findViewById(R.id.sign_up_button);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEmpty(emailField) || isEmpty(passwordField)) {
                    CharSequence text = getString(R.string.empty_fields_error);
                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } else {
                    HashMap<String, String> emailMap = new HashMap<>();
                    emailMap.put("email", emailField.getText().toString());
                    Gson gson = new Gson();
                    String loginBody = gson.toJson(emailMap);
                    new LoginTask().execute(NetworkConnection.FIND_USER, loginBody);
                }
            }
        });

        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);

            }

        });
    }

    private boolean isEmpty(EditText text) {
        if (text.getText().toString().trim().length() > 0) {
            return false;
        }
        return true;
    }

    private class LoginTask extends AsyncTask<String, Void, LoginTask.Result> {
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
         */
        @Override
        protected LoginTask.Result doInBackground(String... params) {
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
            String json = result.mResultValue;
            if (json != null) {
                Log.i("LoginResult", "Success: " + json);
                Log.i("LoginResult", Integer.toString(json.length()));

                if (json.length() > 2) {
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(
                            getApplicationContext(),
                            getString(R.string.login_error),
                            Toast.LENGTH_SHORT).show();
                }
            }

            if (result.mException != null) {
                Log.i("LoginResult", "Error: " + result.mException.getMessage());
                Log.e("SignUpResult", result.mException.toString(), result.mException);
            }
        }

    }
}
