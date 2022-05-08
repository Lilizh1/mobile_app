package com.he.week8_storage1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //注册各组件的事件监听器
        findViewById(R.id.fileStorage).setOnClickListener(onClickListener);
        findViewById(R.id.userinfo).setOnClickListener(onClickListener);
        findViewById(R.id.libraryDatabase).setOnClickListener(onClickListener);
    }

    //各组件被单击后，调用对应的Activity
    private View.OnClickListener onClickListener =
            new View.OnClickListener() {
                Intent intent = new Intent();

                @Override
                public void onClick(View view) {
                    switch (view.getId()) {
                        case R.id.fileStorage:
                            intent.setClass(MainActivity.this, FileStorageActivity.class);
                            startActivity(intent);
                            break;
                        case R.id.userinfo:
                            intent.setClass(MainActivity.this, SharedUserinfoActivity.class);
                            startActivity(intent);
                            break;
                        case R.id.libraryDatabase:
                            intent.setClass(MainActivity.this, LibraryReaderActivity.class);
                            startActivity(intent);
                            break;
                    }
                }
            };
}
