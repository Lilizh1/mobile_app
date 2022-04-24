package com.he.week8_storage1;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

public class SharedUserinfoActivity extends AppCompatActivity {
    //声明一个共享参数对象，用于  SP读取数据
    private SharedPreferences preferences;
    //声明一个共享参数编辑器对象，用于修改和写入  SP数据
    private SharedPreferences.Editor editor;
    //定义组件
    private EditText nameEdt;
    private EditText ageEdt;
    private EditText weightEdt;
    private CheckBox marriedCheck;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_userinfo);
        //初始化组件
        nameEdt = (EditText) findViewById(R.id.nameSpEdt);
        ageEdt = (EditText) findViewById(R.id.ageSpEdt);
        weightEdt = (EditText) findViewById(R.id.weightSpEdt);
        marriedCheck = (CheckBox) findViewById(R.id.marriedSpCheck);
        //为按钮组件注册事件监听器
        findViewById(R.id.saveSpBnt).setOnClickListener(OnClickListener);
        findViewById(R.id.browseSpBnt).setOnClickListener(OnClickListener);
        //获取共享参数对象实例，参数分别是文件名的打开方式
        preferences = getSharedPreferences("user_information", MODE_PRIVATE);
        //获得共享参数对象实例编辑器对象
        editor = preferences.edit();
    }

    //定义事件监听器对象
    private final View.OnClickListener OnClickListener =
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch (view.getId()) {
                        case R.id.saveSpBnt: //将数据存入  SharedPreferences
                            //用编辑器对象的 putXXX()方法记录各种类型数据，完成写入操作
                            //每项数据都有命名和取值，使构成 key-value>键值对
                            //添加一个名叫  name的字符串参数
                            editor.putString("name", nameEdt.getText().toString());
                            //添加一个名叫  age的整型参数
                            editor.putInt("age", Integer.parseInt(ageEdt.getText().toString()));
                            //添加一个名叫  weight的浮点数参数
                            editor.putFloat("weight", Float.parseFloat(weightEdt.getText().toString()));
                            //添加一个名叫  married的布尔型参数
                            editor.putBoolean("married", marriedCheck.isChecked());
                            //添加一个名叫  update_time的时间类型参数，值为限定格式的当前时间
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年 MM月 dd日   " + "HH:mm:ss");
                            editor.putString("update_time", sdf.format(new Date()));
                            //提交编辑器中的修改——一个关键操作
                            editor.commit();
                            //用土司提示完成操作
                            Toast.makeText(SharedUserinfoActivity.this,
                                    "数据已写入共享参数",
                                    Toast.LENGTH_SHORT)
                                    .show();
                            break;
                        case R.id.browseSpBnt:   //取出 SP中的数据
                            //按关键字取出 SP中的各项值，并组合成要在对话框呈现的字符串
                            String str = "姓名：" + preferences.getString("name", null) +
                                    "\n年龄：" + preferences.getInt("age", 0) +
                                    "\n体重：" + preferences.getFloat("weight", 0) +
                                    "\n婚否：" + (preferences.getBoolean("married", false) ? "是" : "否");
                            //在对话框中显示结果
                            AlertDialog.Builder alertDialog =
                                    new AlertDialog.Builder(SharedUserinfoActivity.this);
                            alertDialog.setTitle("用户信息")
                                    .setMessage(str)
                                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                        }
                                    })
                                    .create()
                                    .show();
                            //将取出的值直接显示在界面中的组件上（登录模块中的”记住密码“功能的方案）
                            nameEdt.setText(preferences.getString("name", ""));
                            ageEdt.setText(Integer.toString(preferences.getInt("age", 0)));
                            weightEdt.setText(Float.toString(preferences.getFloat("weight", 0)));
                            marriedCheck.setChecked(preferences.getBoolean("married", false));
                            break;
                    }
                }
            };

}
