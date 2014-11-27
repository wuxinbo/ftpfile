package com.wu.ftpfile.activity;

import android.support.v4.app.FragmentActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.wu.ftpfile.R;

/**
 * Created by wuxinbo on 2014/11/12.
 */
public abstract class MyfragmentActivity extends FragmentActivity {
    public  TextView nav_title;
    /**
     * 导航栏的设置
     */
    protected TextView nav_settext;

    /**
     * 温馨提示
     * @param stringResId
     */
    public  void print(int stringResId) {
        Toast.makeText(getApplicationContext(), this.getString(stringResId),
                Toast.LENGTH_SHORT).
                show();
    }
    protected   void initnavbar(){
        nav_settext = (android.widget.TextView) findViewById(R.id.nav_setting);
        nav_title   = (android.widget.TextView) findViewById(R.id.nav_title);
    }
    protected abstract void setview();
    protected abstract void initview();
    /**
     * 初始化activity
     */
    protected   abstract void initactivity();
}
