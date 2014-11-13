package com.wu.ftpfile.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

public abstract class MyActivity extends Activity {

    /**
	 * 使用Toast向用户提示信息。
	 * @param Text
	 */
	public  void print(String Text) {
		Toast.makeText(getApplicationContext(), Text,
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

    /**
     * 初始化activity
     */
	public abstract void initactivity();
}
