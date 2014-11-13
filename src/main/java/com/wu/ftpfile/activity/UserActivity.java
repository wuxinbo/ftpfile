package com.wu.ftpfile.activity;

import com.wu.ftp.UserInfo;
import com.wu.ftpfile.R;
import com.wu.ftpfile.utils.Fileutil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

/**
 *用户信息activity
 * @author Administrator
 *
 */
public class UserActivity extends MyActivity {
	private UserInfo server;
	private String url=null;
	private String username=null;
	private String password=null;
	//url��ַ��
	private EditText edit_url ;
	//�û���
	private EditText edit_username;
	//����
	private EditText edit_password;
//	private boolean issetInfo=getSharedPreferences("setting", 1).contains("url");
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loginview);
        initactivity();
		boolean issetInfo= Fileutil.sharepreferenceIsExist(this,"userinfo","url");
		
		if (!issetInfo) {
			edit_url.setHint("�������ַ...");
			edit_username.setHint("�������û���...");
			edit_password.setHint("����������...");
		}else{
			LoadConfig();
		}
	}
	
	/**
	 * ���ļ��ж�ȡ������Ϣ��
	 */
	public void LoadConfig(){
		SharedPreferences shaered=getSharedPreferences("setting", 1);
		String urlstr=shaered.getString("url", url);
		String usernamestr=shaered.getString("username", username);
		String passwordstr=shaered.getString("password", password);
		edit_url.setText(urlstr);
		edit_username.setText(usernamestr);
		edit_password.setText(passwordstr);
		server= UserInfo.getServerInstance(urlstr, usernamestr, passwordstr);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
	}

    @Override
    public void initactivity() {
        SetInput();
        edit_url=(EditText) findViewById(R.id.login_url);
        edit_username=(EditText) findViewById(R.id.login_user);
        edit_password=(EditText) findViewById(R.id.login_pwd);
    }
}
