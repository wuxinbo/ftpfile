package com.wu.ftpfile.activity;

import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;

import com.wu.ftp.UserInfo;
import com.wu.ftpfile.Implment.LoginIpl;
import com.wu.ftpfile.Interface.preLoginlistener;
import com.wu.ftpfile.R;
import com.wu.ftpfile.UI.LoginButton;
import com.wu.ftpfile.utils.Fileutil;
import com.wu.ftp.UserInfo;
public class LoginActivity extends MyActivity  implements preLoginlistener {
    private String tag="loginactivity";
    /**
     * 自定义登录按钮
     */
    private LoginButton loginbutton;
    /**
     * 登录服务器所需的信息
     */
    private UserInfo userinfo;
    /**
     * ip地址输入框
     */
    private EditText login_url;
    /**
     * 密码输入框
     */
    private EditText login_pwd;
    /**
     * 用户名输入框
     */
    private EditText login_user;
    /**
     * 用户信息是否存在
     */
    private  boolean userinfoIsExist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginview);
        loginbutton= (LoginButton) findViewById(R.id.login_button);
         login_url= (EditText) findViewById(R.id.login_url);
         login_pwd= (EditText) findViewById(R.id.login_pwd);
         login_user= (EditText) findViewById(R.id.login_user);
        userinfoIsExist =Fileutil.
                sharepreferenceIsExist(this,"userinfo","url");
        //登录按钮回调函数。
        loginbutton.setListener(this,new LoginIpl());
        //加载动画。
        Animation annimation = AnimationUtils.loadAnimation(this,R.anim.button_animation);
        //设置对象的动画。
        loginbutton.startAnimation(annimation);
        if (userinfoIsExist){
            initactivity();
        }

    }


    @Override
    public UserInfo prelogin() {
      UserInfo  user=loginbutton.getuserinfo(login_url.getText().toString(),
                                login_user.getText().toString(),
                                login_pwd.getText().toString());
        if (!userinfoIsExist){
            saveuserinfo(user);
        }else{

        }
        return user;
    }

    /**
     * 将用户信息保存在本地，
     * @param user 用户信息
     */
    private void saveuserinfo(UserInfo user) {
        SharedPreferences userinfo=getSharedPreferences("userinfo",1);
        SharedPreferences.Editor edit =userinfo.edit();
        if (!UserInfo.userIsNull(user)){
        edit.putString("url",user.getUrl());
        edit.putString("username",user.getUsername());
        edit.putString("pwd",user.getPassword());
        edit.commit();}
        else{
            print("不能为空");
        }
    }



    @Override
    public void initactivity() {
        UserInfo  userinfo =Fileutil.readUserinfo(this);
        login_pwd.setText(userinfo.getPassword());
        login_url.setText(userinfo.getUrl());
        login_user.setText(userinfo.getUsername());
    }
}
