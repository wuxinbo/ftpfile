package com.wu.ftpfile.adapter;

//import android.app.Fragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.wu.ftpfile.activity.LocalFileFragment;
import com.wu.ftpfile.activity.ServerInfoFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuxinbo on 2014/11/10.
 */
public class FileFragmentpageAdapter extends FragmentPagerAdapter {

    /**
     * 返回fragment的个数。
     */
    private static final int COUNT=2;
    private List<Fragment> fragmentlist;

    public FileFragmentpageAdapter(FragmentManager fm) {
        super(fm);
        fragmentlist=new ArrayList<Fragment>();
        fragmentlist.add(new ServerInfoFragment());
        fragmentlist.add(new LocalFileFragment());
    }

    @Override
    public int getCount() {
        return COUNT;
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentlist.get(i);
    }
}
