package gameofphones.gatech.stumble;

import android.content.Context;
import android.content.Intent;
import android.net.Network;
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

public class InitialECActivity extends AppCompatActivity {

    private EditText firstNameField;
    private EditText lastNameField;
    private EditText phoneNumField;

    private Button mEnterButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_ec);

        firstNameField = findViewById(R.id.first_name_field);
        lastNameField = findViewById(R.id.last_name_field);
        phoneNumField = findViewById(R.id.callPhoneNumber);

        mEnterButton = findViewById(R.id.enter_button);
        mEnterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEmpty(firstNameField) || isEmpty(lastNameField) || isEmpty(phoneNumField)) {
                    Context context = getApplicationContext();
                    CharSequence text = "Please fill in all fields";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } else {
                    String firstName = firstNameField.getText().toString();
                    String lastName = lastNameField.getText().toString();
                    String phoneNumber = phoneNumField.getText().toString();

                    EmergencyContact ec = new EmergencyContact(firstName, lastName, phoneNumber);
                    User currUser = UserTracker.getInstance().getCurrentUser();
                    new InitialECTask().execute(ec, currUser);
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

    private class InitialECTask extends AsyncTask<Object, Void, InitialECTask.Result> {
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
        protected InitialECTask.Result doInBackground(Object... params) {
            InitialECTask.Result result = null;
            if (!isCancelled() && params != null) {
                EmergencyContact ec = (EmergencyContact) params[0];
                User user = (User) params[1];
                try {
                    String resultString = NetworkConnection.addECToUser(ec, user);
                    if (resultString != null) {
                        result = new InitialECTask.Result(resultString);
                    } else {
                        throw new IOException("No Response Received");
                    }
                } catch (Exception e) {
                    result = new Result(e);
                }
            }
            return result;
        }

        /**
         * Updates the UI with the result.
         */
        @Override
        protected void onPostExecute(InitialECTask.Result result) {
            if (result.mResultValue != null) {
                Log.i("InitialECResult", "Success: " +  result.mResultValue);
                Intent intent = new Intent(InitialECActivity.this, LoginActivity.class);
                startActivity(intent);
            }

            if (result.mException != null) {
                Log.i("InitialECResult", "Error: " + result.mException.getMessage());
                Log.e("InitialECResult", result.mException.toString(), result.mException);

                Toast.makeText(
                        getApplicationContext(),
                        getString(R.string.general_error),
                        Toast.LENGTH_SHORT).show();
            }
        }


    }
}
