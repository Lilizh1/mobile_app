package com.he.week11_service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyNewThreadService extends Service {
    public MyNewThreadService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, final int startId) {
        Log.d("My NewThreadService", "新任务" + startId + "即将运行");
        new Thread() {
            public void run() {
                for (int i = 0; i < 5; i++) {
                    Log.d("My NewThreadService", "任务" + startId + "正在运行：" + i);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Log.d("My NewThreadService", "任务" + startId + "运行结束");
            }
        }.start();
        //stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}


