package com.wu.ftpfile.exception;

/**
 * Created by wuxinbo on 2014/11/9.
 */
public class FTPconnectException extends Exception {
    private String exception ;
    public FTPconnectException(String exceptioninfo) {
            this.exception=exceptioninfo;
    }
}
