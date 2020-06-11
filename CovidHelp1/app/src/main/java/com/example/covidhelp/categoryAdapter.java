package com.example.covidhelp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class categoryAdapter extends BaseAdapter {

    Context context;

    @NonNull
    Category[] categories;

    LayoutInflater inflater;

    public categoryAdapter(Context context, @NonNull Category[] categories) {
        this.context = context;
        this.categories = categories;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return categories.length;
    }

    @Override
    public Object getItem(int i) {
        return categories[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.snippet_category, parent, false);
        }

        // get current item to be displayed


        // get the TextView for item name and item description
        TextView textViewItemName = (TextView)
                convertView.findViewById(R.id.categoryInnerTV);


        //sets the text for item name and item description from the current item object
        textViewItemName.setText(categories[position].getCategory());


        // returns the view for the current row
        return convertView;
    }
}
