package com.wu.ftpfile.activity;

import com.wu.ftpfile.R;
import com.wu.ftpfile.utils.ExitApplication;

import android.app.Activity;
import android.os.Bundle;

public class AboutActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about_activity);
        ExitApplication.getInstance().addToList(this); //将activity添加到集合中。
	}

}
