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
     * 用户表表名。
     */
    public static final String TABLE_NAME = "USER_INFO";
    /**
     * 建用户表，主要存储用户相关信息。
     */
    public static final String createtable = "create table if not exists " + TABLE_NAME + "(" +
            "id integer primary key," + //表的主键
            "user_name varchar(30)," + //用户名
            "password varchar(20)," + //密码
            "host_name varchar(10)," + //ip地址
            "port varchar(5),"+ //端口号
            "encoding varchar(5),"+//服务器上的编码
            "login_time time)"; //最后登录时间

    /**
     * 数据库名
     */
    public static final String DATABASE_NAME="ftpfile";
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
