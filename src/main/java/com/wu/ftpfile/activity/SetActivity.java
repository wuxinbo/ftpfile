package com.wu.ftpfile.activity;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.wu.ftpfile.R;
import com.wu.ftpfile.UI.LevelItem;
import com.wu.ftpfile.model.Constant;
import com.wu.ftpfile.model.SetItemmodel;
import com.wu.ftpfile.utils.ExitApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SetActivity extends MyActivity {
	/**
	 * 设置列表
	 */
//	private ListView setListview;
//    private ListView about_diylistview;
    /**
	 * 设置界面选项
     *
	 */
	private String[] setArray;
    private TextView exit;
    private AlertDialog.Builder exitBuilder;
    private SetItemmodel[] setvalues = new SetItemmodel[4];
    private ArrayList<LevelItem> itemlist = new ArrayList<LevelItem>();
    public String[] getSetArray() {
        return setArray;
    }

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting);
        initactivity();
        ExitApplication.getInstance().addToList(this); //将activity添加到集合中。
	}

    /**
     * 初始化model数据，将数据绑定到对象上。
     */
    private void initmodel() {
        for (int i = 0; i < setArray.length; i++) {
            SetItemmodel model = new SetItemmodel();
            model.setItemname(setArray[i]);
            model.setActivityname("LoginActivity");
            if (i == 0) {
                model.setItemval("wu");
            } else if (i == 1) {
                model.setItemval(Constant.FILE_LOCALTION);
            } else {
                model.setItemval("");
            }
            setvalues[i] = model;
            model = null;
        }

    }

//    public void getActivity() {
//        PackageManager manager = getPackageManager();
////        manager.getActivityInfo(new ComponentName(this,));
//    }
    @Override
    protected void setview(){
        nav_title.setText(getString(R.string.setting));
        nav_settext.setVisibility(View.INVISIBLE);
    }
    @Override
    public void initactivity() {
        initview();
        setview();
    }

    protected void initview() {
        itemlist.add((LevelItem) findViewById(R.id.user));
        itemlist.add((LevelItem) findViewById(R.id.filelocation));
        itemlist.add((LevelItem) findViewById(R.id.DIY));
        itemlist.add((LevelItem) findViewById(R.id.share));
        setArray=getResources().getStringArray(R.array.setting);
        exit = (TextView) findViewById(R.id.setting_exit);
        initmodel();
        initnavbar();
        setItemvalue();
    }

    /**
     * 初始化退出对话框，并且显示
     */
    private void initDialog() {
        exitBuilder = new AlertDialog.Builder(this, AlertDialog.THEME_HOLO_LIGHT);
        exitBuilder.setMessage(R.string.sure_Exit).setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ExitApplication.getInstance().exitApplication();//退出应用程序。
            }
        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).show();

    }

    /**
     * 退出按钮点击事件。
     * @param v
     */
    public void exit(View v) {
        initDialog();
    }

    private void setItemvalue() {
        for (int i = 0; i < itemlist.size(); i++) {
            itemlist.get(i).setViewval(setvalues[i]);
        }
    }
}
