package com.he.intentsdemo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class WriteMsgActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_msg);
        //定义组件并初始化
        TextView promptText = (TextView) findViewById(R.id.promptText);
        final EditText student_no = (EditText) findViewById(R.id.student_no);
        final EditText student_name = (EditText) findViewById(R.id.student_name);
        final EditText gitee_addr = (EditText) findViewById(R.id.gitee_addr);
        Button submitBnt = (Button) findViewById(R.id.submitBnt);

        //定义intent对象，并从中获得附加的数据，保存到Bundle对象中
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        //将取出的信息作为提示值
        promptText.setText("(学号位数：" + bundle.getInt("number_count", 0)
                + "；收集者：" + bundle.getString("teacher_name") + "）");

        //注册并实现提交按钮的点击事件监听器
        submitBnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //创建Intent实例，以携带数据，支持返回
                Intent intent = getIntent();
                //提交时要返回的数据附加到Intent对象上（此为简写，隐藏了Bundle对象）
                intent.putExtra("student_no", student_no.getText().toString());
                intent.putExtra("student_name", student_name.getText().toString());
                intent.putExtra("gitee_addr", gitee_addr.getText().toString());

//上面的3行代码等同于下面5行，写出Bundle更清晰，但熟悉后隐藏Bundle的版本更简洁
//                Bundle bundle = new Bundle();
//                bundle.putString("student_no", student_no.getText().toString());
//                bundle.putString("student_name", student_name.getText().toString());
//                bundle.putString("gitee_addr", gitee_addr.getText().toString());
//                intent.putExtras(bundle);

                //返回结果，结果码为Activity.RESULT_OK。结果码视情况可取Activity.RESULT_CANCELED或任意值。
                setResult(Activity.RESULT_OK, intent);

                //关闭Activity
                finish();
            }
        });

    }
}
