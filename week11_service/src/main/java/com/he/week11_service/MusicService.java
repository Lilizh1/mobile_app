package com.he.week11_service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MusicService extends Service {
    public MusicService() {
    }

    static boolean isplay; //定义当前播放状态
    MediaPlayer player;    //MediaPlayer 对象

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    //在创建服务时，创建 MediaPlayer  对象并加载播放的音乐文件
    @Override
    public void onCreate() {
        player = MediaPlayer.create(this, R.raw.music);
    }

    //在服务开始时实现音乐的播放 @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!player.isPlaying()) {
            player.start();
            isplay = player.isPlaying();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    //释放资源 @Override
    public void onDestroy() {
        player.stop();
        isplay = player.isPlaying();
        player.release();
        super.onDestroy();
    }

}
