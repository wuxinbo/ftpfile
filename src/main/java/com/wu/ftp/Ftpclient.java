package com.wu.ftp;

import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;

/**
 * ftpclint 自定义客户端
 */
public class Ftpclient {

	/**
	 * 是否登陆成功
	 */
	private static boolean islogin=false;
	public static boolean isIslogin() {
		return islogin;
	}
	public static void setIslogin(boolean islogin) {
		Ftpclient.islogin = islogin;
	}
	
	/**
	 * 初始化FTPclient
	 */
	public static FTPClient  initFTP()  {
		FTPClient ftp =new FTPClient();
		 FTPClientConfig config = new FTPClientConfig(
				 FTPClientConfig.SYST_NT);
		 ftp.setControlEncoding("gbk"); //设置编码
		 ftp.setControlKeepAliveTimeout(300); // 超时时间
		 ftp.configure(config);
		return ftp;
	}
	/**
	 *  连接FTP服务器。
	 * @param ftp
	 * @param url
	 * @param password
	 * @param username
	 * @return
	 */
	public static FTPFile []  Login(FTPClient ftp,String url,String password,String username ){
		FTPFile[] files =null;
		FTPClient ftpclient=ftp;
		try {
			ftpclient.connect(url);
			islogin=ftpclient.login(username, password);
			files = ftpclient.listFiles("/");
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return files;
		
	}
	public static boolean changedir(String path){
		FTPClient client =new FTPClient();
		try {
			client.changeWorkingDirectory(path);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
//
}
