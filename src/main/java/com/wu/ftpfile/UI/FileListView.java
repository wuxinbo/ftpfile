package com.wu.ftpfile.UI;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.wu.ftpfile.AsyncTask.AsyncDownLoad;
import com.wu.ftpfile.AsyncTask.AsyncUpdatelist;
import com.wu.ftpfile.Interface.UpdatelistViewIn;
import com.wu.ftpfile.R;
import com.wu.ftpfile.activity.FileInfoActivity;
import com.wu.ftpfile.fragment.FileListFragment;
import com.wu.ftpfile.model.Constant;
import com.wu.ftpfile.model.FileInfo;

import java.io.File;
import java.util.List;

/**
 * Created by wuxinbo on 2014/10/21.
 */
public class FileListView extends ListView implements AdapterView.OnItemClickListener {
    /**
     * 上下文环境
     */
    private static Context context;
    private static final String tag = "IFlelistview";
    private UpdatelistViewIn updatelistview = null;
    private  FileInfoActivity fileInfoActivity;
    /**
     * serverfileinfofragmnet实例
     */
    private FileListFragment serverfileListFragment;
    private FileListFragment LocalfileListFragment;
    //    private static  SQLiteDatabase db=null;
//    private static DataBaseUtil dbhelper =null;
    private List<FileInfo> fileinfos = null;
    /**
     * 服务器上的文件路径
     */
    private StringBuffer serverpath = null;
    /**
     * 本地文件路径。
     */
    private StringBuffer localpath = null;
    /**
     * 第一次得到对象。
     */
    public static boolean isfirstRead = true;

    public FileListView(Context context) {
        super(context);
        initView(context);
    }

    public FileListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public FileListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setFileinfos(List<FileInfo> fileinfos) {
        this.fileinfos = fileinfos;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        final FileInfo fileinfo = fileinfos.get(position);
        final int fragmentnumber=((FileInfoActivity) context).getFragmnetnumber();
        serverpath = new StringBuffer(serverfileListFragment.getPath());
        localpath = new StringBuffer(LocalfileListFragment.getPath());
        serverpath = serverpath.append(File.separator
                + fileinfo.getFilename());
        if (fragmentnumber==Constant.SERVERFILE_FRAGMNET_NUMBER){
            if (fileinfo.getIsdir() == 1) {
                gotoDir(serverpath.toString());
            } else {
                final Button btn_download = (Button) view.findViewById(R.id.download_btn);
                final ProgressBar bar_download = (ProgressBar) view.findViewById(R.id.download_probar);
                btn_download.setVisibility(View.VISIBLE);
                bar_download.setVisibility(View.VISIBLE);
                fileinfo.setFilepath(serverpath.toString());
                btn_download.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AsyncDownLoad download = new AsyncDownLoad(((FileInfoActivity) context).ftp,
                                bar_download, btn_download);
                        download.execute(fileinfo);

                    }
                });
            }
        }else{
            String path=((FileInfoActivity) context).getFragmentInstance(Constant.LOCALFILE_FRAGMNET_NUMBER).getPath();
            localpath.append(File.separator+fileinfo.getFilename());
            if (fileinfo.getIsdir() == 1){
                gotoDir(localpath.toString());
            } else {
                clickfile(localpath.toString());
            }

        }
            serverpath=null;
            localpath=null;

    }

    /**
     * 根据传入的路径,进入相应的目录。
     */
    private void gotoDir(String path ){
        AsyncUpdatelist updatelist = new AsyncUpdatelist(this, context);
        updatelist.execute(path);

    }

    /**
     * 初始化控件
     */
    private void initView(Context context) {
        this.context = context;
        fileInfoActivity = ((FileInfoActivity) context);
        serverfileListFragment = fileInfoActivity.getFragmentInstance(
                Constant.SERVERFILE_FRAGMNET_NUMBER);
        LocalfileListFragment = fileInfoActivity.getFragmentInstance(
                Constant.LOCALFILE_FRAGMNET_NUMBER);

        setOnItemClickListener(this);
    }

    /**
     * 为Filelistview绑定更新列表回调函数。
     *
     * @param updatelistview <a href="#">UpdatelistViewIn</a>的实现。
     */
    public void setlistener(UpdatelistViewIn updatelistview) {
        this.updatelistview = updatelistview;
    }

    /**
     * 更新listview中的数据
     *
     * @param fileinfo file对象
     */
    public void updateListVIew(List<FileInfo> fileinfo) {
        this.fileinfos = fileinfo;
        updatelistview.updatelist(fileinfos);
    }

    /**
     * 将得到的数据按要求填入到map中，map存放的是和File相关的数据，
     * 例如文件大小，文件图标等。
     *
     * @param file 需要填入的File对象
     * @return 装载好的Hashmap
     */
    public static FileInfo FileInit(FileInfo file) {
        String fileInfo = file.getFilename();
        if (file.getIsdir() == 1) {
            file.setFile_pic(R.drawable.dir);
        } else if (isplay(fileInfo)) {
            file.setFile_pic(R.drawable.play);
        } else if (fileInfo.endsWith("mp3")) {
            file.setFile_pic(R.drawable.mp3);
        } else if (fileInfo.endsWith("doc")) {
            file.setFile_pic(R.drawable.doc);
        } else if (fileInfo.endsWith("xls")) {
            file.setFile_pic(R.drawable.xls);
        } else if (fileInfo.endsWith("exe")) {
            file.setFile_pic(R.drawable.exe);
        } else if (isZIP(fileInfo)) {
            file.setFile_pic(R.drawable.zip);
        } else if (ispicture(fileInfo)) {
            file.setHeadimg(FileInfo.getdrawable(file.getFilepath()));
            file.setFile_pic(0);
        } else if (isApk(fileInfo)) {
            file.setIcon(FileInfoActivity.getApkIcon(context, file.getFilepath()));
            file.setFile_pic(0);
        } else {
            file.setFile_pic(R.drawable.unkown);
        }
        return file;
    }

    /**
     * 判断是否为可播放文件
     *
     * @param filename 文件名
     * @return
     */
    private static boolean isplay(String filename) {
        String[] play = context.getResources().getStringArray(R.array.play);
        return isIncludeString(filename, play);
    }

    /**
     * 从给定的数组中寻找str。
     *
     * @param str  需要查询的string
     * @param play 需要查询的数组
     * @return 如果数组中某个字符串以str结尾则返回TRUE，如果没有符合条件的返回false。
     */
    private static boolean isIncludeString(String str, String[] play) {
        for (String name : play) {
            if (str.endsWith(name)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isZIP(String filename) {
        String[] zip = context.getResources().getStringArray(R.array.zip);
        return isIncludeString(filename, zip);
    }

    private static boolean isMusic(String filename) {
        String[] music = context.getResources().getStringArray(R.array.music);
        return isIncludeString(filename, music);
    }

    private static boolean ispicture(String filename) {
        String[] picture = context.getResources().getStringArray(R.array.picture);
        return isIncludeString(filename, picture);
    }

    /**
     * 判断是否是apk安装文件
     *
     * @param filename 文件名
     * @return 如果是apk文件返回true，否则返回flase。
     */
    private static boolean isApk(String filename) {
        if (filename.endsWith("apk")) {
            return true;
        }
        return false;
    }

    /**
     * 当点击本地可识别文件执行相应的操作。
     */
    private void clickfile(String filepath) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (isMusic(filepath)) {
            intent.setDataAndType(Uri.parse(filepath), "audio/mp3");
        } else if (isplay(filepath)) {
            intent.setDataAndType(Uri.parse(filepath), "video/mp4");
        } else if (ispicture(filepath)) {
            intent.setDataAndType(Uri.parse(filepath), "image/jpeg");
        }
        context.startActivity(intent);
    }
}

