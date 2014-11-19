package com.wu.ftpfile.activity;

import java.io.File;

import org.apache.commons.net.ftp.FTPClient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.wu.ftpfile.Implment.FileListPagechangelistener;
import com.wu.ftpfile.R;
import com.wu.ftpfile.adapter.FileFragmentpageAdapter;


/**
 * 程序主入口，
 *
 * @author wuxinbo
 */
public class FileInfoActivity extends MyfragmentActivity  {
    //	private ProgressBar pga;
    private ImageView localimgview;
    /**
     * 存放目录
     */
    private final String tag = "FileinfoActivity";
    /**
     * ftp操作时需要使用的FTPClient
     */
    public FTPClient ftp;

    int i = 0;
    /**
     * 服务器上的目录路径
     */
    public String path = File.separator;
    /**
     * 导航栏的设置
     */
    private TextView nav_settext;
    /**
     * 用来判断当前属于哪个fragment。
     */
    private boolean isserver=true;
    /**
     * 底部导航栏上面的服务器图标
     */
    public  ImageView server_img;
    private ViewPager fileViewpage;
    public  TextView nav_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fileinfofragment);
        initactivity();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            /**
             *当当前路径位于根目录时，按下返回键将会退出，
             * 否则将会返回父目录。
             */
            case KeyEvent.KEYCODE_BACK:
                press_back(path);
                break;
            case KeyEvent.KEYCODE_MENU:
                showPopup();
                break;
        }

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.setting: {
                jumpTosetactivity();
            }
            case R.id.exit:
                press_back(path);
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * 跳转到设置界面。
     */
    private void jumpTosetactivity() {
        Intent in = new Intent();
        in.setClass(this, SetActivity.class);
        startActivity(in);
    }

    public void showPopup() {
        Log.d(tag, " you click is menu");
    }


    public void initactivity() {
        nav_settext=(TextView) findViewById(R.id.nav_setting);
        nav_title=(TextView) findViewById(R.id.nav_title);
        localimgview=(ImageView)findViewById(R.id.local_img);
        server_img=(ImageView)findViewById(R.id.server_img);
        nav_settext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpTosetactivity();
            }
        });
        fileViewpage= (ViewPager) findViewById(R.id.fileviewpager);
        fileViewpage.setAdapter(new FileFragmentpageAdapter(getSupportFragmentManager()));
        fileViewpage.setOnPageChangeListener(new FileListPagechangelistener(this));
    }



    /**
     * 点击返回键触发该动作。
     *
     * @param path
     */
    private void press_back(String path) {
        if (path.equals(File.separator)) {
            if (i == 0) {
                print(R.string.press_exit);
                i++;
            } else {
                finish();
            }
        } else {
            backParentDirectory();
        }

    }

    /**
     * 返回上一级目录
     */
    private void backParentDirectory() {
//        path = path.substring(0, path.lastIndexOf(File.separator));
//        AsyncUpdatelist updatelist = new AsyncUpdatelist(filelistview, this);
//        updatelist.execute(path);
    }

}
