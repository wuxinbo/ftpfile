package com.wu.ftpfile.activity;

import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

/**
 * Created by wuxinbo on 2014/11/12.
 */
public class MyfragmentActivity extends FragmentActivity {
    public  void print(String Text) {
        Toast.makeText(getApplicationContext(), Text,
                Toast.LENGTH_SHORT).
                show();
    }


}
