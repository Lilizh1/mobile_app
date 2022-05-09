package com.he.week11_service;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class MyIntentService extends IntentService {
    public MyIntentService(String name) {
        super(name);
    }

    public MyIntentService() {
        super("Empty Constructor");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d("MyIntentService", "新任务" + startId + "即将运行");
        intent.putExtra("startId", startId);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        int startId = intent.getIntExtra("startId", 0);
        for (int i = 0; i < 5; i++) {

            Log.d("MyIntentService", "任务" + startId + "正在运行：" + i);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Log.d("MyIntentService", "任务" + startId + "运行结束");
    }
}

