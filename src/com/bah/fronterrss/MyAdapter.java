package com.bah.fronterrss;

import java.util.ArrayList;

import android.content.Context;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyAdapter extends ArrayAdapter<Item> {
	 
    private final Context context;
    private final ArrayList<Item> itemsArrayList;

    public MyAdapter(Context context, ArrayList<Item> itemsArrayList) {

        super(context, R.layout.list_item, itemsArrayList);

        this.context = context;
        this.itemsArrayList = itemsArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from inflater
        View rowView = inflater.inflate(R.layout.list_item, parent, false);

        // 3. Get the two text view from the rowView
        TextView titleView = (TextView) rowView.findViewById(R.id.itemTitle);
        TextView dateView = (TextView) rowView.findViewById(R.id.itemDate);
        TextView roomView = (TextView) rowView.findViewById(R.id.itemRoom);

        // 4. Set the text for textView
        titleView.setText(itemsArrayList.get(position).getTitle());
        Linkify.addLinks(titleView, Linkify.ALL);
        dateView.setText(itemsArrayList.get(position).getDate());
        roomView.setText(itemsArrayList.get(position).getRoom());

        // 5. return rowView
        return rowView;
    }
}