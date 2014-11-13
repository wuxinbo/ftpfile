package com.wu.ftpfile.Implment;

import android.util.Log;

import com.wu.ftp.UserInfo;
import com.wu.ftpfile.Interface.Loginclicklistener;
import com.wu.ftpfile.exception.FTPconnectException;

/**
 * Created by wuxinbo on 2014/10/11.
 */
public class LoginIpl implements Loginclicklistener{
    @Override
    public void login(UserInfo userinfo) {
        UserInfo user =userinfo;
        if(UserInfo.userIsNull(user)){
//            throw new FTPconnectException("信息为空");
        }
        Log.d("login", "请登录");
    }
}
