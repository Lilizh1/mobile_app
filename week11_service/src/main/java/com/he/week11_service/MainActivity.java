package com.he.week11_service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;

//目前给出的是整个程序的框架，缺失的需要你随着进展填上
public class MainActivity extends AppCompatActivity {
    private MyService myService;
    private ServiceConnection myServiceConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d("ServiceConnection", "绑定" + componentName.getShortClassName());
            myService = ((MyService.MyBinder) iBinder).getService();
            Log.d("ServiceConnection", "绑定后就能从服务中取得值——" + myService.getValue());
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d("ServiceConnection", "要与" + componentName.getShortClassName() + "解绑");
            myService = null;
        }
    };

    private void show_notification() {
        Intent intent = new Intent(android.provider.Settings.ACTION_SETTINGS);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        // 常用的调用形式之一：getActivity(Context context, int requestCode, Intent intent, int flags)
        //   建立一个通知管理类的对象，负责发通知、清除通知等
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("通知标题：举一反三")
                .setContentText(getString(R.string.notification2))
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                //.setLargeIcon(BitmapFactory.decodeResource(getResources(),  R.mipmap.ic_launcher))
                .setContentIntent(pendingIntent)
                //.setSound(Uri.fromFile(new File("/system/media/audio/ringtones/Luna.ogg")))
                //.setVibrate(new long[]{0, 1000, 1000, 1000})
                //.setLights(Color.GREEN, 1000, 1000)
                //.setPriority(NotificationCompat.PRIORITY_MAX)
                .build();
        manager.notify(1, notification);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. 理解Service：启动和停止Service的事件监听
        findViewById(R.id.start_service).setOnClickListener(onClick);
        findViewById(R.id.stop_service).setOnClickListener(onClick);

        //2. Activity和Service间的通信：绑定、解绑、操作Service的事件监听
        findViewById(R.id.bind_service).setOnClickListener(onClick);
        findViewById(R.id.unbind_service).setOnClickListener(onClick);
        findViewById(R.id.operate_service).setOnClickListener(onClick);

        //3. 耗时服务解决方案
        findViewById(R.id.new_Thread).setOnClickListener(onClick);
        findViewById(R.id.intent_service).setOnClickListener(onClick);

        //4. 系统服务用法：以通知服务为例
        findViewById(R.id.notify_service_fore).setOnClickListener(onClick);

        //5. 服务的应用
        findViewById(R.id.music_service).setOnClickListener(onClick);
        findViewById(R.id.randomNUm_sevice).setOnClickListener(onClick);

    }


    //定义事件监听的逻辑
    private View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            switch (view.getId()) {
                // 1. 下面是用于体验启动和停止服务的分支
                case R.id.start_service:  //启动Service
                    intent.setClass(MainActivity.this, MyService.class);
                    intent.putExtra("message", "启动服务");
                    startService(intent);
                    break;
                case R.id.stop_service:   //停止Service
                    intent.setClass(MainActivity.this, MyService.class);
                    stopService(intent);
                    break;
                //2. 下面是用于体验绑定、解绑的分支
                case R.id.bind_service:  //绑定Service
                    intent.setClass(MainActivity.this, MyService.class);
                    Log.d("MainActivity", "要绑定Service，请Intent带句话——");
                    intent.putExtra("message", "耍个朋友噻！");
                    bindService(intent, myServiceConn, Context.BIND_AUTO_CREATE);
                    break;
                case R.id.operate_service:  //操作Serivce
                    if (myService == null)
                        return;//调用服务中用于处理业务逻辑的代码
                    Log.d("MainActivity", "向Service发起请求，一起干点啥");
                    //用绑定后获得的服务实例，调用服务完成一些操作
                    String returnValue = myService.doSomeOperation("走一个！");
                    Log.d("MainActivity", "接收Service返回的结果：" + returnValue);
                    break;
                case R.id.unbind_service:    //解绑Service
                    unbindService(myServiceConn);
                    break;
                //3. 下面体验在Service中执行耗时任务的方法
                case R.id.new_Thread:   //在Service中开新线程执行耗时任务的方法
                    intent.setClass(MainActivity.this, MyNewThreadService.class);
                    startService(intent);
                    break;
                case R.id.intent_service:   //利用IntentService执行耗时任务的方法
                    intent.setClass(MainActivity.this, MyIntentService.class);
                    startService(intent);
                    break;
                //4. 系统Service应用
                case R.id.notify_service_fore:   //通知服务
                    show_notification();
                    break;
                //5. Service应用
                case R.id.music_service:  //后台音乐
                    intent.setClass(MainActivity.this, MusicActivity.class);
                    startActivity(intent);
                    break;
                case R.id.randomNUm_sevice: //随机号码
                    intent.setClass(MainActivity.this, RandomNumActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };

}
