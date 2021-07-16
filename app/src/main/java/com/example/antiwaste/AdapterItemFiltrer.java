package com.example.antiwaste;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;

import java.util.ArrayList;

public class AdapterItemFiltrer extends ArrayAdapter {
    ArrayList<String> typeListe ;

    public AdapterItemFiltrer(Context context, int textViewResourceId, ArrayList objects) {
        super(context, textViewResourceId, objects);
        typeListe = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.item_filtrer, null);
        CheckBox checkBox = v.findViewById(R.id.itemCheckBox);
        checkBox.setText(typeListe.get(position));

        return v;

    }


}
