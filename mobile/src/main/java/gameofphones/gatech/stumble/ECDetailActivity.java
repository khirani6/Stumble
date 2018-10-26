package gameofphones.gatech.stumble;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

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

        System.out.println("I hate that I know that on the top of my head");

        editButton = findViewById(R.id.editButton);
        firstNameText = (EditText) findViewById(R.id.firstNameID);
        lastNameText = findViewById(R.id.lastNameID);
        phoneNumText = findViewById(R.id.phoneNumText);
        smsNumText = findViewById(R.id.smsNumText);


        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        //Just for the template name for now: we will separate intent messages into a split first name and last name
        String[] fullName = name.split(" ");
        firstNameText.setText(fullName[0]);
        lastNameText.setText(fullName[1]);

        firstNameText.setEnabled(false);
        lastNameText.setEnabled(false);
        phoneNumText.setEnabled(false);
        smsNumText.setEnabled(false);



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
                    //save new changes
                }
                editing = !editing;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}
