package com.wu.ftpfile.AsyncTask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.wu.ftpfile.model.Ftpclient;
import com.wu.ftpfile.model.UserInfo;
import com.wu.ftpfile.R;
import com.wu.ftpfile.UI.FileListView;
import com.wu.ftpfile.activity.FileInfoActivity;
import com.wu.ftpfile.model.Constant;
import com.wu.ftpfile.model.FileInfo;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.IOException;
import java.net.SocketException;
import java.util.List;

/**
 * 连接FTP服务器的后台线程。
 * Created by wuxinbo on 2014/10/21.
 */
public class AsyncConnectServer extends AsyncTask<UserInfo, List<FileInfo>, FTPClient> {
    private FileListView filelistview = null;
    private FileInfoActivity fileInfoActivity=null;

    public AsyncConnectServer(FileListView listview, Context context) {
        filelistview = listview;
        fileInfoActivity = (FileInfoActivity)context;
    }

    @Override
    protected FTPClient doInBackground(UserInfo... params) {
        UserInfo user = params[0];
        FTPClient ftp = Ftpclient.initFTP();
        FTPFile[] files = null;
        try {
            files = Ftpclient.Login(ftp,user);
            Log.d("replay",String.valueOf(ftp.getReplyCode()));
            fileInfoActivity.getFragmentInstance(Constant.SERVERFILE_FRAGMNET_NUMBER)
                    .setPath(ftp.printWorkingDirectory());//设置服务器上面的路径。
        } catch (SocketException e) {
            Log.e("SocketException",e.getMessage());
            List<FileInfo> fileInfos =null;
            publishProgress(fileInfos);
            return ftp;
        } catch (IOException e) {
            Log.e("IOException",e.getMessage());
            List<FileInfo> fileInfos =null;
            publishProgress(fileInfos);
            return ftp;
        }
        List<FileInfo> fileinfos = FileInfo.getFileInfoList(files, "");//设定初始目录。
        publishProgress(fileinfos);
        fileinfos = null;//将该集合对象置为null，方便GC回收。
        return ftp;
    }

    /**
     * 更新UI主线程。
     * @param values
     */
    @Override
    protected void onProgressUpdate(List<FileInfo>... values) {
        fileInfoActivity.getFragmentInstance().getLoadDialog().dismiss();
        if (values==null){
            fileInfoActivity.print(R.string.net_err);//用toast消息机制提示用户网络故障。
        }else{
            filelistview.updateListVIew(values[0]);
        }
    }

    @Override
    protected void onPostExecute(FTPClient ftpClient) {
        fileInfoActivity.ftp = ftpClient;
    }
}

