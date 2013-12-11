package com.sdk.ui;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by wb-wangxiaolong on 13-12-11.
 */
public class DragGridViewAdapter extends ArrayAdapter<Object>{

    private  Context context = null;
    private List<Object> data;
    public DragGridViewAdapter(Context context, int resource, List<Object> objects) {
        super(context, resource, objects);
        this.context = context;
        data = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = new TextView(context);
        textView.setText((String)getItem(position));
        textView.setHeight(100);

        return textView;
    }
}
