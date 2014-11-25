package com.wu.ftpfile.Implment;

import android.content.Context;
import android.support.v4.view.ViewPager;

import com.wu.ftpfile.R;
import com.wu.ftpfile.activity.FileInfoActivity;
import com.wu.ftpfile.model.Constant;

/** 本类是监听fragment切换时的变化
 * Created by Administrator on 2014/11/14.
 */
public class FileListPagechangelistener implements ViewPager.OnPageChangeListener{
    /**
     * 调试信息
     */
//    private String tag=getClass().getName();
    /**
     * 上下文环境
     */
    private Context context;
    private FileInfoActivity activity=null;

    public FileListPagechangelistener(Context context) {
        this.context = context;
        activity = (FileInfoActivity) context;
    }

    @Override
    public void onPageScrolled(int i, float v, int i2) {
    }

    @Override
    public void onPageSelected(int i) {
        switch (i){
            case 0:
                serverfilefragmentChange();
                break;
            case 1:
                LocalfilefragmentChange();
                break;
        }
    }

    /**
     * 当滑到第二页时，{@link com.wu.ftpfile.fragment.ServerInfoFragment}做相应的变化。
     */
    private void serverfilefragmentChange(){
        activity.nav_title.setText(activity.getString(R.string.title_activity_main));
        activity.server_img.setImageResource(R.drawable.server_pressed);
        activity.setFragmnetnumber(Constant.SERVERFILE_FRAGMNET_NUMBER);
    }

    /**
     *
     * <a href="#">LocalFileFragment</a>切换到<a href="#">ServerInfoFragment</a> 一些UI的变化。
     */
    private void LocalfilefragmentChange(){
        activity.nav_title.setText(activity.getString(R.string.title_local));
        activity.server_img.setImageResource(R.drawable.server_nomal);
        activity.setFragmnetnumber(Constant.LOCALFILE_FRAGMNET_NUMBER);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}
