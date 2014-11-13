package com.wu.ftp;

import java.io.Serializable;

import android.app.Application;


/**
 * 用户信息
 * @author wuxinbo
 *
 */
public class UserInfo  extends Application implements Serializable {
	private String url;
	private String username;
	private String password;
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
