package com.wu.ftpfile.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.wu.ftp.UserInfo;
import com.wu.ftpfile.R;


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
