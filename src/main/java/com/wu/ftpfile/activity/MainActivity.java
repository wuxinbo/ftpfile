package com.wu.ftpfile.activity;


import android.app.Activity;
import android.content.Intent;
import android.database.DatabaseUtils;
import android.os.Bundle;

import com.wu.ftp.UserInfo;
import com.wu.ftpfile.R;
import com.wu.ftpfile.model.Constant;
import com.wu.ftpfile.utils.DataBaseUtil;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final boolean userinfoIsExist = UserInfo.sharepreferenceIsExist(this, "userinfo", "url");
        final Intent  in =new Intent();
        if (userinfoIsExist){
            in.setClass(this,FileInfoActivity.class);
        }else{
            in.setClass(this,LoginActivity.class);
        }
        /*
        创建数据库，
         */
        DataBaseUtil dataHelper =new DataBaseUtil(this, Constant.DATABASE_NAME,null,1);
      //  dataHelper.onCreate(dataHelper.getWritableDatabase());
        /*
        让UI主线程休眠1.5秒，用来显示启动画面。
         */
        Thread jump =new Thread( new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finish();
                startActivity(in);
            }
        });
        jump.start();


    }







}
