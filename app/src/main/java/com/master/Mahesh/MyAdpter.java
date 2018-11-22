/*
package com.master.tablayoutwithdynamicfragments;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;


public class MyAdpter extends BaseAdapter {
    private ArrayList<String> data;
    private LayoutInflater inflater;
    private Context context;


    public MyAdpter(Context applicationContext,
                    ArrayList<String> pltdtllst) {
        this.data = pltdtllst;
        this.context = applicationContext;

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final String plot = data.get(position);
        if (inflater == null)
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.inflator_item_list, parent,false);
        TextView one = (TextView) convertView.findViewById(R.id.one);
        EditText two =  convertView.findViewById(R.id.two);
        two.setText("");



        one.setText(plot);

        return convertView;
    }


}
*/
