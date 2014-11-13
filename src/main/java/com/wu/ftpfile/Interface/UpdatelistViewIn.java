package com.wu.ftpfile.Interface;

import com.wu.ftpfile.model.FileInfo;

import org.apache.commons.net.ftp.FTPFile;

import java.util.List;

/**
 * 列表更新回调接口
 * Created by wuxinbo on 2014/10/22.
 */
public interface UpdatelistViewIn {
    /**
     * 将数据填充到listview中
     * @param fileinfos
     */
    void updatelist(List<FileInfo> fileinfos);
}
