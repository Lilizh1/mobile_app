package com.he.week8_storage1;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.icu.text.SimpleDateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Date;

public class LibraryReaderActivity extends AppCompatActivity {
    //定义组件
    private EditText readerNumEdt, readerNameEdt, readerPhoneEdt;
    private TextView resultTxt;
    private RadioGroup readerTypeRadioGroup;
    //定义读者对象和数据库帮助类对象
    Reader reader = null;
    LibraryDBHelper dbHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library_reader);
        //初始化组件
        readerNumEdt = (EditText)findViewById(R.id.readerNumEdt);
        readerNameEdt = (EditText)findViewById(R.id.readerNameEdt);
        readerPhoneEdt = (EditText)findViewById(R.id.readerPhoneEdt);
        resultTxt = (TextView)findViewById(R.id.resulteTxt);
        readerTypeRadioGroup = (RadioGroup)findViewById(R.id.readerTypeRadioGroup);
//注册事件监听器
        findViewById(R.id.addReaderBnt).setOnClickListener(OnClickListener);
        findViewById(R.id.deleteReaderBnt).setOnClickListener(OnClickListener);
        findViewById(R.id.updateReaderBnt).setOnClickListener(OnClickListener);
        findViewById(R.id.queryReaderBnt).setOnClickListener(OnClickListener);
    }

    //定义事件监听器
    private View.OnClickListener OnClickListener = new View.OnClickListener() {
        @SuppressLint("Range")
        @Override
        public void onClick(View view) {
//为当前上下文创建数据库帮助类对象
            LibraryDBHelper dbHelper = new LibraryDBHelper(getApplicationContext());
//设置时间格式
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd " + "HH:mm:ss");
            switch (view.getId()) {
                case R.id.addReaderBnt:
//从组件中收集信息装配 Reader类对象(利用构造函数)，将作为参数传递
                    reader = new Reader(readerNumEdt.getText().toString(),
                            readerNameEdt.getText().toString(),
                            getReaderType(),
                            readerPhoneEdt.getText().toString(),
                            null,
                            sdf.format(new Date()).toString());
//利用数据库帮助类对象实现插入
                    dbHelper.insert_reader(reader);
//显示结果
                    resultTxt.setText("已经插入读者信息");
                    break;
                case R.id.deleteReaderBnt:
//利用数据库帮助类对象实现删除
                    int delCount = dbHelper.delete_reader(readerNumEdt.getText().toString());
//显示结果
                    resultTxt.setText("已经删除  " + delCount + "名读者数据。");
                    break;
                case R.id.updateReaderBnt:
//从组件中收集信息装配 Reader类对象(利用构造函数)，将作为参数传递
                    reader = new Reader(readerNumEdt.getText().toString(),
                            readerNameEdt.getText().toString(),
                            getReaderType(),
//读者类型：本科生、研究生、教职工
                            readerPhoneEdt.getText().toString(),
                            null,
                            sdf.format(new Date()).toString());
//利用数据库帮助类对象实现更新
                    int updateCount = dbHelper.update_reader(reader);
//显示结果
                    resultTxt.setText("已经修改  " + updateCount + "名读者数据。");
                    break;
                case R.id.queryReaderBnt:
//利用数据库帮助类对象实现查询
                    Cursor cursor = dbHelper.query_reader(readerNumEdt.getText().toString());
//显示结果
                    int count = cursor.getCount();
                    if (count == 0) {
//显示结果
                        resultTxt.setText("查询结果:(空)\n");
                    } else {
                        String str = "";
                        int i = 1;
//针对游标 cursor中的每一行循环
                        while (cursor.moveToNext()) {
//构造和装配一个 Reader对象
                            Reader reader = new Reader();
                            reader.setReader_number(cursor.getString(cursor.getColumnIndex("reader_number")));
                            reader.setReader_name(cursor.getString(cursor.getColumnIndex("reader_name")));
                            reader.setReader_type(cursor.getString(cursor.getColumnIndex("reader_type")));
                            reader.setReader_phone(cursor.getString(cursor.getColumnIndex("reader_phone")));
                            reader.setReader_password(cursor.getString(cursor.getColumnIndex("reader_password")));
                            reader.setReader_createtime(cursor.getString(cursor.getColumnIndex("reader_createtime")));
//将构造的对象转为字符串合并到结果中
                            str = str + i + "." + reader.toString();
                            i++;
                        }
//显示结果
                        resultTxt.setText("查询结果:\n" + str);
                    }
                    break;
            }
        }
    };

    //根据 readerTypeRadioGroup的取值确定读者类型
    String getReaderType() {
        String readerType = null;
        switch (readerTypeRadioGroup.getCheckedRadioButtonId()) {
            case R.id.underGraduate:
                readerType = "本科生";
                break;
            case R.id.graduate:
                readerType = "研究生";
                break;
            case R.id.teacher:
                readerType = "教职工";
                break;
        }
        return readerType;
    }

}
