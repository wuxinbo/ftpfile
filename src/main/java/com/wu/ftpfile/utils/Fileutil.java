package com.wu.ftpfile.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.wu.ftp.UserInfo;
import com.wu.ftpfile.model.FileInfo;

public class Fileutil {



    /**
     * 根据给出的目录，创建相应的文件对象，
     * @param path
     * @param filename
     * @return
     */
    public static File createFile(String path,String filename){
		File file1 =new File(path+filename);
        createDir(path);
		if (!file1.exists()) {
			try {
				file1.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			file1.delete();
			try {
				file1.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return file1;
	}

    /**
     * 创建多级目录，
     * @param path 目录的路径。
     * @return
     */
    public static File createDir(String path){
        File dirfile=new File(path);
        if (!dirfile.exists()){
            dirfile.mkdirs();
            return dirfile;
        }
        return null;
    }
	/**
	 *  对给定的字符串进行格式化
	 * @param size 文件的大小
	 * @return 带有单位的字符串，如：B,KB,MB等
	 */
	public static String Format(long size){
		String filesize_str=null;
		//1KB=1024B
		final double KB=1024.0;
		//1MB=1024KB
		final double MB=KB*1024.0;
		//1GB=1024MB
		final double GB=MB*1024.0;
		double  filesize=size;
		//如果文件小于1KB.
		if (size<KB) {
			filesize_str=size+"B";
		}

		else if((size<MB)&&(size>=KB)){
			filesize=size/KB;
			filesize_str=FomatString(filesize)+"KB";
		}
		//��MB����ת����
		else if (size<GB&&(size>=MB)) {
			filesize=size/MB;
			filesize_str=FomatString(filesize)+"MB";
		}
		//��GB����ת����
		else if (size>=GB){
			filesize=size/GB;
			filesize_str=FomatString(filesize)+"GB";
		}
		
		return filesize_str;
	}
	/**
	 *  对文件大小进行格式化
	 * @param filesize   文件大小
	 * @return  格式化之后小数点后两位的字符串
	 */
	public static String FomatString(double filesize) {
		String filesize_str;
		filesize_str=String.valueOf(filesize);
		filesize_str=filesize_str.substring(0, filesize_str.
				indexOf(".")+2);
		return filesize_str;
	}
	public static void close(OutputStream os,InputStream is
			){
		if (os!=null) {
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else if (is!=null) {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

    /**
     * 判断配置文件是否存在
     * @param context  上下文环境
     * @param sharepreferenceName 配置文件名
     * @param key 文件中包含的key
     * @return 如果文件存在返回true，否则返回false
     */
    public static  boolean sharepreferenceIsExist(Context context,
                                          String sharepreferenceName,
                                          String key){
        SharedPreferences sharedpreference = ((Activity)context).
                                              getSharedPreferences(
                                              sharepreferenceName,1);
        if (sharedpreference.contains(key)){
            return true;
        }
            return false;
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

    /**
     *  通过给定的路径返回文件列表
     * @param path 路径
     * @return 封装好的文件列表。
     */
    public static List<FileInfo> getListfile(String path){
        File file =new File(path);
        List<FileInfo> fileInfos =new ArrayList<FileInfo>();
        for (File file1:file.listFiles()){
            FileInfo fileInfo=FileInfo.getinstance(file1.length(),
                                                    file1.getName(),
                                                    null,
                                                    file1.isDirectory());
            if (file1.isHidden()){
                continue;
            }
            fileInfos.add(fileInfo);
        }
        return fileInfos;
    }
}
