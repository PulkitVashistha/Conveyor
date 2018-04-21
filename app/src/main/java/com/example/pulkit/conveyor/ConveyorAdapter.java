package com.example.pulkit.conveyor;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by pulkit on 4/14/2018.
 */

public class ConveyorAdapter extends ArrayAdapter<Conveyor> {

    public ConveyorAdapter(Activity context, ArrayList<Conveyor> Conveyors) {
        super(context, 0, Conveyors);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItem = convertView;

        if (listItem == null) {
            listItem = LayoutInflater.from(getContext()).inflate(R.layout.my_layout, parent, false);
        }
        final Conveyor temp = getItem(position);


        TextView bar = listItem.findViewById(R.id.barcode);
        bar.setText(temp.getBarcode());

        TextView weight = listItem.findViewById(R.id.weight);
        weight.setText(temp.getNumber());

        return listItem;
    }
}