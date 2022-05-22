package com.he.week12_networkprogramming;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.socket_client).setOnClickListener(onClickListenter);
        findViewById(R.id.URL_connection).setOnClickListener(onClickListenter);
        findViewById(R.id.http_connection).setOnClickListener(onClickListenter);
        findViewById(R.id.mysql_connection).setOnClickListener(onClickListenter);
//        findViewById(R.id.download_manage).setOnClickListener(onClickListenter);
    }

    private View.OnClickListener onClickListenter=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            switch(view.getId()){
                case R.id.socket_client:
                    intent.setClass(MainActivity.this, SocketClientActivity.class);
                    startActivity(intent);
                    break;
                case R.id.URL_connection:
                    intent.setClass(MainActivity.this, URLConnectionActivity.class);
                    startActivity(intent);
                    break;

                case R.id.http_connection:
                    intent.setClass(MainActivity.this, HttpURLConnectionActivity.class);
                    startActivity(intent);
                    break;
                case R.id.mysql_connection:
                    intent.setClass(MainActivity.this, MysqlConnectionActivity.class);
                    startActivity(intent);
                    break;
//                case R.id.download_manage:
//                    download();
//                    break;
            }
        }
    };

//    private void download() {
//        //声明Uri对象，用于封装待下载文件（百度APP）的地址
//        Uri uri = Uri.parse("https://mi-public.oss-cn-hangzhou.aliyuncs.com/MosoTeach.apk");
//
//        //声明DownloadManager.Request类的对象，构造时传入文件的Uri地址
//        DownloadManager.Request request = new DownloadManager.Request(uri);
//        //设置标题、描述、保存地址等属性
//        request.setTitle("下载示例");
//        request.setDescription("下载说明");
//        request.setDestinationInExternalFilesDir(this,
//                Environment.DIRECTORY_DOWNLOADS, "temp.apk");
//        //在通知栏显示下载通知，下载过程中和下载完成后均可见
//        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//
//        //用getSystemService(Context.DOWNLOAD_SERVICE)获取DownloadManager对象
//        final DownloadManager downloadManager
//                = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
//        //用DownloadManager对象的enqueue()方法，将下载请求request加到下载队列
//        final long downloadId = downloadManager.enqueue(request);
//
//        IntentFilter filter = new IntentFilter();
//        filter.addAction(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
//        filter.addAction(DownloadManager.ACTION_NOTIFICATION_CLICKED);
//
//        final BroadcastReceiver receiver = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                String action = intent.getAction();
//                long id = intent.getLongExtra(
//                        DownloadManager.EXTRA_DOWNLOAD_ID, -1);
//                if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)) {
//                    if (id == downloadId) {
//                        Uri uri = downloadManager
//                                .getUriForDownloadedFile(downloadId);
//                        Log.i("MainActivity", "下载完毕：" + uri.toString());
//                    }
//                } else if (DownloadManager.ACTION_NOTIFICATION_CLICKED
//                        .equals(action)) {
//                    Log.i("MainActivity", "取消下载");
//                    downloadManager.remove(id);
//                }
//            }
//        };
//        registerReceiver(receiver, filter);
//    }
}
