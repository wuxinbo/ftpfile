package com.wu.ftpfile.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.wu.ftpfile.AsyncTask.AsyncUpdatelist;
import com.wu.ftpfile.Implment.FileListPagechangelistener;
import com.wu.ftpfile.R;
import com.wu.ftpfile.UI.FileListView;
import com.wu.ftpfile.adapter.FileFragmentpageAdapter;
import com.wu.ftpfile.fragment.FileListFragment;
import com.wu.ftpfile.model.Constant;

import org.apache.commons.net.ftp.FTPClient;

import java.io.File;


/**
 * 程序主入口，
 *
 * @author wuxinbo
 */
public class FileInfoActivity extends MyfragmentActivity {
    //	private ProgressBar pga;
    private ImageView localimgview;
    /**
     * 存放目录
     */
    private final String tag = "FileinfoActivity";
    /**
     * ftp操作时需要使用的FTPClient
     */
    public FTPClient ftp;

    int i = 0;
    /**
     * 用来判断当前属于哪个fragment。
     */
    private boolean isserver = true;
    /**
     * 底部导航栏上面的服务器图标
     */
    public ImageView server_img;
    private ViewPager fileViewpage;
    /**
     * fragment的ID。
     * <ul>
     * <li>如果该值为0说明当前活动的fragment是serverinfofragment<li/>
     * <li>如果该值为1说明当前活动的是localfilefragment</li>
     * </ul>
     */
    private int fragmnetnumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fileinfofragment);
        initactivity();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            /**
             *当当前路径位于根目录时，按下返回键将会退出，
             * 否则将会返回父目录。
             */
            case KeyEvent.KEYCODE_BACK:
                press_back();
                break;
            case KeyEvent.KEYCODE_MENU:
                showPopup();
                break;
        }

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.setting: {
                jumpTosetactivity();
            }
            case R.id.exit:
                press_back();
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * 跳转到设置界面。
     */
    private void jumpTosetactivity() {
        Intent in = new Intent();
        in.setClass(this, SetActivity.class);
        startActivity(in);
    }

    public void showPopup() {
        Log.d(tag, " you click is menu");
    }


    @Override
    protected void setview() {
        nav_settext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpTosetactivity();
            }
        });
        fileViewpage.setAdapter(new FileFragmentpageAdapter(getSupportFragmentManager()));
        fileViewpage.setOnPageChangeListener(new FileListPagechangelistener(this));
    }

    @Override
    protected void initview() {
        initnavbar();
        server_img = (ImageView) findViewById(R.id.server_img);
        fileViewpage = (ViewPager) findViewById(R.id.fileviewpager);
    }

    protected void initactivity() {
//        localimgview=(ImageView)findViewById(R.id.local_img);
        initview();
        setview();
    }

    public ViewPager getFileViewpage() {
        return fileViewpage;
    }

    /**
     * 点击返回键触发该动作。
     */
    private void press_back() {
        String serverfilepath = getFragmentInstance(Constant.SERVERFILE_FRAGMNET_NUMBER).getPath();
        String localfilepath = getFragmentInstance(Constant.LOCALFILE_FRAGMNET_NUMBER).getPath();
        if(isFragmentByFragmennumber()){
            if (serverfilepath.equals(File.separator)){
                finnshActivity();
            }else{
            backParentDirectory(serverfilepath);
            }
        }else{
            if (localfilepath.equals(Constant.SD_ROOT_PATH)) {
                finnshActivity();
            }
            else{
                backParentDirectory(localfilepath);
            }

        }

    }

    private void finnshActivity() {
        if (i == 0) {
            print(R.string.press_exit);
            i++;
        } else {
            finish();
        }
    }

    /**
     * 根据fragmentnumber判断当前属于哪个fragment。
     * @return 如果返回true就是serverfileinfofragment，否则为Localfileinfofragment。
     */
    public boolean isFragmentByFragmennumber(){
        if (fragmnetnumber == Constant.SERVERFILE_FRAGMNET_NUMBER) {
            return true;
        }
        return false;
    }
    public int getFragmnetnumber() {
        return fragmnetnumber;
    }

    public void setFragmnetnumber(int fragmnetnumber) {
        this.fragmnetnumber = fragmnetnumber;
    }

    /**
     * 返回上一级目录
     */
    private void backParentDirectory(String path) {
        path = path.substring(0, path.lastIndexOf(File.separator));
//        if (isFragmentByFragmennumber()){
//            getFragmentInstance(Constant.SERVERFILE_FRAGMNET_NUMBER).setPath();
//        }
        FileListView filelistview = null;
        if (isFragmentByFragmennumber()) {
            filelistview = getFragmentInstance(Constant.SERVERFILE_FRAGMNET_NUMBER).getFileListView();
        } else {
            filelistview = getFragmentInstance(Constant.LOCALFILE_FRAGMNET_NUMBER).getFileListView();
        }
        AsyncUpdatelist updatelist = new AsyncUpdatelist(filelistview, this);
        updatelist.execute(path);
    }

    public FileListFragment getFragmentInstance(int fragmentnumber) {
        switch (fragmentnumber) {
            case Constant.SERVERFILE_FRAGMNET_NUMBER:
                return (FileListFragment) fileViewpage.getAdapter().
                        instantiateItem(fileViewpage, Constant.SERVERFILE_FRAGMNET_NUMBER);
            case Constant.LOCALFILE_FRAGMNET_NUMBER:
                return (FileListFragment) fileViewpage.getAdapter().
                        instantiateItem(fileViewpage, Constant.LOCALFILE_FRAGMNET_NUMBER);

        }
        return null;
    }

    /**
     * 采用了新的办法获取APK图标，之前的失败是因为android中存在的一个BUG,通过
     */
    public static Drawable getApkIcon(Context context, String apkPath) {
        PackageManager pm = context.getPackageManager();
        PackageInfo info = pm.getPackageArchiveInfo(apkPath,
                PackageManager.GET_ACTIVITIES);
        if (info != null) {
            ApplicationInfo appInfo = info.applicationInfo;
            appInfo.sourceDir = apkPath;
            appInfo.publicSourceDir = apkPath;
            try {
                return appInfo.loadIcon(pm);
            } catch (OutOfMemoryError e) {
                Log.e("ApkIconLoader", e.toString());
            }
        }
        return null;
    }


}
