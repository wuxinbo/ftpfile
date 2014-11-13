package com.wu.ftpfile.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.wu.ftpfile.Implment.setListViewItemclick;
import com.wu.ftpfile.R;

import java.util.HashMap;
import java.util.Map;

public class SetActivity extends MyActivity {
	/**
	 * 设置列表
	 */
	private ListView setListview;
	/**
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
        setmap.put(setArray[0],"UserActivity");
        setmap.put(setArray[1],"AboutActivity");
        setmap.put(setArray[2],"exit");
    }

    @Override
    public void initactivity() {
        setListview=(ListView) findViewById(R.id.userlistview);
        setArray=getResources().getStringArray(R.array.setting);
        initsetArray();
        setListview.setAdapter(new ArrayAdapter<String>
                (this,
                        R.layout.setlistview_layout,
                        R.id.SetText,
                        setArray));
        setListview.setOnItemClickListener(new setListViewItemclick(this));
    }
}
