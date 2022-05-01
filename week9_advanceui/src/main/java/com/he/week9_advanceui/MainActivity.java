package com.he.week9_advanceui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("总控模块(第9周)");

        findViewById(R.id.btn_spinner).setOnClickListener(onClickListener);
        findViewById(R.id.btn_listview1).setOnClickListener(onClickListener);
        findViewById(R.id.btn_listview2).setOnClickListener(onClickListener);
        findViewById(R.id.btn_listview3).setOnClickListener(onClickListener);
        findViewById(R.id.btn_listview4).setOnClickListener(onClickListener);
        findViewById(R.id.btn_filebrowse).setOnClickListener(onClickListener);
        findViewById(R.id.btn_students).setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            switch (view.getId()){
                case R.id.btn_spinner:
                    intent.setClass(MainActivity.this, SpinnerActivity.class);
                    startActivity(intent);
                    break;
                case R.id.btn_listview1:
                    intent.setClass(MainActivity.this, ListView1_Activity.class);
                    startActivity(intent);
                    break;
                case R.id.btn_listview2:
                    intent.setClass(MainActivity.this, ListView2_Activity.class);
                    startActivity(intent);
                    break;
                case R.id.btn_listview3:
                    intent.setClass(MainActivity.this, ListView3_Activity.class);
                    startActivity(intent);
                    break;
                case R.id.btn_listview4:
                    intent.setClass(MainActivity.this, ListView4_Activity.class);
                    startActivity(intent);
                    break;
                case R.id.btn_filebrowse:
                    intent.setClass(MainActivity.this, FileBrowseActivity.class);
                    startActivity(intent);
                    break;
                case R.id.btn_students:
                    intent.setClass(MainActivity.this, StudentAddActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };
}
