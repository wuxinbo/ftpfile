package com.wu.ftpfile.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.wu.ftp.UserInfo;
import com.wu.ftpfile.model.Constant;

/**
 * 封装SQLite数据库的常用操作。
 * Created by wuxinbo on 2014/10/29.
 */
public class DataBaseUtil extends SQLiteOpenHelper{
    /**
     * Fileinfo表对应的列名。
     */
    public final static String UESR_NAME="user_name";
    public final static String PASSWORD="password";
    public final static String LOGIN_TIME="login_time";
    public final static String HOST_NAME="host_name";
    public DataBaseUtil(Context context, String name, SQLiteDatabase.CursorFactory factory,
                        int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Constant.createtable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * 组成查询sqllite条件。
     * @return
     */
    public static ContentValues getContentValues(UserInfo user){
        ContentValues values =new ContentValues();
//        values.put(DataBaseUtil.FILE_NAME,filename);
//        values.put(DataBaseUtil.SIZE,size);
//        values.put(DataBaseUtil.CREATE_TIME,creattime);
//        values.put(DataBaseUtil.IS_DIR,isdir);
        return values;
    }

    /**
     * 根据所得的参数返回一个cursor对象。
     * @param db sqlitedatabase
     * @param tablename 表名
     * @param columns 要返回的列名
     * @param select 查询条件
     * @param selectionArgs 查询参数
     * @param groupby 分组列
     * @param having 分组条件
     * @param oderby 排序方式
     * @return cursor Object
     */
//    public static Cursor getquerycursor(SQLiteDatabase db,
//                                        String   tablename,
//                                        String[] columns,
//                                        String select,
//                                        String[] selectionArgs,
//                                        String groupby,
//                                        String having,
//                                        String oderby){
//        Cursor cursor= db.query(tablename,
//                columns,
//                select,
//                selectionArgs,
//                groupby,
//                having,
//                oderby
//        );
//    return cursor;
//    }
}
