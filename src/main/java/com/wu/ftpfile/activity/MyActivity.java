package com.wu.ftpfile.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.wu.ftp.UserInfo;
import com.wu.ftpfile.R;

public abstract class MyActivity extends Activity {
    /**
     * 导航栏的标题
     */
    protected TextView nav_title;
    /**
     * 导航栏设置按钮
     */
    protected TextView nav_settext;
    /**
	 * 使用Toast向用户提示信息。
	 * @param stringResId 字符串ID
	 */
    public  void print(int stringResId) {
        Toast.makeText(getApplicationContext(), this.getString(stringResId),
                Toast.LENGTH_SHORT).
                show();
    }

	/**
	 * 禁止软键盘的弹出
	 */
	public void SetInput() {
		getWindow().setSoftInputMode(WindowManager.
				LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
	}

    protected abstract void setview();
    protected abstract void initview();
    /**
     * 初始化activity
     */
	public  abstract void initactivity();
    /**
     * 初始化导航栏。
     */
    protected   void initnavbar(){
        nav_settext = (android.widget.TextView) findViewById(R.id.nav_setting);
        nav_title   = (android.widget.TextView) findViewById(R.id.nav_title);
    }
    protected  void initUserinfo(){

    }

}
