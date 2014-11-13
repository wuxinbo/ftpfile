package com.wu.ftpfile.Implment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.wu.ftpfile.activity.SetActivity;

import java.util.Map;

/**
 * Created by wuxinbo on 2014/10/18.
 */
public class setListViewItemclick implements AdapterView.OnItemClickListener {
    /**
     * 设置界面信息
     */
    private String [] setarray;
    /**
     * 上下文环境
     */
    private Context context;
    /**
     * 列表与之对应的activity名。
     */
    private Map<String,String> setMap;
    /**
     * activity 跳转
     */
    private Intent in =new Intent();
    public final String PACKAGE_NAME="com.wu.ftpfile.activity";
    private  SetActivity setactivity=null;
    public setListViewItemclick(Context context){
        setactivity=((SetActivity)context);
        this.context=context;
        setarray = setactivity.getSetArray();
        setMap=setactivity.getSetmap();

    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String activityName =setMap.get(setarray[position]);
        if (activityName.equals("exit")){
            setactivity.finish();
        }else{
        in.setClassName(context,PACKAGE_NAME+"."+activityName);
        context.startActivity(in);
        }
    }

//    /**
//     * 初始化监听器
//     */
//    private void initlistener(){
//
//
//    }
}
