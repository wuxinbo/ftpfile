package com.wu.ftpfile.Interface;

import com.wu.ftp.UserInfo;
import com.wu.ftpfile.exception.FTPconnectException;

/**
 * Created by wuxinbo on 2014/10/11.
 */
public interface Loginclicklistener {
    /**
     * 点击登录时触发该操作
     */
    void login(UserInfo userinfo) ;


}
