package gameofphones.gatech.stumble;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class ECActivity extends AppCompatActivity {

    private ImageButton mAddButton;

    ArrayList<EmergencyContact> templateNamesArr = new ArrayList<>();
    String names = "KOMAL HIRANI";



    ListView ecListView;

    //Been using this tutorial for doing this: https://appsandbiscuits.com/listview-tutorial-android-12-ccef4ead27cc

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ec);

        UserTracker userTracker = new UserTracker();

        User user = userTracker.getCurrentUser();

        for (EmergencyContact name : user.getEmergencyContacts()) {
            templateNamesArr.add(name);
        }

        ECListAdapter ecListAdapter = new ECListAdapter(this, templateNamesArr);

        mAddButton = findViewById(R.id.addButton);
        ecListView = findViewById(R.id.ec_list);
        ecListView.setAdapter(ecListAdapter);

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ECActivity.this, AddECActivity.class);
                startActivity(intent);

            }
        });

        ecListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(ECActivity.this, ECDetailActivity.class);
                //String message = templateNamesArr[position];
                //intent.putExtra("name", message);
                startActivity(intent);

            }

        });
    }
}
