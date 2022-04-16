package com.he.contentprovidertest;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FirstProviderActivity extends AppCompatActivity {
    //定义要操作 ContentProvider 对象的解析器对象
    ContentResolver contentResolver;
    //定义要操作内容提供者的地址
    Uri uri = Uri.parse("content://com.he.contentprovide.FirstProvider");
    //定义按钮组件
    Button insert, delete, update, query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_provider);

        //通过 getContentResolver()获取系统操作用的解析器 contentResolver
        contentResolver = getContentResolver();
        //初始化组件并设置事件监听器
        insert = (Button) findViewById(R.id.insert);
        delete = (Button)findViewById(R.id.delete);
        update = (Button)findViewById(R.id.update);
        query = (Button)findViewById(R.id.query);
        insert.setOnClickListener(onClickListener);
        delete.setOnClickListener(onClickListener);
        update.setOnClickListener(onClickListener);
        query.setOnClickListener(onClickListener);
    }
    //定义事件监听器
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.insert: //增加
                    insert(view);
                    break;
                case R.id.delete: //删除
                    delete(view);
                    break;
                case R.id.update: //更新
                    update(view);
                    break;
                case R.id.query: //查询
                    query(view);
                    break;
            }
        }
    };
    //实现插入数据
    public void insert(View source) {
    //把要插入的值封装到 ContentValue 对象中
        ContentValues values = new ContentValues();
        values.put("name", "Zhang San");
        values.put("age", 25);
    //调用 ContentResolver 的 insert()方法完成插入
    //返回的是该 Uri 对应的 ContentProvider 的 insert()的返回值
        Uri newUri = contentResolver.insert(uri, values);
    //以下是完成插入操作后的逻辑
        Toast.makeText(this, "远程 ContentProvide 新插入记录的 Uri 为：" + newUri,
                Toast.LENGTH_LONG).show();
    }
    //实现删除数据
    public void delete(View source) {
    //调用 ContentResolver 的 delete()方法。
    //返回的是删除的行数
        int count = contentResolver.delete(uri, "age > 120", null);
    //以下是完成删除操作后的逻辑
        Toast.makeText(this, "远程 ContentProvide 删除记录数为：" + count,
                Toast.LENGTH_LONG).show();
    }
    //实现数据更新
    public void update(View source) {
    //把要更新的值封装到 ContentValue 对象中
        ContentValues values = new ContentValues();
        values.put("name", "Zhang Xiao San");
    //调用 ContentResolver 的 update()方法。
    //返回的是更新的行数
        int count = contentResolver.update(uri, values,
                "name = ?",
                new String[]{"zhang san"});
    //以下是完成更新操作后的逻辑
        Toast.makeText(this, "远程 ContentProvide 更新记录数为：" + count, Toast.LENGTH_LONG).show();
    }
    //实现查询方法
    public void query(View source) {
    //调用 ContentReslver 的 query()方法
    //实际返回的是该 Uri 对应的 ContentProvider 的 query()的返回值
        Cursor cursor = contentResolver.query(uri,
                        new String[]{"name", "age"},
                "name LIKE ? and age > 21",
                new String[]{"zhang"}, "age");
    //以下是完成插入操作后的逻辑，一般针对 cursor 中的每一行进行处理
        Toast.makeText(this, "远程 ContentProvide 返回的 Cursor 为：" + cursor,
                Toast.LENGTH_LONG).show();
    }
}
