package com.he.week12_networkprogramming;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

public class URLConnectionActivity extends AppCompatActivity {

    String urlstr = "http://192.168.1.110:8080/Androidproject/";
    TextView showSource;
    WebView showWeb, goYtu;
    String response;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x123) {
                showSource.setText(response);
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urlconnection);
        showSource = (TextView) findViewById(R.id.showSource);
        showWeb = (WebView) findViewById(R.id.showWeb);
        goYtu = (WebView) findViewById(R.id.goLink);
        findViewById(R.id.get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        response = GetPostUtil.sendGet(urlstr + "getpage.jsp", "name=YTUer");
                        handler.sendEmptyMessage(0x123);
                    }
                }.start();
                showWeb.loadUrl(urlstr + "getpage.jsp?name=YTUer");
            }
        });
        findViewById(R.id.post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        response = GetPostUtil.sendPost(urlstr + "postpage.jsp", "name=ytu&pwd=123456");
                        handler.sendEmptyMessage(0x123);
                    }
                }.start();
                showWeb.loadUrl(urlstr + "postpage.jsp?name=ytu&pwd=123456");
            }
        });
        StringBuffer htmlBuffer = new StringBuffer();
        htmlBuffer.append("<html>");
        htmlBuffer.append("<body>点击<a href=\"http://www.ytu.edu.cn\">烟大</a></body>");
        htmlBuffer.append("</html>");
        goYtu.loadDataWithBaseURL("", htmlBuffer.toString(), "text/html", "UTF-8", "");
    }
}
