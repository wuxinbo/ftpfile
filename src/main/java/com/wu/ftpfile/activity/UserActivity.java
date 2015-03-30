package com.wu.ftpfile.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.wu.ftpfile.R;
import com.wu.ftpfile.UI.LevelItem;

/**
 * 用户信息管理界面
 * Created by wuxinbo on 2015/3/27.
 */
public class UserActivity extends MyActivity{
    /**
     * 显示当前用户信息。
     */
    private LevelItem userView;
    private TextView exitView;
    @Override
    protected void setview() {
        nav_title.setText(getString(R.string.userInfo));//设置导航栏的表题。
        nav_settext.setVisibility(View.INVISIBLE); //让设置按钮不可见
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userinfo_layout);
        initactivity();
    }

    /**
     * 注销当前账号。
     * @param v
     */
    public void logout(View v){
        Intent in =new Intent();
        in.setClass(this,LoginActivity.class);
        startActivity(in);
    }
    @Override
    protected void initview() {
        userView =(LevelItem)findViewById(R.id.userView);
        exitView= (TextView) findViewById(R.id.exitView);
    }

    @Override
    public void initactivity() {
        initnavbar();
        initview();
        setview();
    }
}
