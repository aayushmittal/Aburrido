package com.example.aayush.aburrido;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by aayush on 12/6/16.
 */
public class ArrayAdapterClass extends ArrayAdapter<String> {
    Context context;
    ArrayList<String> list;
    public ArrayAdapterClass(Context context, ArrayList<String> list) {
        super(context, 0,list);
        this.context = context;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = View.inflate(context,R.layout.selector_list,null);
        }
        TextView textView = (TextView) convertView.findViewById(R.id.text);
        textView.setText(list.get(position));
        return convertView;
    }
}
