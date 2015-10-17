package com.wu.ftpfile.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.wu.ftpfile.R;
import com.wu.ftpfile.UI.UserItemView;
import com.wu.ftpfile.model.UserInfo;
import com.wu.ftpfile.utils.DataBaseUtil;

/**
 * 用户信息详细管理
 * Created by wuxin on 2015/8/8.
 */
public class UserInfoActivity extends MyActivity{
    /**
     * 用户名
     */
    private UserItemView userNameView;
    /**
     * 主机地址
     */
    private UserItemView hostView;
    /**
     * 密码
     */
    private UserItemView passwordView;
    @Override
    protected void setview() {
        UserInfo userInfo = DataBaseUtil.getdataHelper(this).getUserInfoFromDataBase();
        nav_title.setVisibility(View.INVISIBLE);
        nav_settext.setText(getResources().getText(R.string.save));
        nav_settext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserInfo();
            }
        });
        hostView.setValueForView(userInfo.getUrl());
        userNameView.setValueForView(userInfo.getUsername());
        passwordView.setValueForView(userInfo.getPassword());
    }

    @Override
    protected void initview() {
        hostView = (UserItemView) findViewById(R.id.host);
        userNameView=(UserItemView) findViewById(R.id.username);
        passwordView=(UserItemView) findViewById(R.id.password);
    }

    @Override
    public void initactivity() {
        initview();
        setview();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userinfo);
        initactivity();
    }

    /**
     * 保存用户信息
     */
    public void saveUserInfo(){
        Log.d("save","save successful!");
    }
}
