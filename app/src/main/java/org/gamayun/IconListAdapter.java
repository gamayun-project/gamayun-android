package org.gamayun;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class IconListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] itemsNames;
    private final Integer[] imagesIds;

    public IconListAdapter(Activity context, String[] itemsNames, Integer[] imagesIds) {
        super(context, R.layout.icon_list_view, itemsNames);
        this.context=context;
        this.itemsNames = itemsNames;
        this.imagesIds = imagesIds;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.icon_list_view, null, true);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.name);
        txtTitle.setText(itemsNames[position]);
        imageView.setImageResource(imagesIds[position]);
        return rowView;

    }
}