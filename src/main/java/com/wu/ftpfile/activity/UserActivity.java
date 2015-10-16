package com.wu.ftpfile.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.wu.ftpfile.model.UserInfo;
import com.wu.ftpfile.R;
import com.wu.ftpfile.UI.LevelItem;
import com.wu.ftpfile.model.SetItemmodel;
import com.wu.ftpfile.utils.DataBaseUtil;

/**
 * 用户信息管理界面
 * Created by wuxinbo on 2015/3/27.
 */
public class UserActivity extends MyActivity{
    /**
     * 显示当前用户信息。
     */
    private TextView userView;
    private TextView exitView;

    @Override
    protected void setview() {
        nav_title.setText(getString(R.string.userInfo));//设置导航栏的表题。
        nav_settext.setVisibility(View.INVISIBLE); //让设置按钮不可见
        UserInfo userInfo =DataBaseUtil.getdataHelper(this).getUserInfoFromDataBase();
        userView.setText(userInfo.getUsername());//显示当前用户信息
//        Log.d("username",userInfo.getUsername());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_layout);
        initactivity();
    }

    /**
     * 注销当前账号。
     * @param v
     */
    public void logout(View v){
        Intent in =new Intent();
        in.setClass(this, LoginActivity.class);
        startActivity(in);
    }

    /**
     * 进入用户信息修改页面
     * @param v
     */
    public  void gotoUserInfoActivity(View v){
        Intent in =new Intent();
        in.setClass(this,UserInfoActivity.class);
        startActivity(in);
    }
    @Override
    protected void initview() {
        userView =(TextView)findViewById(R.id.userView);
        exitView= (TextView) findViewById(R.id.exitView);
    }

    @Override
    public void initactivity() {
        initnavbar();
        initview();
        setview();
    }

}
