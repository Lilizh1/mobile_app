package com.he.week13_moretech;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ParentActivity extends AppCompatActivity {
    //定义按钮
    Button seekChildBnt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent);
        setTitle("上一级页面");

        seekChildBnt= (Button) findViewById(R.id.seekChildBnt);
        seekChildBnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ParentActivity.this,ChildActivity.class); //创建 Intent 对象
                startActivity(intent); //启动 Activity
            }
        });
    }
}
