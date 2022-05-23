package com.he.week13_moretech;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //为页面中的按钮注册监听事件
        findViewById(R.id.login_bnt).setOnClickListener(this);
        findViewById(R.id.bottom_navigate_bnt).setOnClickListener(this);
        findViewById(R.id.appplication_bnt).setOnClickListener(this);
        findViewById(R.id.save_instance_bnt).setOnClickListener(this);
        findViewById(R.id.action_item_bnt).setOnClickListener(this);
        findViewById(R.id.action_return_bnt).setOnClickListener(this);
        findViewById(R.id.sensor_test_bnt).setOnClickListener(this);
        findViewById(R.id.sensor_accelerometer_bnt).setOnClickListener(this);
    }

    //本周的事件监听采用另一种写法：新建的Activity类实现OnClickListener接口（见类的定义）
    //在前面大多数时候，采用的是创建事件监听类的对象进行
    //在实践中，可以任选事件监听的形式(不止这两种写法)，或者按团队约定的统一写法
    @Override
    public void onClick(View v) {
        //5.1 利用Activity模板
        if (v.getId() == R.id.login_bnt) {//5.1.1 登录Activity
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
        else if (v.getId() == R.id.bottom_navigate_bnt) {//5.1.2 底部导航Actvity
            startActivity(new Intent(MainActivity.this, BottomNaviActivity.class));
        }
        //5.2 扩大数据的作用范围/生存期
        else if (v.getId() == R.id.appplication_bnt) {//5.2.1 应用程序的全局变量
            startActivity(new Intent(MainActivity.this, SetApplicationActivity.class));
        } else if (v.getId() == R.id.save_instance_bnt) {//5.2.2 我不怕被回收
            startActivity(new Intent(MainActivity.this, SaveInstanceActivity.class));
        }
//        //5.3 使用Action Bar
        else if (v.getId() == R.id.action_item_bnt) {//5.3.1 标题栏上的菜单
            startActivity(new Intent(MainActivity.this, ActionBarActivity.class));
        } else if (v.getId() == R.id.action_return_bnt) {//5.3.2 层级式导航
            startActivity(new Intent(MainActivity.this, ParentActivity.class));
        }
//        //5.4 使用传感器
        else if (v.getId() == R.id.sensor_test_bnt) {//5.4.1 测试传感器
            startActivity(new Intent(MainActivity.this, SensorTestActivity.class));
        } else if (v.getId() == R.id.sensor_accelerometer_bnt) {//5.4.2 加速度传感器
            startActivity(new Intent(MainActivity.this, SensorAccelerometerActivity.class));
        }
    }
}
