package com.he.week8_storage1;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class LibraryDBHelper extends SQLiteOpenHelper {
    //数字图书馆数据库版本号
    public static final int DATABASE_VERSION = 1;
    //数字图书馆数据库名
    private static final String DATABASE_NAME = "library.db";
    //数据库中三个最基本的表（读者、图书、借书）的名称
    private static final String READERS = "readers";
    private static final String BOOKS = "books";
    private static final String BORROWS = "borrows";

    //声明 SQLiteDataBase对象
//private SQLiteDatabase sqLiteDatabase;
//帮助类的构造函数
//这实际上是对父类构造函数的简化，在外部访问定义对象实例时，只需要指定上下文(Context)即可，数据库名、工厂类、版本号可省略
    public LibraryDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //重载 onCreate方法，创建数据库中的各个表
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//创建读者表
        String CREATE_READERS =
                "create table readers(" +
                        "reader_id integer primary key," +
                        "reader_number text," +
                        "reader_name text," +
                        "reader_type text," +
                        "reader_phone text," +
                        "reader_password text ," +
                        "reader_createtime text)";
        sqLiteDatabase.execSQL(CREATE_READERS);
//创建图书表
        String CREATE_BOOK =
                "create table books(" +
                        "book_id integer primary key," +
                        "book_name text," +
                        "book_author text, " +
                        "book_publisher text," +
                        "book_intime text," +
                        "book_counts integer)";
        sqLiteDatabase.execSQL(CREATE_BOOK);
//下面请自行创建图书借阅表 borrow（reader_id, book_id, borrowtime, ...）
    }
/*
下面只以对读者表(readers)的操作为例定义了对表的增删改查操作
对其他表的操作请学习者自行拓展
为便于学习者聚焦解决问题的机制，采用了一种清晰但低效的写法(在后有初步讨论)
后期我们将深入学习在真正工程中采用的改进方案
*/
//插入读者信息
//参数：reader -要插入的读者对象
//讨论：本例中用读者对象为参数，而不是像教材中用 ContentValues对象，
//
//将实现细节封装在数据库帮助器内，降低了外部用户调用难度和思维负担，这是一种更好的写法

    public void insert_reader(Reader reader) {
//定义 SQLiteDatabase对象实例
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
//在日志中输出文件位置，可以删除
//贺老师在调试中用于确定数据库位置时用过，保留下供同学们学习中参考
        Log.d("文件位置", sqLiteDatabase.getPath());
//用参数 Reader对象中的各成员值构造  ContentValues对象
//以利用 SqLiteDatabase对象的  insert方法实现操作
        ContentValues values = new ContentValues();
        values.put("reader_number", reader.getReader_number());
        values.put("reader_name", reader.getReader_name());
        values.put("reader_type", reader.getReader_type());
        values.put("reader_phone", reader.getReader_phone());
        values.put("reader_createtime", reader.getReader_createtime());
        values.put("reader_password", reader.getReader_password());
//调用 SqLiteDatabase对象的  insert方法实现向数据库的 readers表中插入数据
//注意：这里体现 LibraryDBHelper类将  SqLiteDatabase作为底层，调用其方法实现功能
        sqLiteDatabase.insert("readers", null, values);
//关闭数据库连接
        sqLiteDatabase.close();
//补充：插入一位读者就要打开一次数据库，这在性能方面是个大隐患，在实际系统中万万不能这样，请思考如何改善性能（其余操作亦存在相同问题，请自行思考，在本课后面也将会讨论解决        的方案）
    }

    //删除读者
//参数：reader_num-要删除读者的编号
//返回值：删除的记录数目
    public int delete_reader(String reader_num) {
//定义 SQLiteDatabase对象实例
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
//调用 SqLiteDatabase对象的  delete方法实现从数据库的 readers表中删除数据
//返回删除记录的数目
        int result = sqLiteDatabase.delete("readers",
                "reader_number = ?",
                new String[]{reader_num});   //精确匹配读者编号作为删除条件
//关闭数据库连接
        sqLiteDatabase.close();
//返回删除记录的数目
        return result;
    }
//更新读者信息
//参数：reader -要修改的读者信息
//按照读者编号找到读者，其余属性按对象中的指定的值改变

    //返回值：更新的记录数目
    public int update_reader(Reader reader) {
//定义 SQLiteDatabase对象实例
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
//取出读者编号，将作为更新的条件
        String reader_num = reader.getReader_number();
//用参数中提供的 reader对象构造  ContentValues对象 values，用作为修改的值
        ContentValues values = new ContentValues();
        values.put("reader_name", reader.getReader_name());
        values.put("reader_type", reader.getReader_type());
        values.put("reader_phone", reader.getReader_phone());
        values.put("reader_createtime", reader.getReader_createtime());
        values.put("reader_password", reader.getReader_password());
//调用 SqLiteDatabase对象的  update方法实现更新操作
        int result = sqLiteDatabase.update("readers",
                values,
//更新为  values
                "reader_number = ?",
                new String[]{reader_num});   //精确匹配读者编号作为更新条件
//关闭数据库连接
        sqLiteDatabase.close();
//返回更新的记录数目
        return result;
    }

    //查询读者信息:以读者为关键字查询
//参数：reader_num -要删除的读者号
//返回值：查询得到的 cursor；若不存在该读者，返回  null
    @SuppressLint("Range")
    public Cursor query_reader(String reader_num) {
//定义 SQLiteDatabase对象实例
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
//调用 SqLiteDatabase对象的  query方法实现查询操作
//查询条件中用 LIKE匹配"%"  + reader_num + "%"条件，查询中编号中含参数所给值的记录
        Cursor cursor = sqLiteDatabase.query("readers", null,
                "reader_number LIKE ?", new String[]{"%" + reader_num + "%"},
                null, null, null, null);
//对于查询操作，由于返回的 curso仍然要用到数据库的连接，不作关闭处理
//这实际上会对系统效率造成此处可接受的不良影响
//sqLiteDatabase.close();
//返回查询得到的游标 cursor，待返回后由调用者处理
        return cursor;
    }

    //其后可以继续定义针对图书表、借阅表的方法，以支持相关应用
//……
//重载 onUpgrade，可以为空，但必须有
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

}
