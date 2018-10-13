package gameofphones.gatech.stumble;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class ECDetailActivity extends AppCompatActivity {

    private EditText firstNameText;
    private EditText lastNameText;
    private EditText phoneNumText;
    private EditText smsNumText;
    private ImageButton editButton;
    boolean editing = false;

    //Been using this tutorial for doing this: https://appsandbiscuits.com/listview-tutorial-android-12-ccef4ead27cc

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecdetail);

        editButton = findViewById(R.id.editButton);
        firstNameText = findViewById(R.id.first_name_field);
        lastNameText = findViewById(R.id.last_name_field);
        phoneNumText = findViewById(R.id.phoneNumText);
        smsNumText = findViewById(R.id.smsNumText);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editing) {
                    editButton.setImageResource(R.drawable.save_button);
                    firstNameText.setEnabled(true);
                    lastNameText.setEnabled(true);
                    phoneNumText.setEnabled(true);
                    smsNumText.setEnabled(true);

                } else {
                    editButton.setImageResource(R.drawable.edit_button);
                    firstNameText.setEnabled(false);
                    lastNameText.setEnabled(false);
                    phoneNumText.setEnabled(false);
                    smsNumText.setEnabled(false);
                }
                editing = !editing;
            }
        });
    }
}
