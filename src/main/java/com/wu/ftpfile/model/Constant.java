package com.wu.ftpfile.model;

import android.os.Environment;

/**
 * 此类主要保存的是常量。
 *
 * @author wuxinbo
 */
public class Constant {
    /**
     * 菜单更新按钮的ID
     */
    public static final int UPDATE = 1;
    /**
     * 菜单关于按钮的ID
     */
    public static final int ABOUT = 2;
    /**
     * 菜单退出按钮的ID
     */
    public static final int EXIT = 3;
    /**
     * 菜单设置按钮的ID
     */
    public static final int SETTING = 4;
    /**
     * 建Fileinfo表，主要存储文件对象的相关信息。
     */
    public static final String TABLE_NAME="fileinfo";
    public static final String createtable = "create table "+TABLE_NAME+"(" +
                                             "id int," +
                                             "name varchar(30)," +
                                             "filesize varchar(5)," +
                                             "createtime varchar(10)," +
                                             "isdir int)";
    /**
     * sd卡的根目录
     */
    public static final String SD_ROOT_PATH= Environment.
                                             getExternalStorageDirectory().
                                             getAbsolutePath();
    /**
     * APP文件存放目录
     */
    public static final String APP_DIR_PATH="ftpfile";
    /**
     * 服务器下载到本地的目录。
     */
    public static final String APP_DIR_DOWNLOAD_PATH="download";
}
