package com.wu.ftpfile.activity;

import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

/**
 * Created by wuxinbo on 2014/11/12.
 */
public class MyfragmentActivity extends FragmentActivity {
    /**
     * 温馨提示
     * @param stringResId
     */
    public  void print(int stringResId) {
        Toast.makeText(getApplicationContext(), this.getString(stringResId),
                Toast.LENGTH_SHORT).
                show();
    }


}
