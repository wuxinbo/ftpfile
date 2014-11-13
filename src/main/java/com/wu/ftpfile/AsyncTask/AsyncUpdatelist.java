package com.wu.ftpfile.AsyncTask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.wu.ftpfile.UI.FileListView;
import com.wu.ftpfile.activity.FileInfoActivity;
import com.wu.ftpfile.model.FileInfo;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by wuxinbo on 2014/10/23.
 */
public class AsyncUpdatelist extends AsyncTask<String,List<FileInfo>,Void> {
    private FTPClient ftp=null;
    private FileListView filelistview =null;
    public AsyncUpdatelist(FileListView listview,Context context){
        this.ftp=((FileInfoActivity)context).ftp;
        filelistview=listview;
    }
    @Override
    protected Void doInBackground(String... params) {
        String path=params[0];
        FTPFile[] files=null;
        if (ftp==null){
            Log.d("updatelist","ftp is null");
        }
        try {
            files = ftp.listFiles(path);
            List<FileInfo> fileinfos =FileInfo.getFileInfoList(files);
            publishProgress(fileinfos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(List<FileInfo>... values) {
        List<FileInfo> files=values[0];
        filelistview.updateListVIew(files);
    }
}
