package com.wu.ftpfile.UI;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import com.wu.ftpfile.model.UserInfo;
import com.wu.ftpfile.Interface.Loginclicklistener;
import com.wu.ftpfile.Interface.preLoginlistener;

/**
 * 自定义登录按钮，并且重写监听器
 * Created by wuxinbo on 2014/10/10.
 */
public class LoginButton  extends Button implements View.OnClickListener{
    /**
     *  登录按钮的回调函数
     */
    private  Loginclicklistener login;
    /**
     * 登录服务器所需的信息。
     */
    private UserInfo userinfo;
    /**
     * 登录前的回调函数
     */
    private preLoginlistener preloginlistener;
    public LoginButton(Context context) {
        super(context);
    }

    /**
     *  从xml文件生成对象
     * @param context
     * @param attrs
     */
    public LoginButton(Context context,AttributeSet attrs){
        super(context, attrs, 0);
        setOnClickListener(this);
    }
    //绑定登录按钮的回调函数。
    public void setListener( preLoginlistener prelogin,Loginclicklistener loginlistener){
        login=loginlistener;
        preloginlistener=prelogin;
    }

    @Override
    public void onClick(View v) {
        userinfo=preloginlistener.prelogin();
        if (login!=null){
            login.login(userinfo);
        }
    }

    /**
     * 根据传入的参数实例化对象
     * @param url ip地址
     * @param username 用户名
     * @param pwd 密码
     * @return 实例化之后的对象
     */
    public UserInfo getuserinfo(String url, String username, String pwd){
        UserInfo user= UserInfo.getServerInstance(url,username,pwd);
        return user;
    }


}
