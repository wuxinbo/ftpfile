package com.wu.ftp;

import java.io.Serializable;
import java.util.Date;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;


/**
 * 用户信息
 * @author wuxinbo
 *
 */
public class UserInfo  implements Serializable {
    /**
     * 服务器ip或者域名
     */
    private String url;
    /**
     * 用户名
     */
	private String username;
    /**
     * 密码。
     */
	private String password;
    /**
     *FTP连接端口号。
     */
    private String port;
    /**
     * 编码
     */
    private String Encoding;
    /**
     * 是否为当前用户，默认不是当前用户。
     */
    private boolean currentUser;
    /**
     * 用户登录时间。
     */
    public Date loginTime;

    public void setCurrentUser(boolean currentUser) {
        this.currentUser = currentUser;
    }

    public Date getLoginTime() {
        if (loginTime==null){
            loginTime =new Date();
        }
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String isCurrentUser() {
        if (currentUser==true){
            return "1";
        }else{
            return "0";
        }
    }

    /**
     * 根据传入的是0或者1进行判断，如果是1就赋值true，为0赋值为FALSE。
     * @param currentUser
     *
     */
    public void setCurrentUser(String currentUser) {
        if (currentUser!=null&&currentUser.equals("0")){
            this.currentUser = false;
        }else if (currentUser!=null&&currentUser.equals("1")){
            this.currentUser = true;
        }

    }

    public String getEncoding() {
        return Encoding;
    }

    public void setEncoding(String encoding) {
        Encoding = encoding;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    private static UserInfo server= new UserInfo();
	
	/**
	 *  工厂方法得到服务器实例
	 * @param Url ip地址
	 * @param Username 用户名
	 * @param Password	密码
	 * @return 实例化后的userinfo
	 */
	public static UserInfo getServerInstance(String Url,String Username,String Password){
		
		server.url=Url;
		server.password=Password;
		server.username=Username;
		return server;
	}

    /**
     * 读取用户信息，
     * @param context 上下文环境
     * @return 用户信息对象
     */
    public static UserInfo readUserinfo(Context context){
        UserInfo user = new UserInfo();
       SharedPreferences userinfo= ((Activity)context).
                                    getSharedPreferences("userinfo",1);

        user.setPassword(userinfo.getString("pwd",""));
        user.setUsername(userinfo.getString("username",""));
        user.setUrl(userinfo.getString("url",""));
        return user;
    }

    public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
    /**
     * 判断用户信息是否为空
     * @param user
     * @return 如果是空返回true,否则返回false
     */
    public static boolean userIsNull(UserInfo user ){
        if(user.getUrl().isEmpty()||
                user.getUsername().isEmpty()||
                user.getPassword().isEmpty()){
            return true;
        }
        return false;
    }
	
}
