package com.wu.ftpfile.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wu.ftpfile.R;
import com.wu.ftpfile.model.FileInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.zip.Inflater;

/**
 * FileListView 的适配器。
 * Created by wuxinbo on 2014/11/2.
 */
public class FileLIstAdapter extends BaseAdapter {
    private static List<FileInfo> fileslist=null;
    private LayoutInflater inflater;
    String tag="FileLIstAdapter";
    public FileLIstAdapter(Context context,List<FileInfo> filelist){
        inflater=LayoutInflater.from(context);
        fileslist=filelist;
    }
    @Override
    public int getCount() {
        return fileslist.size();
    }

    @Override
    public Object getItem(int position) {
        return fileslist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=inflater.inflate(R.layout.listview,null);
        FileInfo file =fileslist.get(position);
        ImageView icon_view= (ImageView) view.findViewById(R.id.icon);
        TextView ItemTitle_view= (TextView) view.findViewById(R.id.ItemTitle);
        TextView filesize_view= (TextView) view.findViewById(R.id.filesize);
        TextView createtime_view= (TextView) view.findViewById(R.id.createtime);
        icon_view.setImageResource(file.getFile_pic());
        String FileInfo=file.getFilename();
        if (FileInfo.length()>18){
            FileInfo=FileInfo.substring(0,18);
        }
        ItemTitle_view.setText(FileInfo);
        filesize_view.setText(file.getFilesize());
        createtime_view.setText(file.getCreattime());
        return view;
    }


}
