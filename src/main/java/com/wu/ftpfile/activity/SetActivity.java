package com.wu.ftpfile.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.wu.ftpfile.Implment.setListViewItemclick;
import com.wu.ftpfile.R;
import com.wu.ftpfile.model.SetItemmodel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SetActivity extends MyActivity {
	/**
	 * 设置列表
	 */
	private ListView setListview;
    private ListView about_diylistview;
    /**
	 * 设置界面选项
     *
	 */
	private String[] setArray;
    private Map<String,String> setmap = new HashMap<String, String>();
    private ArrayList<SetItemmodel> setvalues = new ArrayList<SetItemmodel>();
    private ArrayList<SetItemmodel> setvalues_2 = new ArrayList<SetItemmodel>();

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

    private void initsetArray() {
        for (String str : setArray) {
            SetItemmodel model = new SetItemmodel();
            model.setItemname(str);
            model.setActivityname("LoginActivity");
            model.setItemval("wu");
        }
//        setmap.put(setArray[0],);
//        setmap.put(setArray[1],"AboutActivity");
//        setmap.put(setArray[2],"exit");
    }
    @Override
    protected void setview(){
        nav_title.setText(getString(R.string.setting));
        nav_settext.setVisibility(View.INVISIBLE);
        setListview.setAdapter(new ArrayAdapter<String>
                (this,
                        R.layout.setlistview_layout,
                        R.id.SetText,
                        setArray));
        setListview.setOnItemClickListener(new setListViewItemclick(this));
    }
    @Override
    public void initactivity() {
        initview();
        setview();
    }
//    private void init

    protected void initview() {
        setListview=(ListView) findViewById(R.id.userlistview);
        setArray=getResources().getStringArray(R.array.setting);
        initsetArray();
        initnavbar();
    }
}
