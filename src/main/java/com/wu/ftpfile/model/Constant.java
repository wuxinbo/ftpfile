package com.wu.ftpfile.model;

import android.os.Environment;

/**
 * 此类主要保存的是常量。
 *
 * @author wuxinbo
 */
public class Constant {
//    /**
//     * 菜单设置按钮的ID
//     */
//    public static final int SETTING = 4;
    /**
     * 建Fileinfo表，主要存储文件对象的相关信息。
     */
    public static final String TABLE_NAME = "userinfo";
    public static final String createtable = "create table " + TABLE_NAME + "(" +
            "id int," +
            "username varchar(30)," +
            "password varchar(5)," +
            "host_name varchar(10)," +
            "login_time time)";
    /**
     * sd卡的根目录
     */
    public static final String SD_ROOT_PATH = Environment.
            getExternalStorageDirectory().
            getAbsolutePath();
    /**
     * APP文件存放目录
     */
    public static final String APP_DIR_PATH = "ftpfile";
    /**
     * 服务器下载到本地的目录。
     */
    public static final String APP_DIR_DOWNLOAD_PATH = "download";
    /**
     * <a href="#">serverfilefragment<a/>在viewpage中的id
     */
    public static final int SERVERFILE_FRAGMNET_NUMBER = 0;
    /**
     * <a href="#">localfilefragment</a>在viewpager中的id。
     */
    public static final int LOCALFILE_FRAGMNET_NUMBER = 1;

    public static final int FILENAME_FORMAT_LENGTH = 23;
    public static final int DIR = 1;
//    public static final int FILE=0;
    /**
     * apk文件的datatype类型。
     */
    public static final String APK_INSTALL_DATATYPE =
            "application/vnd.android.package-archive";
    public static final String FILE_LOCALTION = "sdcard0:ftpfile\\download";
    /**
     * LOGINACTIVITY number
     */
    public static final int LOGINACTIVITY = 0;
    public static final int DIYACTIVITY = 2;
    public static final int SHAREACTIVITY = 3;
    public static final int ABOUTACTIVITY = 4;
}
