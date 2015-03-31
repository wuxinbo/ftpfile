package com.wu.ftp;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
		 FTPClientConfig config = new FTPClientConfig();
		 ftp.setControlEncoding("utf-8"); //设置编码
		 ftp.setControlKeepAliveTimeout(300); // 超时时间
		 ftp.configure(config);
		return ftp;
	}
	/**
	 *  对连接FTP服务器进行封装。
	 * @param ftp
	 * @param url
	 * @param password
	 * @param username
	 * @return
	 */
	public static FTPFile []  Login(FTPClient ftp,String url,String password,String username )throws SocketException,IOException{
		FTPFile[] files =null;
            ftp.connect(url);
			islogin=ftp.login(username, password);
			files = ftp.listFiles("");
            if (ftp.isConnected()){
                ftp.disconnect();
            }
		return files;
		
	}
    /**
     * 上传文件，自定义上传
     * @throws IOException
     */
    public static long  uploadFile(FTPClient ftp, String remoteFileName,InputStream in) throws IOException{
        OutputStream os = ftp.storeFileStream(remoteFileName);
        byte [] data =new byte[4096];
        long uploadsize=0l;//已经读取到的字节数
        int readSize=0;//每次读取到的字节
        while((readSize=in.read(data))!=-1){
            uploadsize+=readSize;
            os.write(data,0,readSize);
            System.out.println(uploadsize);
        }
        in.close();
        os.close();
        return uploadsize;

    }
    /**
     * 文件（文件夹）下载，
     * @param ftp ftp实例
     * @param file 要下载的文件。
     * @return
     */
    public static long downloadFile(FTPClient ftp,FTPFile file,String localPath){
        long downloadsize=0L;//已经读取到的字节数
        try{
            if (file.isDirectory()) {

            }else{
                InputStream in= ftp.retrieveFileStream(file.getName());
                FileOutputStream os =new FileOutputStream(new File(localPath));
                byte [] data =new byte[4096];

                int readSize=0;//每次读取到的字节
                while((readSize=in.read(data))!=-1){
                    downloadsize+=readSize;
                    os.write(data,0,readSize);
                    System.out.println(downloadsize);
                }
            }
        }catch(IOException e){

        }
        return downloadsize;
    }
    /**
     * 根据文件名生成Inputstream
     * @param localFileName
     * @return
     * @throws FileNotFoundException
     */
    public static InputStream getInputStream(String localFileName) throws FileNotFoundException {
        return new FileInputStream(new File(localFileName));
    }
    /**
     * 在服务器上面新建一个目录。
     * @param remoteFileName 目录名
     * @param ftp 已经登录到服务器的FTPClinet实例
     * @return 新建成功返回true，否则返回FALSE；
     * @throws IOException
     */
    public static boolean makeDir(String remoteFileName,FTPClient ftp) throws IOException{
        return ftp.makeDirectory(remoteFileName);
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
