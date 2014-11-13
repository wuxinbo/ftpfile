package com.wu.ftpfile.activity;

import android.support.v4.app.Fragment;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wu.ftp.UserInfo;
import com.wu.ftpfile.AsyncTask.AsyncConnectServer;
import com.wu.ftpfile.Interface.UpdatelistViewIn;
import com.wu.ftpfile.R;
import com.wu.ftpfile.adapter.FileLIstAdapter;
import com.wu.ftpfile.UI.FileListView;
import com.wu.ftpfile.model.FileInfo;

import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 这个fragment显示服务器上的文件
 * Created by wuxinbo on 2014/11/3.
 */
public class ServerInfoFragment extends Fragment implements UpdatelistViewIn {
    private TextView tv;
    private FileListView filelistview;
    private FileInfoActivity ACTIVITY =null;
    BaseAdapter listItemAdapter = null;
    List<FileInfo> fileinfos = null;
    /**
     * 存放目录
     */
    private final String tag = "ServerInfoFragment";
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
    public String path = File.separator;
    private TextView nav_title;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ACTIVITY=(FileInfoActivity)getActivity();
        nav_title= (TextView) ACTIVITY.findViewById(R.id.nav_title);
        nav_title.setText("服务器");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.activity_fileinfo,container,false);
        initactivity(view);
        startAction();
        return view;

    }
    public void initactivity(View view) {
        filelistview = (FileListView) view.findViewById(R.id.listView1);
        filelistview.setlistener(this);
        fileinfos = new ArrayList<FileInfo>();
        tv = (TextView) view.findViewById(R.id.tv2);
        listItemAdapter = new FileLIstAdapter(ACTIVITY,fileinfos);


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
                ACTIVITY.print(getResources().getString(R.string.network_err));
                break;
            case WifiManager.WIFI_STATE_ENABLED:
            {   //wifi已经打开。
                ACTIVITY.print(getResources().getString(R.string.network_normal));
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
        UserInfo info = new UserInfo();
        SharedPreferences shared = ACTIVITY.getSharedPreferences("userinfo", 1);
        if (shared != null) {
            info = UserInfo.getServerInstance(shared.getString("url", null),
                    shared.getString("username", ""),
                    shared.getString("pwd", ""));
            return info;
        }
        return null;
    }
    private void conenctserver() {
        user = readConfig();
        AsyncConnectServer connectServer =
                new AsyncConnectServer(filelistview, ACTIVITY);
        connectServer.execute(user);
    }

    @Override
    public void updatelist(List<FileInfo> fileinfo) {
        List<FileInfo> files=fileinfo;
        if (fileinfos.size() > 0) {
            fileinfos.clear();
        }
        if (files==null){
            ACTIVITY.print("登录失败！");
            return ;
        }else{
        for (FileInfo file : files) {
            FileInfo fileInfo  = FileListView.FileInit(file);
            fileinfos.add(fileInfo);
        }
        }
        listItemAdapter.notifyDataSetChanged();
        filelistview.setAdapter(listItemAdapter);
        if (path.equals("/")){
            tv.setText("root");
        }else {
            tv.setText(path);
        }
    }
}
