package com.wu.ftpfile.activity;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wu.ftpfile.R;

/**
 * 这个fragment主要用于显示本地文件。
 * Created by wuxinbo on 2014/11/3.
 */
public class LocalFileFragment extends Fragment {
    /**
     * Fileinfoactivity.
     */
    private FileInfoActivity ACTIVITY =null;
    private TextView nav_title;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ACTIVITY=(FileInfoActivity)getActivity();
//        nav_title= (TextView) ACTIVITY.findViewById(R.id.nav_title);
//        nav_title.setText("本地文件");

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.localfilefragment,container,false);
        return v;
    }
}
