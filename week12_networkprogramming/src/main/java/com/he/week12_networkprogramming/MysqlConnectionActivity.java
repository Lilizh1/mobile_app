package com.he.week12_networkprogramming;

import android.icu.text.SimpleDateFormat;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MysqlConnectionActivity extends AppCompatActivity {
    //定义组件
    private EditText readerNumEdt, readerNameEdt, readerPhoneEdt;
    private TextView resultTxt;
    private RadioGroup readerTypeRadioGroup;
    //操作远程数据库是耗时操作，需要建立新线程操作，其结果通过 Handle 影响主 UI 线程
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case 0x11:
                    String s = (String) msg.obj;
                    resultTxt.setText(s);
                    break;
                case 0x12:
                    String ss = (String) msg.obj;
                    resultTxt.setText(ss);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mysql_connection);
        //初始化组件
        readerNumEdt = (EditText) findViewById(R.id.readerNumEdt);
        readerNameEdt = (EditText) findViewById(R.id.readerNameEdt);
        readerPhoneEdt = (EditText) findViewById(R.id.readerPhoneEdt);
        resultTxt = (TextView) findViewById(R.id.resulteTxt);
        readerTypeRadioGroup = (RadioGroup) findViewById(R.id.readerTypeRadioGroup);
        //为数据库的增删改查按钮注册事件监听器
        //	findViewById(R.id.addReaderBnt).setOnClickListener(OnClickListener);
        //	findViewById(R.id.deleteReaderBnt).setOnClickListener(OnClickListener);
        //  findViewById(R.id.updateReaderBnt).setOnClickListener(OnClickListener);
        findViewById(R.id.queryReaderBnt).setOnClickListener(OnClickListener);
    }

    //定义事件监听器
    private View.OnClickListener OnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            final Message message = handler.obtainMessage();
            message.what = 0x11;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd " + "HH:mm:ss");
            switch (view.getId()) {
                case R.id.addReaderBnt:       //增加读者
                    break;
                case R.id.deleteReaderBnt:    //删除
                    break;
                case R.id.updateReaderBnt:    //更新
                    break;
                case R.id.queryReaderBnt:    //按编号查询
                    new Thread(new Runnable() {
                        public void run() {
                            ResultSet res = DBUtils.queryReader(readerNumEdt.getText().toString());
                            String str = "";
                            int i = 1;
                            try {
                                while (res.next()) {
                                    Reader reader = new Reader();
                                    reader.setReader_number(res.getString("reader_number"));
                                    reader.setReader_name(res.getString("reader_name"));
                                    reader.setReader_type(res.getString("reader_type"));
                                    reader.setReader_phone(res.getString("reader_phone"));
                                    reader.setReader_password(res.getString("reader_password"));
                                    reader.setReader_createtime(
                                            String.valueOf(res.getDate("reader_createtime")));
                                    str = str + i + "." + reader.toString();
                                    i++;
                                }
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                            if (str != "") {
                                message.what = 0x12;
                                message.obj = "查询结果:\n" + str;
                            } else {
                                message.obj = "查询结果为空";
                            }
                            handler.sendMessage(message);
                        }
                    }).start();
                    break;
            }
        }
    };
}
