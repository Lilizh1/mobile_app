package com.he.activitylifetest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String TAG ="MainActivity";
    private Button btnGo;
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"执行了 onStart()方法");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"执行了 onStop()方法");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"执行了 onDestroy()方法");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"执行了 onPause()方法");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"执行了 onResume()方法");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"执行了 onRestart()方法");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG,"执行了 onCreate()方法");

        btnGo = (Button) findViewById(R.id.btnGo);
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }
}
