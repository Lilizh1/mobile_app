package com.he.intentsdemo;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    private ImageButton linksBnt;      //收集链接
    private ImageButton openPageBnt;   //网上冲浪
    private ImageButton dialBnt;       //打电话
    private ImageButton mapBnt;        //查地图
    private ImageButton emailBnt;      //发邮件
    private ImageButton myBroadcastBnt;        //查地图
    private ImageButton receiveSMSBroadcastBnt;      //发邮件

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化收集链接按钮并注册监听器
        linksBnt = (ImageButton) findViewById(R.id.linksBnt);
        linksBnt.setOnClickListener(buttonOnClickListener);
        //初始化网上冲浪按钮并注册监听器
        openPageBnt = (ImageButton) findViewById(R.id.openPageBnt);
        openPageBnt.setOnClickListener(buttonOnClickListener);

        //初始化查地图按钮并注册监听器
        mapBnt = (ImageButton)findViewById(R.id.mapBnt);
        mapBnt.setOnClickListener(buttonOnClickListener);

        //初始化打电话按钮并注册监听器
        dialBnt = (ImageButton)findViewById(R.id.dialBnt);
        dialBnt.setOnClickListener(buttonOnClickListener);

        //初始化发邮件按钮并注册监听器
        emailBnt = (ImageButton)findViewById(R.id.emailBnt);
        emailBnt.setOnClickListener(buttonOnClickListener);

        //初始化广播按钮并注册监听器
        myBroadcastBnt = (ImageButton)findViewById(R.id.myBroadcastBnt);
        myBroadcastBnt.setOnClickListener(buttonOnClickListener);

        //初始化发短信按钮并注册监听器
        receiveSMSBroadcastBnt = (ImageButton)findViewById(R.id.receiveSMSBroadcastBnt);
        receiveSMSBroadcastBnt.setOnClickListener(buttonOnClickListener);


    }
    //定义事件监听的处理逻辑
    private View.OnClickListener buttonOnClickListener =
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    Uri data;
                    switch(view.getId()){
                        case R.id.linksBnt: //收集链接
                            intent.setClass(MainActivity.this, LinksActivity.class);
                            //下面一行用于后面的“体验标志位”要求，切换方式不同
                            //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            break;
                        case R.id.openPageBnt: //打开网页
                            intent.setAction(Intent.ACTION_VIEW);
                            data = Uri.parse("http://www.baidu.com");
                            intent.setData(data);
                            // 上面分步的设置，与下面的写法等价
                            // Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.ytu.edu.cn"));
                            startActivity(intent);
                            break;
                        case R.id.mapBnt: //查地图
                            intent.setAction(Intent.ACTION_VIEW);
                            data = Uri.parse("geo:37.465108105834844, 121.47892736523434");
                            intent.setData(data);
                            startActivity(intent);
                            break;
                        case R.id.dialBnt: //打电话
                            intent.setAction(Intent.ACTION_DIAL).setData(Uri.parse("tel:19861109689"));
                            startActivity(intent);
                            break;
                        case R.id.emailBnt: //发邮件
                            intent.setAction(Intent.ACTION_SENDTO);
                            data = Uri.parse("mailto:1307713043@qq.com");
                            intent.setData(data);
                            startActivity(intent);
                            break;
                        case R.id.myBroadcastBnt: //发送广播
                            //很多参考书中的示例未setComponent而导致收不到广播
                            intent.setComponent(new ComponentName(getPackageName(),
                                    "com.he.intentsdemo.MyBroadcastReceiver"));
                            intent.putExtra("broadcast_content", "自己发广播自己玩。");
                            Log.d("提示", "发送广播");
                            sendBroadcast(intent);

                            intent.putExtra("broadcast_content", "乖乖宅校，抗疫先锋！");
                            Log.d("提示", "再发送广播");
                            sendBroadcast(intent);
                            break;
                        case R.id.receiveSMSBroadcastBnt: //为接受系统短信广播，动态设置权限
                            //如果没有读短信和收短信的权限，只需要授权一次即可
                            //若不专门设置此按钮处理短信的话，这一段放在onCreate()方法中也合适
                            if (ActivityCompat.checkSelfPermission(MainActivity.this,
                                    Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED ||
                                    ActivityCompat.checkSelfPermission(MainActivity.this,
                                            Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
                                //利用代码为应用动态设置读短信和收短信的权限
                                ActivityCompat.requestPermissions(MainActivity.this,
                                        new String[]{Manifest.permission.READ_SMS,
                                                Manifest.permission.RECEIVE_SMS},1);
                            }
                            break;


                    }
                }
            };

}
