package edu.westga.ryanflemingfinalproject.Model;
import android.app.Activity;
import android.database.DataSetObserver;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import edu.westga.ryanflemingfinalproject.R;

public class CustomArrayAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private  ArrayList<String> name;
    private  ArrayList<String> value;

    public CustomArrayAdapter(Activity context, ArrayList<String> name,  ArrayList<String> value)
    {
        super(context, R.layout.list_view_text_view, name);
        this.context = context;
        this.name = name;
        this.value = value;

    }@Override
    public View getView(int position, View view, ViewGroup parent)
    {

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_view_text_view, null, true);

        TextView txtName = (TextView) rowView.findViewById(R.id.list_text_name);

        TextView txtValue = (TextView) rowView.findViewById(R.id.list_text_value);
        txtName.setText(String.format("%s:", this.name.get(position)));
        txtValue.setText(String.format("$%s", this.value.get(position)));

        DisplayMetrics metrics = this.context.getResources().getDisplayMetrics();
        txtName.setWidth(metrics.widthPixels / 2);
        txtValue.setWidth(metrics.widthPixels / 2);
        return rowView;
    }
}
