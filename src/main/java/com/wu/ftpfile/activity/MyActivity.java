package com.wu.ftpfile.activity;

import android.app.Activity;
import android.view.WindowManager;
import android.widget.Toast;

public abstract class MyActivity extends Activity {

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

    /**
     * 初始化activity
     */
	public abstract void initactivity();
}
