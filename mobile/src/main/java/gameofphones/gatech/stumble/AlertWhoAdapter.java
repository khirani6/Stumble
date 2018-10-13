package gameofphones.gatech.stumble;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AlertWhoAdapter extends ArrayAdapter {

    private final Activity context;
    private final String[] nameArray;


    public AlertWhoAdapter(Activity context, String[] nameArrayParam){

        super(context,R.layout.alertwho_row , nameArrayParam);
        this.context = context;
        this.nameArray = nameArrayParam;

    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.alertwho_row, null,true);

        //this code gets references to objects in the listview_row.xml file
        TextView nameTextField = (TextView) rowView.findViewById(R.id.nameView);


        //this code sets the values of the objects to values from the arrays
        nameTextField.setText(nameArray[position]);

        return rowView;

    };

}

