package gameofphones.gatech.stumble;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ECListAdapter extends ArrayAdapter {


    //been referencing from this tutorial: https://appsandbiscuits.com/listview-tutorial-android-12-ccef4ead27cc

    //to reference the Activity
    private final Activity context;

    //to store the list of EC's
    private final ArrayList<EmergencyContact> nameArray;

    public ECListAdapter(Activity context, ArrayList<EmergencyContact> nameArrayParam){

        super(context,R.layout.ec_list_row , nameArrayParam);
        this.context=context;
        this.nameArray = nameArrayParam;

    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.ec_list_row, null, true);


        TextView nameTextField = (TextView) rowView.findViewById(R.id.nameTextView);
        for(EmergencyContact i : nameArray) nameTextField.setText(i.toString());
        return rowView;

    }
}
