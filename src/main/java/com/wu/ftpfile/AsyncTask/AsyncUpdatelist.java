package com.wu.ftpfile.AsyncTask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.wu.ftpfile.UI.FileListView;
import com.wu.ftpfile.activity.FileInfoActivity;
import com.wu.ftpfile.model.Constant;
import com.wu.ftpfile.model.FileInfo;
import com.wu.ftpfile.utils.Fileutil;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.IOException;
import java.util.List;

/** 获取文件信息并更新listview.
 * Created by wuxinbo on 2014/10/23.
 */
public class AsyncUpdatelist extends AsyncTask<String,List<FileInfo>,Void> {
    private FTPClient ftp=null;
    private FileListView filelistview =null;
    private String path;
    public AsyncUpdatelist(FileListView listview,Context context){
        this.ftp=((FileInfoActivity)context).ftp;
        filelistview=listview;
    }
    @Override
    protected Void doInBackground(String... params) {
        path=params[0];
        FTPFile[] files=null;
        List<FileInfo> fileinfos=null;

        if (!isServerfileFragment()){
             fileinfos=  Fileutil.getListfile(path);
        }
        else{
        try {
            if (ftp==null){
                Log.d("updatelist","ftp is null");
            }else{
            files = ftp.listFiles(path);
             fileinfos =FileInfo.getFileInfoList(files);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        }
        publishProgress(fileinfos);
        return null;
    }

    /**
     *  根据路径来判断属于哪个fragment。
     * @return 如果为TRUE说明是serverfilefragment,否则是Localfilefragment。
     */
    private boolean isServerfileFragment(){
        if (path.startsWith(Constant.SD_ROOT_PATH)){
            return false;
        }
        return true;
    }
    @Override
    protected void onProgressUpdate(List<FileInfo>... values) {
        List<FileInfo> files=values[0];
        filelistview.updateListVIew(files);
        if (isServerfileFragment()){
            filelistview.getServerfileListFragment().getPath_view().setText(path);
        }
        filelistview.getLocalfileListFragment().getPath_view().setText(path);
    }
}
