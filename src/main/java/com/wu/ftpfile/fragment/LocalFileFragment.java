package com.wu.ftpfile.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wu.ftpfile.AsyncTask.AsyncUpdatelocalList;
import com.wu.ftpfile.R;
import com.wu.ftpfile.activity.FileInfoActivity;
import com.wu.ftpfile.model.Constant;


/**
 * 这个fragment主要用于显示本地文件。
 * Created by wuxinbo on 2014/11/3.
 */
public class LocalFileFragment extends FileListFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ACTIVITY=(FileInfoActivity)getActivity();
        path=Constant.SD_ROOT_PATH;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.localfilefragment,container,false);
        initview(v);
        StartAction();
        return v;
    }
    private void StartAction (){
        AsyncUpdatelocalList updatelocalList =new AsyncUpdatelocalList(fileListView);
        updatelocalList.execute(Constant.SD_ROOT_PATH);
    }


//    @Override
//    public void initFilelist(View view) {
//        filelistview= (com.wu.ftpfile.UI.FileListView) view.findViewById(R.id.local_listView);
//        filelistview.setlistener(this);
//    }

//    @Override
//    public void setadapter() {
//        filelistview.setAdapter(listItemAdapter);
//    }
}
