package com.wu.ftpfile.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.wu.ftpfile.model.Constant;

/**
 * 封装SQLite数据库的常用操作。
 * Created by wuxinbo on 2014/10/29.
 */
public class DataBaseUtil extends SQLiteOpenHelper{
    /**
     * Fileinfo表对应的列名。
     */
    public final static String FILE_NAME="name";
    public final static String SIZE="filesize";
    public final static String CREATE_TIME="createtime";
    public final static String IS_DIR="isdir";
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
    public static ContentValues getContentValues(String filename,
                                                 String size,
                                                 String creattime,
                                                 int isdir){
        ContentValues values =new ContentValues();
        values.put(DataBaseUtil.FILE_NAME,filename);
        values.put(DataBaseUtil.SIZE,size);
        values.put(DataBaseUtil.CREATE_TIME,creattime);
        values.put(DataBaseUtil.IS_DIR,isdir);
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
