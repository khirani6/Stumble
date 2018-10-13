package gameofphones.gatech.stumble;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class AlertWhoActivity extends AppCompatActivity {

    String[] templateNamesArr = {"Komal Hirani", "Emergency Room"};

    final String alertString = "This is a test alert sent by User";

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_who);

        AlertWhoAdapter alertWho = new AlertWhoAdapter(this, templateNamesArr);
        listView = findViewById(R.id.alert_who_list);
        listView.setAdapter(alertWho);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(AlertWhoActivity.this, AlertSentActivity.class);
                String message = templateNamesArr[position];
                intent.putExtra("name", message);
                startActivity(intent);
            }
        });

    }
}
