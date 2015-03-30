package com.wu.ftpfile.activity;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;

import com.wu.ftp.UserInfo;
import com.wu.ftpfile.Implment.LoginIpl;
import com.wu.ftpfile.Interface.preLoginlistener;
import com.wu.ftpfile.R;
import com.wu.ftpfile.UI.LoginButton;
import com.wu.ftpfile.model.Constant;
import com.wu.ftpfile.utils.DataBaseUtil;
import com.wu.ftpfile.utils.ExitApplication;

/**
 * 显示登录页面，并执行登录操作。
 * @author  wuxinibo
 */
public class LoginActivity extends MyActivity implements preLoginlistener {
    private String tag = "loginactivity";
    /**
     * 自定义登录按钮
     */
    protected LoginButton loginbutton;
    /**
     * 登录服务器所需的信息
     */
    protected UserInfo userinfo;
    /**
     * ip地址输入框
     */
    protected EditText login_url;
    /**
     * 密码输入框
     */
    protected EditText login_pwd;
    /**
     * 用户名输入框
     */
    protected EditText login_user;
    /**
     * 用户信息是否存在
     */
    protected boolean userInfoIsExist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginview);
        userInfoIsExist = DataBaseUtil.userInfoTableIsExist(this, Constant.TABLE_NAME);
        initactivity();
        ExitApplication.getInstance().addToList(this); //将activity添加到集合中。
    }


    @Override
    public UserInfo prelogin() {
        UserInfo user = loginbutton.getuserinfo(login_url.getText().toString(),
                login_user.getText().toString(),
                login_pwd.getText().toString());
            DataBaseUtil dataHelper =DataBaseUtil.getdataHelper(this);
            user.setEncoding("UTF-8");
            user.setCurrentUser("1");//登录时设置为默认用户。
            dataHelper.saveUserInfo(user);
            gotofileinfoactivity();
        return user;
    }

    /**
     * 跳转到文件浏览界面
     */
    public void gotofileinfoactivity() {
        Intent in = new Intent();
        in.setClass(this, FileInfoActivity.class);
        startActivity(in);
    }


    @Override
    protected void setview() {
        if (userInfoIsExist) {
            setloginvalue();
        }
        //登录按钮回调函数。
        loginbutton.setListener(this, new LoginIpl(this));
        //加载动画。
        Animation annimation = AnimationUtils.loadAnimation(this, R.anim.button_animation);
        //设置对象的动画。
        loginbutton.startAnimation(annimation);
    }

    private void setloginvalue() {
        login_pwd.setText(userinfo.getPassword());
        login_url.setText(userinfo.getUrl());
        login_user.setText(userinfo.getUsername());
    }

    @Override
    protected void initview() {
        loginbutton = (LoginButton) findViewById(R.id.login_button);
        login_url = (EditText) findViewById(R.id.login_url);
        login_pwd = (EditText) findViewById(R.id.login_pwd);
        login_user = (EditText) findViewById(R.id.login_user);
        userinfo = UserInfo.readUserinfo(this);
    }

    @Override
    public void initactivity() {
        initview();
        setview();
    }
}
