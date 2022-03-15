package com.he.interactactivitytest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText editHello;
    private Button bntSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//初始化组件
        editHello = (EditText) findViewById(R.id.editHello);
        bntSend = (Button) findViewById(R.id.btnSend);

        //定义事件监听代码
        bntSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putInt("num",1);
                bundle.putString("msg", editHello.getText().toString());

                Intent intent =new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtras(bundle);

                startActivityForResult(intent, 1);  //此处将1定义成静态变量更佳
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1) {   //若当前Activity可能由多处返回，此处分支将更有必要
            Bundle retBundle = data.getExtras();
            String retMsg = retBundle.getString("msg");

            editHello.setText("");
            editHello.setHint("收到回复：" + retMsg);
        }
    }

}
