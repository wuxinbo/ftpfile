package com.wu.ftpfile.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.ThumbnailUtils;

import com.wu.ftpfile.utils.Fileutil;

import org.apache.commons.net.ftp.FTPFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 文件对象的实体类
 */
public class FileInfo implements Comparable<Object> {
    /**
     * 如果是文件则显示文件大小。
     */
    private String filesize;
    /**
     * 文件名
     */
    private String filename;
    /**
     * 文件创建时间。
     */
    private String creattime;
    /**
     * 是否为文件。
     */
    private int isdir = 0;
    private final static SimpleDateFormat sdf = new
            SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static boolean isdir(int dir) {
        if (dir == Constant.DIR) {
            return true;
        }
        return false;
    }


//    public boolean isHidden() {
//        return isHidden;
//    }

//    /**
//     * 根据传入的数字判断是否是隐藏文件。
//     * @param isHidden <p>1代表隐藏文件</p><p>0代表不是隐藏文件</p>
//     */
//    public void setHidden(int isHidden) {
//        if (isHidden==1) {
//            this.isHidden = true;
//        }
//    }

//    /**
//     * 该文件是否是隐藏文件。
//     */
//    private boolean isHidden;

    public int getFile_pic() {
        return file_pic;
    }

    public void setFile_pic(int file_pic) {
        this.file_pic = file_pic;
    }

    private int file_pic;

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    /**
     * 未格式化的文件大小。
     */
    private long size;
    /**
     * 文件的存放路径。
     */
    private String filepath;
    /**
     * 文件图标。
     */
    private Bitmap headimg;
    /**
     * 主要用于从apk文件中读取icon图标。
     */
    private Drawable icon;
    /**
     * 如果是文件夹类型，此变量为该文件夹下的文件个数。
     */
    private int fileCount;

    public Drawable getIcon() {
        return icon;
    }

    public void setfileCount(String dirpath) {
        File file = new File(dirpath);
        /*
        为了防止某些文件需要Root权限才能访问，而一般用户没有获取大Root权限，
        没有获取到Root很有可能就会报nullPointException.所以在这里捕获异常。
         */
        try {
            fileCount = file.listFiles().length;
        }catch(NullPointerException e)
        {
            fileCount = 0;
        }
    }

    public int getFileCount() {
        return fileCount;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public Bitmap getHeadimg() {
        return headimg;
    }

    public void setHeadimg(Bitmap headimg) {
        this.headimg = headimg;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public static FileInfo getinstance(long filesize,
                                       String filename,
                                       Calendar creattime,
                                       boolean isdir) {
        FileInfo fileinfo = new FileInfo();
        fileinfo.setIsdir(isdir);
        fileinfo.setFilesize(filesize);
        fileinfo.setFilename(filename);
        fileinfo.setCreattime(creattime);
        fileinfo.size = filesize;
        return fileinfo;
    }

    public String getFilesize() {
        return filesize;
    }

    public void setFilesize(long filesize) {
        if (isdir == 1) {
            this.filesize = "";
        } else {
            this.filesize = Fileutil.Format(filesize);
        }
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getCreattime() {
        return creattime;
    }

    public void setCreattime(Calendar creattime) {
        if (creattime == null) {
            this.creattime = "";
        } else {
            this.creattime = sdf.format(creattime.getTime());
        }
    }

    public int getIsdir() {
        return isdir;
    }

    public void setIsdir(boolean isdir) {
        int i = 0;
        if (isdir) {
            i = 1;
        }
        this.isdir = i;
    }

    /**
     * 对FileiNfo进行封装，
     *
     * @param files
     * @return
     */
    public static List<FileInfo> getFileInfoList(FTPFile[] files, String path) {
        List<FileInfo> fileinfos = new ArrayList<FileInfo>();
        if (files != null) {
            for (FTPFile ftpfile : files) {
                FileInfo fileInfo = FileInfo.getinstance(ftpfile.getSize(),
                        ftpfile.getName(),
                        ftpfile.getTimestamp(),
                        ftpfile.isDirectory());
                fileInfo.setFilepath(path + fileInfo.getFilename());
                fileinfos.add(fileInfo);
            }
            return fileinfos;
        }
        return null;
    }

    @Override
    public int compareTo(Object another) {
        FileInfo fileInfo = (FileInfo) another;
        return compareBydirfile(fileInfo);
    }

    /**
     * 重写比较方法，文件夹排在前面，文件排在后面。如果是文件夹在根据文件名字进行排名
     * @param fileInfo
     * @return 如果
     */
    private int compareBydirfile(FileInfo fileInfo) {
        if (this.getIsdir() < fileInfo.getIsdir()) {
            return 1;
        } else if (this.getIsdir() > fileInfo.getIsdir()) {
            return -1;
        }
        return compareByname(fileInfo);
    }

    /**
     * 对名字进行排序。
     *
     * @param fileInfo
     * @return
     */
    private int compareByname(FileInfo fileInfo) {
        int i = this.filename.compareToIgnoreCase(fileInfo.filename);
        if (i > 0) {
            return 1;
        } else if (i < 0) {
            return -1;
        } else {
            return compareBydirfile(fileInfo);
        }

    }

    public static Bitmap getdrawable(String filepath) {
        final int height = 100;
        final int width = 100;
        Bitmap bitmap = BitmapFactory.decodeFile(filepath);
        bitmap = ThumbnailUtils.extractThumbnail(bitmap, height, width);
        return bitmap;
    }

}
