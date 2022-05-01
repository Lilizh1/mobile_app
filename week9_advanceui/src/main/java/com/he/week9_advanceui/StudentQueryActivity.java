package com.he.week9_advanceui;

import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class StudentQueryActivity extends AppCompatActivity {
    //定义内部对象
    ListView listView;
    StudentDBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_query);
        this.setTitle("浏览学生列表信息");
        listView = (ListView) findViewById(R.id.studentListview);
        dbHelper = new StudentDBHelper(this);
        use_cursor();
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                final long temp = id;
                builder.setMessage("真的要删除该记录吗？")
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                dbHelper.del((int) temp);
                                use_cursor();
                            }
                        })
                        .setNegativeButton("否", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                            }
                        })
                        .create()
                        .show();
            }
        });
        dbHelper.close();
    }
    private void use_cursor() {
        Cursor cursor = dbHelper.query();
        Log.d("提示", "有结果");
        String[] from = {"_id", "num", "name"};
        int[] to = {R.id.student_id, R.id.student_num, R.id.student_name};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getApplicationContext(),
                R.layout.studentrow, cursor, from, to);
        listView.setAdapter(adapter);
    }
}
