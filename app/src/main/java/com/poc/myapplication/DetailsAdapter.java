package com.poc.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DetailsAdapter extends ArrayAdapter<ItemTop>{

    public DetailsAdapter(Context context, ArrayList<ItemTop>lista){
        super(context, 0, lista);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if(view == null){
            ItemTop item = getItem(position);
            view = LayoutInflater.from(getContext()).inflate(R.layout.details_top_list_item, parent,false);
            TextView name = view.findViewById(R.id.name);
            name.setText(item.getName());
            TextView oyentes = view.findViewById(R.id.oyentes);
            String stringOyentes = "Oyentes: "+ item.getOyentes();
            oyentes.setText(stringOyentes);
            TextView url = view.findViewById(R.id.url);
            url.setText(item.getUrl());
        }

        return view;
    }
}
