package com.he.week9_advanceui;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lenovo on 2022/4/25.
 */

public class StudentDBHelper extends SQLiteOpenHelper {
    //数据库名称
    private static final String DB_NAME = "students.db";
    //表名
    private static final String TBL_NAME = "student";
    //声明 SQLiteDatabase 对象
    private SQLiteDatabase stuDB;

    //构造方法
    public StudentDBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        this.stuDB = database;
        String CREATE_TBL = "CREATE TABLE student(_id integer primary key autoincrement, num text, name text)";
        stuDB.execSQL(CREATE_TBL);
    }

    public void insert(ContentValues values) {
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TBL_NAME, null, values);
        db.close();
    }

    //查询（获得全部）
    public Cursor query() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TBL_NAME, null,
                null, null,
                null, null, null);
        return cursor;
    }

    public void del(int id) {
        if (stuDB == null)
            stuDB = getWritableDatabase();
        stuDB.delete(TBL_NAME, "_id=?", new String[]{String.valueOf(id)});
    }

    //关闭数据库
    public void close() {
        if (stuDB != null)
            stuDB.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
