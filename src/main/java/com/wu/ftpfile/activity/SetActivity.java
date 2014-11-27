package com.wu.ftpfile.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.wu.ftpfile.Implment.setListViewItemclick;
import com.wu.ftpfile.R;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SetActivity extends MyActivity {
	/**
	 * 设置列表
	 */
	private ListView setListview;
	/**
	 * 设置界面选项
     *
	 */
	private String[] setArray;
    private Map<String,String> setmap = new HashMap<String, String>();

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
        setmap.put(setArray[0],"LoginActivity");
        setmap.put(setArray[1],"AboutActivity");
        setmap.put(setArray[2],"exit");
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


    protected void initview() {
        setListview=(ListView) findViewById(R.id.userlistview);
        setArray=getResources().getStringArray(R.array.setting);
        System.out.println(Arrays.toString(setArray));
        initsetArray();
        initnavbar();
    }
}
