package com.wu.ftpfile.fragment;

import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wu.ftp.UserInfo;
import com.wu.ftpfile.AsyncTask.AsyncConnectServer;
import com.wu.ftpfile.R;
import com.wu.ftpfile.activity.FileInfoActivity;
import com.wu.ftpfile.utils.DataBaseUtil;

import org.apache.commons.net.ftp.FTPClient;


/**
 * 这个fragment显示服务器上的文件
 * Created by wuxinbo on 2014/11/3.
 */
public class ServerInfoFragment extends FileListFragment  {
//    private final String tag = "ServerInfoFragment";
    /**
     * ftp操作时需要使用的FTPClient
     */
    public FTPClient ftp;
    /**
     * FTP的账号。
     */
    private UserInfo user;
    /**
     * 服务器上的目录路径
     */
    private TextView nav_title;
//    private FileListView filelistview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ACTIVITY=(FileInfoActivity)getActivity();
//        nav_title= (TextView) ACTIVITY.findViewById(R.id.nav_title);
//        nav_title.setText("服务器");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.activity_fileinfo,container,false);
        initview(view);
        startAction();
        return view;
    }


    /**
     * 根据wifi的状态执行相应的操作，当wifi可用时连接服务器。
     *
     */
    private void startAction() {
        WifiManager manager = (WifiManager) getActivity().getSystemService(ACTIVITY.WIFI_SERVICE);
        int i = manager.getWifiState();
        switch (i){
            case WifiManager.WIFI_STATE_DISABLED:
                //提示用户当前网络不可用。
                ACTIVITY.print(R.string.network_err);
                break;
            case WifiManager.WIFI_STATE_ENABLED:
            {   //wifi已经打开。
                ACTIVITY.print(R.string.network_normal);
                conenctserver();
                break;
            }
        }
    }
    /**
     * 读取保存在sharepreferences中的信息。
     *
     * @return 读取到值时返回true，否则返回false
     */
    public UserInfo readConfig() {
        UserInfo info = null;
        SharedPreferences shared = ACTIVITY.getSharedPreferences("userinfo", 1);
        if (shared != null) {
            info = UserInfo.getServerInstance(shared.getString("url", null),
                    shared.getString("username", ""),
                    shared.getString("pwd", ""));
            return info;
        }
        return null;
    }
    /*
    连接Ftp服务器获取数据。
     */
    public void conenctserver() {
        user = DataBaseUtil.getdataHelper(ACTIVITY).getUserInfoFromDataBase();
        AsyncConnectServer connectServer =
                new AsyncConnectServer(fileListView, ACTIVITY);
        connectServer.execute(user);
    }

//    @Override
//    public void initFilelist(View view) {
//        filelistview= (com.wu.ftpfile.UI.FileListView) view.findViewById(R.id.server_listView);
//        filelistview.setlistener(this);
//    }

//    @Override
//    public void setadapter() {
//        filelistview.setAdapter(listItemAdapter);
//    }


}
