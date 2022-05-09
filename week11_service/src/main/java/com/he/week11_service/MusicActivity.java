package com.he.week11_service;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

public class MusicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        //设置全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ImageButton playBtn = (ImageButton) findViewById(R.id.playBtn);//获取“播放/停止”按钮

//启动服务与停止服务，实现播放背景音乐与停止播放背景音乐
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MusicService.isplay == false) {
                    startService(new Intent(MusicActivity.this, MusicService.class));
                    ((ImageButton) v).setImageDrawable(getResources().getDrawable(R.drawable.play,
                            null));
                } else {
                    stopService(new Intent(MusicActivity.this, MusicService.class));
                    ((ImageButton) v).setImageDrawable(getResources().getDrawable(R.drawable.stop,
                            null));
                }
            }
        });
    }

    protected void onStart() {
        startService(new Intent(MusicActivity.this, MusicService.class));
        super.onStart();

    }
}
