package com.wu.ftpfile.UI;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wu.ftpfile.R;
import com.wu.ftpfile.model.SetItemmodel;

import java.util.HashMap;
import java.util.Map;

/**
 * 设置界面的组合控件，用于显示设置条目。
 * Created by Administrator on 2014/12/14.
 */
public class LevelItem extends LinearLayout implements View.OnClickListener {
    private TextView itemNameview;
    private TextView itemvalview;
    private Context context;
    private String [] array=null;
    /**
     * 存放activity名字。和对应的item。
     */
    private Map<String,String> activityMaps= new HashMap<String,String>();
    public LevelItem(Context context) {
        super(context);
    }

    public LevelItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initwidgt(context);
    }

    private void initMap(){
        activityMaps.put(array[0], context.getPackageName() + ".activity.UserActivity");
        activityMaps.put(array[1],"");
        activityMaps.put(array[2],"");
        activityMaps.put(array[3], context.getPackageName() + ".activity.AboutActivity");
    }
    /**
     * 初始化控件。
     *
     * @param context 上下文环境。
     */
    private void initwidgt(Context context) {
        this.context=context;
        View view = LayoutInflater.from(context).inflate(R.layout.setlistview_layout, this);
        array=context.getResources().getStringArray(R.array.setting);//得到设置界面的集合。
        itemNameview = (TextView) view.findViewById(R.id.SetText);
        itemvalview = (TextView) view.findViewById(R.id.value);
        setBackgroundColor(Color.WHITE);//设置背景色为白色。
        setOnClickListener(this);
        initMap();
    }

    /**
     * 给控件赋值
     */
    public void setViewval(SetItemmodel model) {
        itemNameview.setText(model.getItemname());
        itemvalview.setText(model.getItemval());
    }

    /**
     * 点击事件根据用户点击的item进行跳转
     * @param v
     */
    @Override
    public void onClick(View v) {
       TextView text= (TextView)v.findViewById(R.id.SetText);
       Intent in= new Intent();
       for (String str:array){
            //比对Map中存放的数据和和用户点击的数据。符合规则进行跳转。
           if (text.getText().toString().equals(str) && !activityMaps.get(str).equals("")) {
                //根据存放在数组Map的activity名，来决定跳转到对应的activity。
                in.setClassName(context, activityMaps.get(str));
                context.startActivity(in);
                break;
            }
       }
    }
}
