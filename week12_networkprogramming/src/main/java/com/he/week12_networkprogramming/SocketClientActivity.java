package com.he.week12_networkprogramming;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClientActivity extends AppCompatActivity {
    //声明文本视图 chatmessage，用于显示聊天记录
    private TextView chatmessage = null;
    //声明编辑框 sendmessage，用于用户输入短信内容
    private EditText sendmessage = null;
    //服务器的 IP 地址，在 cmd 中用命令 ipconfig 查看实际的 IPV4 并替换
    private static final String HOST = "192.168.1.110";
    private static final int PORT = 29898;//服务器端口号
    private Socket socket = null;//声明套接字类，用于传输数据

    private BufferedReader bufferedReader = null;
    private PrintWriter printWriter = null;
    private String msg = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket_client);
        //初始化组件
        chatmessage = (TextView)findViewById(R.id.chatmessage);
        sendmessage = (EditText)findViewById(R.id.sendmessage);
        new Thread() {
            public void run() {
                try {
                    socket = new Socket(HOST, PORT);
                    bufferedReader = new BufferedReader(
                            new InputStreamReader(socket.getInputStream()));
                    printWriter = new PrintWriter(
                            new BufferedWriter(
                                    new OutputStreamWriter(socket.getOutputStream())), true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                super.run();
            }
        }.start();//启动线程
        findViewById(R.id.sendbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = sendmessage.getText().toString();
                Log.d("TAG", "将发送 1：" + message);
                if (socket != null && printWriter != null) {
                    if (socket.isConnected()) {
                        if (!socket.isOutputShutdown()) {
                            printWriter.println(message);
                            printWriter.flush();
                            chatmessage.setText(chatmessage.getText().toString() + "\n" + "发送：" + message);
                            sendmessage.setText("");

                        }
                    }
                }
            }
        });

        new Thread() {
            public void run() {
                while (true) {
                    if (socket != null && bufferedReader != null) {
                        if (socket.isConnected()) {
                            if (!socket.isInputShutdown()) {
                                try {
                                    if ((msg = bufferedReader.readLine()) != null) {
                                        Log.i("TAG", msg);
                                        chatmessage.setText(chatmessage.getText().toString() + "\n" + "接收：" + msg);
                                    }
                                } catch (Exception ex) {
                                    ex.printStackTrace();//显示异常信息
                                }
                            }
                        }
                    }
                }

            }
        }.start();
    }
}
