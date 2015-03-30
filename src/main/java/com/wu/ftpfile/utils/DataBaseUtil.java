package com.wu.ftpfile.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.wu.ftp.UserInfo;
import com.wu.ftpfile.model.Constant;

import java.util.Date;

/**
 * 封装SQLite数据库的常用操作。
 * Created by wuxinbo on 2014/10/29.
 */
public class DataBaseUtil extends SQLiteOpenHelper{
    /**
     * user_info表对应的列名。
     */
    public final static String UESR_NAME="user_name";//用户名
    public final static String PASSWORD="password";//密码
    public final static String LOGIN_TIME="login_time";//登录时间
    public final static String HOST_NAME="host_name";//主机名
    public final static String ENCODING="encoding";//编码
    public final static String CURRENT_USER="current_user";//当前用户
    /**
     * userinfo表对应的列集合。
     */
    public final static String[] USER_INFO_COLUMNS= new String []{UESR_NAME,PASSWORD,LOGIN_TIME,
                                                                       HOST_NAME,ENCODING,CURRENT_USER};
//    public final static String PORT="port";
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
     * 得到DataBaseUtil实例。
     * @param context 上下文环境
     * @return 实例的DataBaseUtil
     */
    public static DataBaseUtil getdataHelper(Context context){
        DataBaseUtil dataHelper =new DataBaseUtil(context, Constant.DATABASE_NAME,null,1);
        return  dataHelper;
    }
    /**
     * 保存用户信息岛数据表。
     * @param user 封装了用户信息的Userinfo。
     */
    public  void saveUserInfo(UserInfo user){
        SQLiteDatabase db=getWritableDatabase();
        db.insert(Constant.TABLE_NAME,null,getContentValues(user));
        db.close();
    }

    /**
     * 从数据库中读取用户信息。
     * @return
     */
    public  UserInfo getUserInfoFromDataBase(){
        SQLiteDatabase db =getWritableDatabase();
        Cursor cursor = getquerycursor(db,Constant.TABLE_NAME,
                USER_INFO_COLUMNS,null,null,null,null,null);
        UserInfo user =new UserInfo();
        if (cursor.moveToFirst()){//将游标移到第一位。
        user.setUsername(cursor.getString(cursor.getColumnIndex(UESR_NAME)));
        user.setPassword(cursor.getString(cursor.getColumnIndex(PASSWORD)));
        user.setUrl(cursor.getString(cursor.getColumnIndex(HOST_NAME)));
        user.setEncoding(cursor.getString(cursor.getColumnIndex(ENCODING)));
        }
        cursor.close();
        db.close();
        return user;
    }
    /**
     * 组成查询sqllite条件。
     * @param user 封装了的用户信息。
     * @return 封装了Map结构
     */
    public static ContentValues getContentValues(UserInfo user){
        ContentValues values =new ContentValues();
        values.put(DataBaseUtil.UESR_NAME,user.getUsername());
        values.put(DataBaseUtil.PASSWORD,user.getPassword());
        values.put(DataBaseUtil.HOST_NAME,user.getUrl());
        values.put(DataBaseUtil.ENCODING,user.getEncoding());
        values.put(DataBaseUtil.CURRENT_USER,user.isCurrentUser());
        values.put(DataBaseUtil.LOGIN_TIME,new Date().toString());//获取当前时间作为登录时间。
        return values;
    }

    /**
     * 判断是否有用户表。
     * @param context
     * @param tableName
     * @return
     */
    public static boolean userInfoTableIsExist(Context context,String tableName ){
       DataBaseUtil dbHelper = getdataHelper(context);
       String sql = "SELECT count(*) FROM "+tableName+" ; ";
       SQLiteDatabase db = dbHelper.getReadableDatabase();
       Cursor cursor=db.rawQuery(sql,null);
        int count=0;
        if (cursor.moveToFirst()){
            count=cursor.getInt(0);
        }
       if (count==0){
           return false;
       }else {
           return true;
       }
    }

    /**
     * 查询是否有该用户。
     * @param context 上下文环境
     * @param tableName 表名
     * @param select 查询条件（字段）
     * @param where 查询参数（值）
     * @return 如果有改用户返回查询记录数，否则返回0
     */
    public static int selectUserInfo(Context context,String tableName,String select,String where){
        DataBaseUtil dbHelper = getdataHelper(context);
        String sql = "SELECT count(*) FROM "+tableName+" WHERE "+select+" = '"+where+"'; ";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor=db.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            return cursor.getInt(0);
        }
        return 0;
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
    public static Cursor getquerycursor(SQLiteDatabase db,
                                        String   tablename,
                                        String[] columns,
                                        String select,
                                        String[] selectionArgs,
                                        String groupby,
                                        String having,
                                        String oderby){
        Cursor cursor= db.query(tablename,
                columns,
                select,
                selectionArgs,
                groupby,
                having,
                oderby
        );
    return cursor;
    }
}
