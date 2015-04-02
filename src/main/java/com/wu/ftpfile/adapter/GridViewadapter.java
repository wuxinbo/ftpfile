package com.wu.ftpfile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wu.ftpfile.R;
import com.wu.ftpfile.model.SubMenu;

/**
 * gridView 的适配器。
 * Created by Administrator on 2015/4/2.
 */
public class GridViewadapter  extends BaseAdapter{
    /**
     * 菜单选项。
     */
    private  static  SubMenu[] menus ;
    private Context context;
    private SubMenu subMenu;
    public GridViewadapter(Context context) {
        this.context = context;
        menus = new SubMenu[]{new SubMenu(context.getString(R.string.upload)),
                              new SubMenu(context.getString(R.string.download))};
    }

    @Override
    public int getCount() {
        return menus.length;
    }

    @Override
    public Object getItem(int position) {
        return menus[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.grid_layout,null);
        }
        subMenu =menus[position];
        TextView menuName = (TextView) convertView.findViewById(R.id.gridView_textView);
        menuName.setText(subMenu.getName());
        return convertView;
}
}
