package com.he.week11_service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("My service", "onCreate execuated!");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String msg = intent.getStringExtra("message");
        Log.d("My service", "onStartCommand executed!" + "	intent: " + msg +
                ", flags: " + flags +
                ", startId: " + startId);
//        try {
//            Thread.sleep(20000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d("My service", "onDestroy execuated!");
        super.onDestroy();
    }

    public class MyBinder extends Binder {
        public MyService getService() {
            return MyService.this;    //返回 Service 的当前实例（就可访问 Service 中包含的数据和方法了）
        }
    }

    //  定义 MyBinder 类的一个实例，而这一类的实例恰也是 Service 的成员，用于向外部提供信息
    private MyBinder myBinder = new MyBinder();
    private String valueToShow = "我代表希望传出去的值";

    public String getValue() {
        return valueToShow;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        String msg = intent.getStringExtra("message");
        Log.d("MyService", "绑定时收到 MainActivity intent 消息: " + msg);
        return myBinder;
    }

    //解绑时执行 @Override
    public boolean onUnbind(Intent intent) {
        Log.d("MyService", "散伙就散伙——onUnbind executed!");
        return super.onUnbind(intent);
    }

    //这个自定义函数代表所有的在后台默默搬砖完成任务的函数
    public String doSomeOperation(String str) {
        Log.d("MyService", "收到 MainActivity 要求: " + str);
        return "谁怕谁，跟一个!";
    }
}







