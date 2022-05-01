package com.he.week9_advanceui;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StudentAddActivity extends AppCompatActivity {
    //定义组件
    private EditText studentNumEdt, studentNameEdt;
    private Button studentAddBtn, studentQueryBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_add);
        this.setTitle("添加收藏信息");
        studentNumEdt = (EditText) findViewById(R.id.studentNumEdt);
        studentNameEdt = (EditText) findViewById(R.id.studentNameEdt);
        studentAddBtn = (Button) findViewById(R.id.studentAddBtn);
        studentQueryBtn = (Button) findViewById(R.id.studentQueryBtn);
        studentAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = studentNameEdt.getText().toString();
                String num = studentNumEdt.getText().toString();
                ContentValues values = new ContentValues();
                values.put("name", name);
                values.put("num", num);
                StudentDBHelper helper = new StudentDBHelper(getApplicationContext());
                helper.insert(values);
                studentNameEdt.setText("");
                studentNumEdt.setText("");
                Toast.makeText(StudentAddActivity.this, "添加成功",
                        Toast.LENGTH_SHORT).show();
            }
        });
        studentQueryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentAddActivity.this,
                        StudentQueryActivity.class);
                startActivity(intent);
            }
        });
    }
}
