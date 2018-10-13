package gameofphones.gatech.stumble;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ECActivity extends AppCompatActivity {

    private ImageButton mAddButton;

    //Been using this tutorial for doing this: https://appsandbiscuits.com/listview-tutorial-android-12-ccef4ead27cc

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ec);

        mAddButton = findViewById(R.id.addButton);

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ECActivity.this, AddECActivity.class);
                startActivity(intent);

            }
        });
    }
}
