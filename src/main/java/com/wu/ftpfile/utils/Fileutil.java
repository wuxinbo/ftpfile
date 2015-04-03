package com.wu.ftpfile.utils;

import android.content.Context;
import android.util.Log;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.wu.ftpfile.R;
import com.wu.ftpfile.UI.FileListView;
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
     * 获取到文件或文件夹大小
     * @param path 文件路径
     * @return 文件或文件夹的大小。
     */
    public static long getFileSize(String path){
        File file =new File(path);
        long size=0L;
        if (file.isDirectory()) {//判断是否为目录
            File[] subFiles= file.listFiles();//获取到该目录下的子文件
            for (File subFile : subFiles) {
                if (subFile.isDirectory()) {
                    size+=getFileSize(path+"/"+subFile.getName());//利用递归获取到子文件大小。再进行累加
                }else{
                    size+=subFile.length();//如果子文件是文件在进行累加。
                }
            }
        }else{
            size+=file.length();//如果是文件在进行累加。
        }
        return size;
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
            fileInfo.setFilepath(path + File.separator + file1.getName());
            /*
            判断当前文件是文件夹。
             */
            if (FileInfo.isdir(fileInfo.getIsdir())) {
                fileInfo.setfileCount(fileInfo.getFilepath());
            }
            if (file1.isHidden()){
                continue;
            }
            fileInfos.add(fileInfo);

        }
        return fileInfos;
    }

    /**
     * 应用程序申请Root权限
     * @param command 需要执行的命令
     *  @return  获取成功返回true，否则返回false；
     */
    public static boolean getRoot(String command){
        Process process = null;
        DataOutputStream os = null;
        try {
            process = Runtime.getRuntime().exec("su");
//            os = new DataOutputStream(process.getOutputStream());
//            os.writeBytes（command+"\n"）；\\os.writeBytes("echo \"Do I have root?\" >/system/sd/temporary.txt\n");
//            os.writeBytes（"exit\n"）；
//            os.flush（）；
            process.waitFor();
            } catch (Exception e) {
            Log.d("ROOT", "the device is not rooted， error message： " + e.getMessage());
            return false;
            } finally {
//                os.close();
            process.destroy();
            }

            return true;
    }

    /**
     * 是否是压缩文件
     * @param filename 文件名
     * @return 如果是返回true，否则返回FALSE。
     */
    public static boolean isZIP(Context context,String filename) {
        String[] zip = context.getResources().getStringArray(R.array.zip);
        return isIncludeString(filename, zip);
    }

    /**
     * 从给定的数组中寻找str。
     *
     * @param str  需要查询的string
     * @param play 需要查询的数组
     * @return 如果数组中某个字符串以str结尾则返回TRUE，如果没有符合条件的返回false。
     */
    public static boolean isIncludeString(String str, String[] play) {
        for (String name : play) {
            if (str.endsWith(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否是apk安装文件
     *
     * @param filename 文件名
     * @return 如果是apk文件返回true，否则返回flase。
     */
    public static boolean isApk(String filename) {
        if (filename.endsWith("apk")) {
            return true;
        }
        return false;
    }

    /**
     * 是否是图片。
     * @param filename 文件名
     * @return 如果是返回true，否则返回FALSE。
     */
    public  static boolean ispicture(Context context,String filename) {
        String[] picture = context.getResources().getStringArray(R.array.picture);
        return isIncludeString(filename, picture);
    }

    /**
     * 判断是否为可播放文件
     *
     * @param filename 文件名
     * @return
     */
    public static boolean isplay(Context context,String filename) {
        String[] play = context.getResources().getStringArray(R.array.play);
        return isIncludeString(filename, play);
    }

    /**
     * 是否是音乐文件
     * @param filename 文件名
     * @return 如果是返回true，否则返回FALSE。
     */
    public  static boolean isMusic(Context context,String filename) {
        String[] music = context.getResources().getStringArray(R.array.music);
        return isIncludeString(filename, music);
    }
}
