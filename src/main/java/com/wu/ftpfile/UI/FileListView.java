package com.wu.ftpfile.UI;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.wu.ftpfile.AsyncTask.AsyncDownLoad;
import com.wu.ftpfile.AsyncTask.AsyncUpdatelist;
import com.wu.ftpfile.Interface.UpdatelistViewIn;
import com.wu.ftpfile.R;
import com.wu.ftpfile.activity.FileInfoActivity;
import com.wu.ftpfile.fragment.FileListFragment;
import com.wu.ftpfile.model.Constant;
import com.wu.ftpfile.model.FileInfo;
import com.wu.ftpfile.utils.Fileutil;

import java.io.File;
import java.util.List;

/**
 * 自定义Listview用来封装显示数据。
 * Created by wuxinbo on 2014/10/21.
 */
public class FileListView extends ListView implements AdapterView.OnItemClickListener,
                                                        AdapterView.OnItemLongClickListener {
    /**
     * 上下文环境
     */
    private static Context context;
    private static final String tag = "IFlelistview";
    private UpdatelistViewIn updatelistview = null;
    private FileInfoActivity fileInfoActivity;
    /**
     * serverfileinfofragmnet实例
     */
    private FileListFragment serverfileListFragment;
    private FileListFragment LocalfileListFragment;
    private List<FileInfo> fileinfos = null;
    /**
     * 服务器上的文件路径
     */
    private StringBuffer serverPath = null;
    /**
     * 本地文件路径。
     */
    private StringBuffer localPath = null;
    /**
     * 菜单选项对话框。
     */
    private AlertDialog menuDialog;

    public AlertDialog getMenuDialog() {
        return menuDialog;
    }


    public FileListView(Context context) {
        super(context);
        initView(context);
    }

    public FileListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public FileListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setFileinfos(List<FileInfo> fileinfos) {
        this.fileinfos = fileinfos;
    }



    /**
     * 点击事件监听器。
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        final FileInfo fileinfo = fileinfos.get(position);
        final int fragmentnumber = ((FileInfoActivity) context).getFragmnetnumber();
        serverPath = new StringBuffer(serverfileListFragment.getPath()); //得到服务器上的文件路径
        localPath = new StringBuffer(LocalfileListFragment.getPath());//得到本地文件的路径。
        serverPath = serverPath.append(File.separator
                + fileinfo.getFilename());
        /*
        如果是当前是服务器fragment,就显示服务器上面的文件。
         */
        if (fragmentnumber == Constant.SERVERFILE_FRAGMNET_NUMBER) {
            if (fileinfo.getIsdir() == 1) {
                gotoDir(serverPath.toString());
            } else {
                final Button btn_download = (Button) view.findViewById(R.id.download_btn);
                final ProgressBar bar_download = (ProgressBar) view.findViewById(R.id.download_probar);
                btn_download.setVisibility(View.VISIBLE);
                bar_download.setVisibility(View.VISIBLE);
                fileinfo.setFilepath(serverPath.toString());
                /*
                下载按钮监听器执行方法。
                 */
                btn_download.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AsyncDownLoad download = new AsyncDownLoad(((FileInfoActivity) context).ftp,
                                bar_download, btn_download);
                        download.execute(fileinfo);

                    }
                });
            }
        } else {
            /*
            进入本地文件
             */
            String path = ((FileInfoActivity) context).getFragmentInstance(Constant.LOCALFILE_FRAGMNET_NUMBER).getPath();
            localPath.append(File.separator + fileinfo.getFilename());
            if (fileinfo.getIsdir() == 1) {
                gotoDir(localPath.toString());
            } else {
                clickfile(localPath.toString());
            }

        }
        serverPath = null;
        localPath = null;

    }

    /**
     * 根据传入的路径,进入相应的目录。
     */
    private void gotoDir(String path) {
        AsyncUpdatelist updatelist = new AsyncUpdatelist(this, context);
        updatelist.execute(path);

    }

    /**
     * 初始化控件
     */
    private void initView(Context context) {
        this.context = context;
        fileInfoActivity = ((FileInfoActivity) context);
        serverfileListFragment = fileInfoActivity.getFragmentInstance(
                Constant.SERVERFILE_FRAGMNET_NUMBER);
        LocalfileListFragment = fileInfoActivity.getFragmentInstance(
                Constant.LOCALFILE_FRAGMNET_NUMBER);

        setOnItemClickListener(this);//绑定点击事件。
        setOnItemLongClickListener(this); //绑定长按事件监听器
    }

    /**
     * 为Filelistview绑定更新列表回调函数。
     *
     * @param updatelistview <a href="#">UpdatelistViewIn</a>的实现。
     */
    public void setlistener(UpdatelistViewIn updatelistview) {
        this.updatelistview = updatelistview;
    }

    /**
     * 更新listview中的数据
     *
     * @param fileinfo file对象
     */
    public void updateListVIew(List<FileInfo> fileinfo) {
        this.fileinfos = fileinfo;
        updatelistview.updatelist(fileinfos);
    }

    /**
     * 将得到的数据按要求填入到map中，map存放的是和File相关的数据，
     * 例如文件大小，文件图标等。
     *
     * @param file 需要填入的File对象
     * @return 装载好的Hashmap
     */
    public static FileInfo FileInit(FileInfo file) {
        String fileInfo = file.getFilename();
        if (file.getIsdir() == 1) {
            file.setFile_pic(R.drawable.dir);
        } else if (Fileutil.isplay(context, fileInfo)) {
            file.setFile_pic(R.drawable.play);
        } else if (fileInfo.endsWith("mp3")) {
            file.setFile_pic(R.drawable.mp3);
        } else if (fileInfo.endsWith("doc")) {
            file.setFile_pic(R.drawable.doc);
        } else if (fileInfo.endsWith("xls")) {
            file.setFile_pic(R.drawable.xls);
        } else if (fileInfo.endsWith("exe")) {
            file.setFile_pic(R.drawable.exe);
        } else if (Fileutil.isZIP(context,fileInfo)) {
            file.setFile_pic(R.drawable.zip);
        } else if (Fileutil.ispicture(context, fileInfo)) {
            file.setHeadimg(FileInfo.getdrawable(file.getFilepath()));
            file.setFile_pic(0);
        } else if (Fileutil.isApk(fileInfo)) {
            file.setIcon(FileInfoActivity.getApkIcon(context, file.getFilepath()));
            file.setFile_pic(0);
        } else {
            file.setFile_pic(R.drawable.unkown);
        }
        return file;
    }

    /**
     * 当点击本地可识别文件执行相应的操作。
     */
    private void clickfile(String filepath) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (Fileutil.isMusic(context, filepath)) {
            intent.setDataAndType(Uri.parse(filepath), "audio/mp3");
//            intent.setComponent(new ComponentName("com.android.music",
//                    "com.android.music.MediaPlaybackActivity"));
        } else if (Fileutil.isplay(context, filepath)) {
            intent.setDataAndType(Uri.parse(filepath), "video/mp4");
        } else if (Fileutil.ispicture(context,filepath)) {
//            ComponentName com =new ComponentName("com.android.gallery",
//                    "com.android.camera.GalleryPicker");
//            intent.setComponent(com);
            intent.setDataAndType(Uri.parse(filepath), "image/*");
        } else if (Fileutil.isApk(filepath)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            ComponentName com = new ComponentName(Constant.PACKAGENAME_INSTALLER,
//                    Constant.PACKAGENAME_INSTALLER_ACTIVITY);
//            intent.setComponent(com);
//            intent.setDataAndType(Uri.parse(filepath), Constant.APK_INSTALL_DATATYPE);
        }
        context.startActivity(intent);
    }

    /**
     * listview 长按点击事件。
     * @param parent
     * @param view
     * @param position
     * @param id
     * @return
     */
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        AlertDialog.Builder  menuBuilder =new AlertDialog.Builder(context, AlertDialog.THEME_HOLO_LIGHT);
        menuDialog = menuBuilder.setView(View.inflate(context,R.layout.grid_menu_layout,null)).setTitle("操作").create();
        menuDialog.show();
        return true;
    }
}

