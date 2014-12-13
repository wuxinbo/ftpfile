package com.wu.ftpfile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wu.ftpfile.R;
import com.wu.ftpfile.model.SetItemmodel;

/**
 * Created by Administrator on 2014/12/13.
 */
public class SetAdapter extends BaseAdapter {
    private final static int count = 2;
    private SetItemmodel[] array = null;
    private Context context;
    private LayoutInflater inflater;

    public SetAdapter(SetItemmodel[] aaray, Context context) {
        this.array = aaray;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return array.length;
    }

    @Override
    public Object getItem(int position) {
        return array[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.setlistview_layout, null);
        }
        String str = array[position].getItemval();
        TextView vaview = (TextView) convertView.findViewById(R.id.value);
        vaview.setText(str);
        return convertView;
    }
}
