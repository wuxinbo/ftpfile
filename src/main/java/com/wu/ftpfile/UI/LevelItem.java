package com.wu.ftpfile.UI;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wu.ftpfile.R;
import com.wu.ftpfile.model.SetItemmodel;

/**
 * 设置界面的组合控件，用于显示设置条目。
 * Created by Administrator on 2014/12/14.
 */
public class LevelItem extends LinearLayout implements View.OnClickListener {
    private TextView itemNameview;
    private TextView itemvalview;

    public LevelItem(Context context) {
        super(context);
    }

    public LevelItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initwidgt(context);
    }

    /**
     * 初始化控件。
     *
     * @param context 上下文环境。
     */
    private void initwidgt(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.setlistview_layout, this);
        itemNameview = (TextView) view.findViewById(R.id.SetText);
        itemvalview = (TextView) view.findViewById(R.id.value);
        setOnClickListener(this);
    }

    /**
     * 给空间初始化赋值
     */
    public void setViewval(SetItemmodel model) {
        itemNameview.setText(model.getItemname());
        itemvalview.setText(model.getItemval());
    }

    @Override
    public void onClick(View v) {

        System.out.println("click me");
    }

}
