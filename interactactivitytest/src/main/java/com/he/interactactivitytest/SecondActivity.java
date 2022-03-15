package com.he.interactactivitytest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    //定义组件对象
    private TextView msg;
    private Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        //创建组件对应的对象
        msg = (TextView) findViewById(R.id.receiveMsg);
        btn = (Button) findViewById(R.id.feedbackBtn);

        //新④ 用Intent对象接受并处理数据
        Intent intent = getIntent();

        msg.setText(intent.getIntExtra("num",0)  + ": "
                + intent.getStringExtra("msg"));

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                intent.putExtra("msg", "I'm fine, thank you!");
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }
}
