package com.he.week8_storage1;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileStorageActivity extends AppCompatActivity {
    //要操作的文件名
    final String FILE_NAME = "memo.txt";
    //标识文件是否被修改，初值为false
    boolean ischanged = false;
    //定义组件
    ImageView openFileBnt, saveFileBnt, exitFileBnt;
    EditText editFileEdt, fielPathEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_storage);
        //初始化组件
        openFileBnt = (ImageView) findViewById(R.id.openFileBnt);
        saveFileBnt = (ImageView) findViewById(R.id.saveFileBnt);
        exitFileBnt = (ImageView) findViewById(R.id.exitFileBnt);
        editFileEdt = (EditText) findViewById(R.id.editFileEdt);
        fielPathEdt = (EditText) findViewById(R.id.fielPathEdt);
        //为图片按钮注册事件监听器
        openFileBnt.setOnClickListener(onClickListener);
        saveFileBnt.setOnClickListener(onClickListener);
        exitFileBnt.setOnClickListener(onClickListener);
        //设置编辑框中内容发生变化时的事件监听器，以检测、记录内容是否发生变化
        editFileEdt.addTextChangedListener(textChangedListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.openFileBnt:   //读文件
                    readFile();
                    break;
                case R.id.saveFileBnt:   //保存文件后，重置文件内容被修改标志
                    writeFile();
                    ischanged = false;
                    break;
                case R.id.exitFileBnt:   //退出处理：若有改动需确认是否保存
                    if (ischanged) {   //当有改动时
                        AlertDialog.Builder alterDialog = new AlertDialog.Builder(FileStorageActivity.this);
                        alterDialog.setTitle("提示")
                                .setMessage("文件有改动，是否保存")
                                .setPositiveButton("保存", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        //当确定保存时，写文件后退出
                                        writeFile();
                                        finish();
                                    }
                                })
                                .setNegativeButton("不保存", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        //选择不保存时，直接退出
                                        finish();
                                    }
                                })
                                .create()
                                .show();
                    } else { //没有改动时直接退出
                        finish();
                        Toast.makeText(FileStorageActivity.this, "安全退出",
                                Toast.LENGTH_SHORT).show();
                    }
            }
        }
    };

    //写入文件
    private void writeFile() {
        //取得文件内容（适用于小文件）
        String str = editFileEdt.getText().toString();
        try {
            //定义输出流对象并打开文件
            FileOutputStream fos = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            //用 write方法写入字节流
            fos.write(str.getBytes());
        //关闭文件
            fos.close();
        } catch (FileNotFoundException e) {  //异常捕获（由 IDE自动生成）
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //读文件内容
    private void readFile() {
        try {
            //定义文件输入流对象并打开文件
            FileInputStream fis = openFileInput(FILE_NAME);

            //定义用于读入文件的缓冲区
            byte[] buff = new byte[1024];
            //已读入字节数
            int hasRead = 0;
            //定义并初始字符串构造器对象
            StringBuilder sb = new StringBuilder();
            //循环将文件内容读入缓冲区并合并到字符串构造器
            while ((hasRead = fis.read(buff)) > 0) {
                sb.append(new String(buff, 0, hasRead));
            }
            //关闭文件
            fis.close();
            //更新组件内容
            editFileEdt.setText(sb);
            //更新文件路径信息
            fielPathEdt.setText(getFilesDir().toString());
        } catch (FileNotFoundException e) {    //异常捕获（由 IDE自动生成）
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //定义编辑框中文本改变的监听器（框架由 IDE辅助生成）
    private TextWatcher textChangedListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        //当文本改变后，将已修改标记改为  true
        @Override
        public void afterTextChanged(Editable editable) {
            ischanged = true;
        }
    };

}
