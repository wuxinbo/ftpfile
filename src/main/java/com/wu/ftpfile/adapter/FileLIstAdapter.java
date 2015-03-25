package com.wu.ftpfile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wu.ftpfile.R;
import com.wu.ftpfile.model.Constant;
import com.wu.ftpfile.model.FileInfo;

import java.util.List;

/**
 * FileListView 的适配器。
 * Created by wuxinbo on 2014/11/2.
 */
public class FileLIstAdapter extends BaseAdapter {
    private  List<FileInfo> fileslist=null;
    private LayoutInflater inflater;
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
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.filelistview_layout, null);
        }
        FileInfo file =fileslist.get(position);
        ImageView icon_view = (ImageView) convertView.findViewById(R.id.icon);
        TextView ItemTitle_view = (TextView) convertView.findViewById(R.id.ItemTitle);
        TextView filesize_view = (TextView) convertView.findViewById(R.id.filesize);
        TextView createtime_view = (TextView) convertView.findViewById(R.id.createtime);
        if (file.getFile_pic() == 0) {//判断文件（文件夹图标）
            if (file.getHeadimg() != null) {
                icon_view.setImageBitmap(file.getHeadimg());
            } else if (file.getIcon() != null) {
                icon_view.setImageDrawable(file.getIcon());
            }
        } else {
            icon_view.setImageResource(file.getFile_pic());//设置文件图标
        }
        if (FileInfo.isdir(file.getIsdir())) {
            if (file!=null){
                filesize_view.setText(file.getFileCount() + "项");
            }
        } else {
            filesize_view.setText(file.getFilesize());
        }
        String FileInfo=file.getFilename();
        FileInfo = String_length_format(FileInfo);
        ItemTitle_view.setText(FileInfo);
        createtime_view.setText(file.getCreattime());
        file = null;
        return convertView;
    }

    public static String String_length_format(String str) {
        if (str.length() > Constant.FILENAME_FORMAT_LENGTH) {
            str = str.substring(0, Constant.FILENAME_FORMAT_LENGTH);
        }
        return str;
    }

}
