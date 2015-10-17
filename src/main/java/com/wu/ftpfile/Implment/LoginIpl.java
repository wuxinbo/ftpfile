package com.wu.ftpfile.Implment;

import android.content.Context;

import com.wu.ftpfile.model.UserInfo;
import com.wu.ftpfile.Interface.Loginclicklistener;
import com.wu.ftpfile.activity.LoginActivity;

/**
 * Created by wuxinbo on 2014/10/11.
 */
public class LoginIpl implements Loginclicklistener {
    private Context context;

    public LoginIpl(Context context) {
        this.context = context;
    }

    @Override
    public void login(UserInfo userinfo) {
        UserInfo user = userinfo;
        if (UserInfo.userIsNull(user)) {
//            throw new FTPconnectException("信息为空");
        } else {
            ((LoginActivity) context).gotofileinfoactivity();
        }
    }
}
