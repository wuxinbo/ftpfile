package com.wu.ftpfile.AsyncTask;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import com.wu.ftp.Ftpclient;
import com.wu.ftp.UserInfo;
import com.wu.ftpfile.UI.FileListView;
import com.wu.ftpfile.activity.FileInfoActivity;
import com.wu.ftpfile.model.FileInfo;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by wuxinbo on 2014/10/21.
 */
public class AsyncConnectServer extends AsyncTask<UserInfo,List<FileInfo>,FTPClient> {
    private FileListView filelistview =null;
    private Context context;
    public AsyncConnectServer(FileListView listview,Context context ){
        filelistview=listview;
        this.context=context;
    }
    @Override
    protected FTPClient  doInBackground(UserInfo... params) {
        UserInfo user = params[0];
        FTPClient ftp = null;
        ftp = Ftpclient.initFTP();
        FTPFile[] files=Ftpclient.Login(ftp,
                    user.getUrl(),
                    user.getPassword(),
                    user.getUsername());
        List<FileInfo> fileinfos =FileInfo.getFileInfoList(files);
        publishProgress(fileinfos);
        fileinfos=null;
        return ftp;
    }

    @Override
    protected void onProgressUpdate(List<FileInfo>... values) {
        filelistview.updateListVIew(values[0]);
    }

    @Override
    protected void onPostExecute(FTPClient ftpClient) {
        ((FileInfoActivity)context).ftp=ftpClient;
    }
}

