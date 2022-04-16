package com.he.contentprovidertest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    //定义组件
    private Button firstCPBnt;
    private Button readContactBnt;
    private Button manageContactBnt;
    private Button manageMediaBnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化"ContentProvider 初体验"组件并注册事件监听器
        firstCPBnt = (Button) findViewById(R.id.firstCPBnt);
        firstCPBnt.setOnClickListener(onClickListening);
        //初始化"读取联系人"组件并注册事件监听器
        readContactBnt = (Button)findViewById(R.id.readContactBnt);
        readContactBnt.setOnClickListener(onClickListening);
        //初始化"管理联系人"组件并注册事件监听器
        manageContactBnt = (Button)findViewById(R.id.manageContactBnt);
        manageContactBnt.setOnClickListener(onClickListening);
        //初始化"管理多媒体"组件并注册事件监听器
        manageMediaBnt = (Button)findViewById(R.id.manageMediaBnt);
        manageMediaBnt.setOnClickListener(onClickListening);
    }
    private View.OnClickListener onClickListening = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            switch (view.getId()) {
                case R.id.firstCPBnt: //ContentProvider 初体验
                    intent.setClass(MainActivity.this, FirstProviderActivity.class);
                    startActivity(intent);
                    break;
                case R.id.readContactBnt: //读取联系人
                    intent.setClass(MainActivity.this, ReadContectActivity.class);
                    startActivity(intent);
                    break;
                case R.id.manageContactBnt: //管理联系人
                    intent.setClass(MainActivity.this, ManageContactsActivity.class);
                    startActivity(intent);
                    break;
                case R.id.manageMediaBnt: //管理多媒体
                    intent.setClass(MainActivity.this, ManageMediaActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };

}
