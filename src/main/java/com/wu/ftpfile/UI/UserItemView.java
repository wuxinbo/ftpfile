package com.wu.ftpfile.UI;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wu.ftpfile.R;

import java.util.Map;
import java.util.Set;

/**
 * 用户信息管理控件，展示用户详细信息，修改用户信息
 * Created by wuxin on 2015/8/8.
 */
public class UserItemView extends LinearLayout{
    /**
     *显示键
     */
    private TextView keyView;
    /**
     * 显示值
     */
    private EditText valueView;
    private Context context;
    public UserItemView(Context context) {
        super(context);
    }

    public UserItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public UserItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

//    public UserItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }

    /**
     * 为控件赋值
     * @param value
     */
    public void setValueForView(String value){
        valueView.setText(value);
    }

    public void initView(Context context,AttributeSet attr){
        View view = LayoutInflater.from(context).inflate(R.layout.userinfo_layout,this);
        if (isInEditMode()){
            return;
        }
        TypedArray array =getContext().obtainStyledAttributes(attr, R.styleable.UserItemView);
        String key =array.getString(R.styleable.UserItemView_key); //获取Xml配置文件中属性值
        keyView = (TextView) view.findViewById(R.id.key);
        valueView =(EditText)view.findViewById(R.id.value);
        keyView.setText(key);
    }
    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
    }
}
