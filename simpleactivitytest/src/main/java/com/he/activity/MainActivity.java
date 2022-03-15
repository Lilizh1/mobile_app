package com.he.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private Button clickBtn;
    private TextView showText;

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        super.onOptionsMenuClosed(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showText = (TextView) findViewById(R.id.showText);
        clickBtn = (Button) findViewById(R.id.clickBtn);

        //clickBtn.setOnClickListener(this);
        //匿名类
        clickBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showText.setText("按钮被单击！");
            }
        });
        showText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "文本框被点了...", Toast.LENGTH_LONG).show();
            }
        });

    }
    //利用继承
//   public void onClick(View v){
//        switch (v.getId()){
//            case R.id.showText:
//                showText.setText("你点我干什么！");
//            //    Toast.makeText(MainActivity.this, "文本框被点了...", Toast.LENGTH_LONG).show();
//                break;
//            case R.id.clickBtn:
//            //    showText.setText("你点我干什么！");
//                Toast.makeText(MainActivity.this, "文本框被点了...", Toast.LENGTH_LONG).show();
//                break;
//        }
//
//   }
}
