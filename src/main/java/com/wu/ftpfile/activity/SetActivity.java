package com.wu.ftpfile.activity;

import android.os.Bundle;
import android.view.View;

import com.wu.ftpfile.R;
import com.wu.ftpfile.UI.LevelItem;
import com.wu.ftpfile.model.Constant;
import com.wu.ftpfile.model.SetItemmodel;

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
    private Map<String,String> setmap = new HashMap<String, String>();
    private SetItemmodel[] setvalues = new SetItemmodel[4];
    private ArrayList<LevelItem> itemlist = new ArrayList<LevelItem>();
    public String[] getSetArray() {
        return setArray;
    }

    public Map<String, String> getSetmap() {
        return setmap;
    }

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting);
        initactivity();
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
//        setmap.put(setArray[0],);
//        setmap.put(setArray[1],"AboutActivity");
//        setmap.put(setArray[2],"exit");
    }
    @Override
    protected void setview(){
        nav_title.setText(getString(R.string.setting));
        nav_settext.setVisibility(View.INVISIBLE);

//        setListview.setAdapter(new ArrayAdapter<String>
//                (this,
//                        R.layout.setlistview_layout,
//                        R.id.SetText,
//                        setArray));
//        setListview.setOnItemClickListener(new setListViewItemclick(this));
    }
    @Override
    public void initactivity() {
        initview();
        setview();
    }
//    private void init

    protected void initview() {
        itemlist.add((LevelItem) findViewById(R.id.user));
        itemlist.add((LevelItem) findViewById(R.id.filelocation));
        itemlist.add((LevelItem) findViewById(R.id.DIY));
        itemlist.add((LevelItem) findViewById(R.id.share));
        setArray=getResources().getStringArray(R.array.setting);
        initmodel();
        initnavbar();
        setItemvalue();
    }

    public void exit(View v) {
        finish();
    }

    private void setItemvalue() {
        for (int i = 0; i < itemlist.size(); i++) {
            itemlist.get(i).setViewval(setvalues[i]);
        }
    }
}
