package com.wu.ftpfile.AsyncTask;

import android.os.AsyncTask;

import com.wu.ftpfile.UI.FileListView;
import com.wu.ftpfile.model.Constant;
import com.wu.ftpfile.model.FileInfo;
import com.wu.ftpfile.utils.Fileutil;

import java.util.List;

/**
 * 利用后台线程来更新本地文件。
 * Created by Administrator on 2014/11/14.
 */
public class AsyncUpdatelocalList extends AsyncTask<String,List<FileInfo>,Void> {
    private FileListView filelistview =null;

    public AsyncUpdatelocalList(FileListView filelistview) {
        this.filelistview = filelistview;
    }

    @Override
    protected Void doInBackground(String... params) {
        List<FileInfo> fileInfos= Fileutil.getListfile(params[0]);
        publishProgress(fileInfos);
        return null;
    }

    @Override
    protected void onProgressUpdate(List<FileInfo>... values) {
        List<FileInfo> files=values[0];
        filelistview.updateListVIew(files);
    }
}
