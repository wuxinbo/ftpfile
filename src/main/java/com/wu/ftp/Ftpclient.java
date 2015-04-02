package com.wu.ftp;

import android.util.Log;

import com.wu.ftpfile.utils.Fileutil;

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
     * 判断是否为目录。
     * @param remotePath 远程文件路径
     * @return 如果是返回true，否则返回false；
     */
    public static boolean isDirectory(FTPClient ftp,String remotePath){
        try {
            return ftp.changeWorkingDirectory(remotePath);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 文件（文件夹）下载，
     * @param ftp ftp实例
     * @param remotePath 要下载的文件路径。
     * @param localPath 保存到本地文件位置。
     * @return 下载的文件大小
     */
    public static long downloadFile(FTPClient ftp,String remotePath,String localPath){
        long downloadsize=0L;//已经读取到的字节数
        File file =new File(remotePath);
        InputStream in=null;
        FileOutputStream os=null;
//		File DirFile = null;
        try{
            if (isDirectory(ftp,remotePath)) {
                FTPFile[] subFiles= ftp.listFiles(remotePath);
                for (FTPFile subFile : subFiles) {
                    Fileutil.createDir(localPath);//在本地创建同名的目录。
                    if (subFile.isDirectory()) {
                        Fileutil.createDir(localPath+"/"+subFile.getName()); //在本地创建子目录
                        ftp.changeWorkingDirectory(remotePath+"/"+subFile.getName());//切换当前目录到子目录
                        downloadsize+=downloadFile(ftp, ftp.printWorkingDirectory(), localPath+"/"+subFile.getName());
                    }else{
                        in= ftp.retrieveFileStream(remotePath+"/"+subFile.getName());
                        os =new FileOutputStream(new File(localPath+"/"+subFile.getName()));
                        byte [] data =new byte[4096]; //缓冲区数组。
                        int readSize=0;//每次读取到的字节
                        while((readSize=in.read(data))!=-1){
                            downloadsize+=readSize;
                            os.write(data,0,readSize);
                        }
                        in.close(); //关闭输入流
                        ftp.completePendingCommand();//关闭输入流之后，必须调用这个方法来向服务器确认操作成功。
                    }
                }
            }else{
                in= ftp.retrieveFileStream(remotePath);
                System.out.println(remotePath);
                os =new FileOutputStream(new File(localPath));
                byte [] data =new byte[4096]; //缓冲区数组。
                int readSize=0;//每次读取到的字节
                while((readSize=in.read(data))!=-1){
                    downloadsize+=readSize;
                    os.write(data,0,readSize);
//					System.out.println(downloadsize);
                }
                in.close(); //关闭输入流
                ftp.completePendingCommand();
            }
        }catch(IOException e){
        }
        finally{
            try {
//				in.close(); //关闭输入流

                os.close(); //关闭输出流
            } catch (IOException e) {
            }
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
    /**
     * 上传整个文件夹或者是文件
     * @param ftp
     * @param dirName 目录名
     * @return 如果上传成功返回true，否则返回FALSE；
     */
    public static boolean uploadDir(FTPClient ftp, String dirName){
        File file =new File(dirName);
        long uploadSize=0l;
        try {
            String path =ftp.printWorkingDirectory()+"/"+file.getName();
            if(file.isDirectory()){ //是否为目录
				/*在服务器上面新建一个和本地同名的目录*/
                makeDir(path, ftp);
                File[] files=file.listFiles();
                ftp.changeWorkingDirectory(path);//将工作目录变更为该目录下
                for (File subFile : files) {/*遍历该目录下的子文件。*/
                    if(subFile.isDirectory()){//判断子文件是否是文件夹。
                        makeDir(ftp.printWorkingDirectory()+"/"+subFile.getName(),ftp);//在服务器上面的父级目录下面新建该子目录。
                        uploadDir(ftp,dirName+"/"+subFile.getName());//利用递归再遍历它的子目录。
                    }else{
                        InputStream in =getInputStream(dirName+"/"+subFile.getName());//得到输入流
                        uploadSize+=uploadFile(ftp,path+"/"+subFile.getName(), in);//开始上传文件。
                        System.out.println("返回的代码："+ftp.getReply());
                        in.close();
                    }
                }
            }else{
                InputStream in =getInputStream(dirName);//得到输入流
                uploadSize+=uploadFile(ftp,path, in);//开始上传文件。
                in.close();
            }
        }catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
