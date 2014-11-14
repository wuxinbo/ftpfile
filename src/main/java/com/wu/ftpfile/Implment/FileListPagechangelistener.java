package com.wu.ftpfile.Implment;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.wu.ftpfile.R;
import com.wu.ftpfile.activity.FileInfoActivity;

/**
 * Created by Administrator on 2014/11/14.
 */
public class FileListPagechangelistener implements ViewPager.OnPageChangeListener{
    private String tag=getClass().getName();
    private Context context;

    public FileListPagechangelistener(Context context) {
        this.context = context;
    }

    @Override
    public void onPageScrolled(int i, float v, int i2) {
        Log.d(tag,String.valueOf(i));
    }

    @Override
    public void onPageSelected(int i) {
        FileInfoActivity activity = (FileInfoActivity) context;
        switch (i){
            case 0:activity.nav_title.setText(activity.getString(R.string.title_activity_main));
                break;
            case 1:activity.nav_title.setText(activity.getString(R.string.title_local));
        }
        Log.d(tag,String.valueOf(i));
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}
