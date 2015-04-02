package com.wu.ftpfile.fragment;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wu.ftpfile.Interface.UpdatelistViewIn;
import com.wu.ftpfile.R;
import com.wu.ftpfile.UI.FileListView;
import com.wu.ftpfile.activity.FileInfoActivity;
import com.wu.ftpfile.adapter.FileLIstAdapter;
import com.wu.ftpfile.model.Constant;
import com.wu.ftpfile.model.FileInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** <code>serverfragment</code>和localfileserverment的父类，实现了<b>updatelistviewIn<b/>
 * Created by wuxinbo on 2014/11/13.
 */
public abstract class FileListFragment  extends Fragment implements UpdatelistViewIn {
    /**
     * 文件信息列表展示。
     */
    protected FileListView fileListView;
    protected BaseAdapter listItemAdapter = null;
    protected List<FileInfo> fileinfos = null;
    protected String path = File.separator;
    protected FileInfoActivity ACTIVITY =null;
    /**
     * 用于显示路径的
     */
    protected TextView path_view;

//    private String tag="FileListFragment";
    public  void updatelist(List<FileInfo> fileinfo){
        if (fileinfos.size() > 0) {
            fileinfos.clear();
        }
        if (fileinfo==null){
            ACTIVITY.print(R.string.login_err);
            return ;
        }else{
            for (FileInfo file : fileinfo) {
                FileInfo fileInfo  = FileListView.FileInit(file);
                fileinfos.add(fileInfo);
            }
        }
        Collections.sort(fileinfos);//对文件集合进行排序。
        fileListView.setFileinfos(fileinfos);
        listItemAdapter.notifyDataSetChanged();//通知适配器数据更新
        fileListView.setAdapter(listItemAdapter);
        if (this instanceof LocalFileFragment){
            if (path.equals(Constant.SD_ROOT_PATH)){
                path_view.setText("内存");
            }else {
                path_view.setText(path);
            }
        }else{
            if (path.equals("/")){
                path_view.setText("root");
            }else {
                path_view.setText(path);
            }
        }
    }
    protected void initview(View view) {
        fileinfos = new ArrayList<FileInfo>();
        fileListView= (com.wu.ftpfile.UI.FileListView) view.findViewById(R.id.local_listView);
        fileListView.setlistener(this); //绑定监听器。
        listItemAdapter = new FileLIstAdapter(ACTIVITY,fileinfos);
        path_view = (TextView) view.findViewById(R.id.pathView);
    }

    public String getPath() {
        return path;
    }


    public void setPath(String path) {
        this.path = path;
    }

    public TextView getPath_view() {
        return path_view;
    }

    public FileListView getFileListView() {
        return fileListView;
    }


}
