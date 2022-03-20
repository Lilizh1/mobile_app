package com.he.layoutdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText EditText1;
    private EditText EditText2;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText1 = (EditText) findViewById(R.id.editText1);
        EditText2 = (EditText) findViewById(R.id.editText2);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username="201958501313";
                String password="123456";
                if(username.equals(EditText1.getText().toString())==true&&password.equals(EditText2.getText().toString())==true) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("num", 1);

                    Intent intent = new Intent(MainActivity.this, welcome.class);
                    intent.putExtras(bundle);

                    startActivityForResult(intent, 1);
                }
                else{
                       Toast.makeText(MainActivity.this, "学号或密码有误请重新输入！", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
