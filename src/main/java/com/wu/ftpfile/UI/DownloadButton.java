package com.wu.ftpfile.UI;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import com.wu.ftpfile.R;

/** 自定义下载按钮，并封装监听器功能。
 * Created by Administrator on 2014/11/19.
 */
public class DownloadButton extends Button implements View.OnClickListener{
    private Context context;
    public DownloadButton(Context context) {
        super(context);
        initview(context);
    }

    public DownloadButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initview(context);
    }

    public DownloadButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onClick(View v) {
//        AsyncDownLoad download=new AsyncDownLoad(((FileInfoActivity)context).ftp,
//                bar_download,btn_download);
//        download.execute(fileinfo);
    }
    private void initview(Context context){
        this.setText(context.getString(R.string.download));
        setOnClickListener(this);
    }
}
