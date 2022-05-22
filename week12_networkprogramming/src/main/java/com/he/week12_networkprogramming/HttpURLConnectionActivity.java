package com.he.week12_networkprogramming;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpURLConnectionActivity extends AppCompatActivity {
    TextView showTextView = null;
    ImageView showImageView = null;
    String resultStr = "";
    ProgressBar progressBar = null;
    ViewGroup viewGroup = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_urlconnection);
        showTextView = (TextView) findViewById(R.id.textview_show);
        showImageView = (ImageView) findViewById(R.id.imagview_show);
        findViewById(R.id.btn_visit_web).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImageView.setVisibility(View.GONE);
                showTextView.setVisibility(View.VISIBLE);
                Thread visitBaiduThread = new Thread(new VisitWebRunnable());
                visitBaiduThread.start();
                try {
                    visitBaiduThread.join();

                    if (!resultStr.equals("")) {
                        showTextView.setText(resultStr);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        findViewById(R.id.btn_download_img).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImageView.setVisibility(View.VISIBLE);
                showTextView.setVisibility(View.GONE);
                String imgUrl = "http://p3.itc.cn/q_70/images03/20200710/a31a2a2fb5434a438e39b443a53dca37.jpeg";
                new DownImgAsyncTask().execute(imgUrl);
            }
        });
    }

    //自定义类，内含新线程连接 HttpURL 获得网页
    class VisitWebRunnable implements Runnable {
        @Override
        public void run() {
            HttpURLConnection conn = null; //连接对象
            InputStream is = null; //输入流
            String resultData = ""; //
            try {
                URL url = new URL("https://m.baidu.com/"); //URL 对象
                conn = (HttpURLConnection) url.openConnection(); //使用 URL 打开一个链接
                conn.setDoInput(true); //允许输入流，即允许下载
                conn.setDoOutput(true); //允许输出流，即允许上传
                conn.setUseCaches(false); //不使用缓冲
                conn.setRequestMethod("GET"); //使用 get 请求
                is = conn.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader bufferReader = new BufferedReader(isr);
                String inputLine = "";
                while ((inputLine = bufferReader.readLine()) != null) {
                    resultData += inputLine + "\n";
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (conn != null) {
                    conn.disconnect();
                }
            }
            resultStr = resultData;
        }
    }

    class DownImgAsyncTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showImageView.setImageBitmap(null);
            showProgressBar();
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            URL imgUrl = null;
            Bitmap bitmap = null;
            try {
                imgUrl = new URL(params[0]);
                HttpURLConnection conn = (HttpURLConnection) imgUrl.openConnection();
                conn.setDoInput(true);
                conn.connect();
                InputStream is = conn.getInputStream();
                bitmap = BitmapFactory.decodeStream(is);
                is.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            if (result != null) {
                dismissProgressBar();
                showImageView.setImageBitmap(result);
            }
        }
    }

    private void showProgressBar() {
        progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleLarge);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        progressBar.setVisibility(View.VISIBLE);
        Context context = getApplicationContext();
        viewGroup = (ViewGroup) findViewById(R.id.parent_view);
        viewGroup.addView(progressBar, params);
    }

    private void dismissProgressBar() {
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
            viewGroup.removeView(progressBar);
            progressBar = null;
        }
    }
}