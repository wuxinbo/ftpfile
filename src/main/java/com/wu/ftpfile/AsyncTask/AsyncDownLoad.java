package com.wu.ftpfile.AsyncTask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Button;
import android.widget.ProgressBar;

import com.wu.ftpfile.model.Constant;
import com.wu.ftpfile.model.FileInfo;
import com.wu.ftpfile.utils.Fileutil;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by wuxinbo on 2014/11/1.
 */
public class AsyncDownLoad extends AsyncTask<FileInfo,Long,Void> {
    private FTPClient ftpclient;
//    private Context context;
    private ProgressBar downloadbar;
    private Button download_button;
    public AsyncDownLoad(FTPClient client,ProgressBar downloadbar,
                         Button download_button){
        ftpclient=client;
        this.downloadbar=downloadbar;
        this.download_button=download_button;
    }
    @Override
    protected Void doInBackground(FileInfo... params) {
        FileInfo fileinfo =params[0];
        String filename=fileinfo.getFilename();
        long filesize=fileinfo.getSize();
        final byte[] readdata=new byte [2048];
        StringBuffer writefile_path = initWriteFilePath();
        StringBuffer readfilepath=new StringBuffer(File.separator);
        readfilepath.append(filename);
        OutputStream opt= null;
        File file = Fileutil.createFile(writefile_path.toString(),filename);
        Log.d("file_path",writefile_path.toString());
        try {
            opt = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        InputStream is=null;
        long read_size=0,download_size=0;
        try {
            ftpclient.enterLocalPassiveMode();
            ftpclient.setFileType(FTPClient.BINARY_FILE_TYPE);
            is=ftpclient.retrieveFileStream(readfilepath.toString());
            while((read_size=is.read(readdata))!=-1){
                opt.write(readdata,0,(int)read_size);
                download_size+=read_size;
                publishProgress(new Long(download_size),new Long(filesize));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            Fileutil.close(opt, is);
        }
        return null;
    }

    private StringBuffer initWriteFilePath() {
        StringBuffer writefile_path=new StringBuffer();
        writefile_path.append(File.separator);
        writefile_path.append(Constant.SD_ROOT_PATH);
        writefile_path.append(File.separator);
        writefile_path.append(Constant.APP_DIR_PATH);
        writefile_path.append(File.separator);
        writefile_path.append(Constant.APP_DIR_DOWNLOAD_PATH);
        writefile_path.append(File.separator);
        return writefile_path;
    }

    @Override
    protected void onProgressUpdate(Long... values) {
        super.onProgressUpdate(values);
        double filesize=values[1].doubleValue();
        int download_size=(int)((values[0].doubleValue()/filesize)*100);
        downloadbar.setProgress(download_size);
        download_button.setText(download_size+"%");
        if (download_button.getText().toString().equals("100%")){
            download_button.setText("完成");
        }
    }
}
