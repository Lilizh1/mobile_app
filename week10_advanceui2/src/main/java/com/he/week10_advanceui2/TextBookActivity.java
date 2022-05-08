package com.he.week10_advanceui2;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TextBookActivity extends AppCompatActivity {
    //要操作的文件名
    final String FILE_NAME = "memo.txt";
    //标识文件是否被修改，初值为 false
    boolean ischanged = false;
    //定义组件
    EditText editFileEdt, fielPathEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_book);
        //初始化组件
        editFileEdt = (EditText) findViewById(R.id.editFileEdt);
        fielPathEdt = (EditText) findViewById(R.id.fielPathEdt);

        editFileEdt.addTextChangedListener(textChangedListener);
    }

/*    //重写创建菜单的方法 @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        int group1 = 1;
        int gourp2 = 2;
        menu.add(group1, 201, 1, "打开文件");
        menu.add(group1, 202, 2, "保存文件");
        menu.add(gourp2, 203, 3, "退出应用");
        menu.add(gourp2, 204, 4, "关于...");
        return true;
    }*/

    public boolean onCreateOptionsMenu(Menu menu) {
        SubMenu subMenu = menu.addSubMenu(0, Menu.NONE, Menu.NONE, "文件操作");
        MenuItem openItem = subMenu.add(2, 201, 1, "打开文件");
        openItem.setIcon(R.drawable.open);
        MenuItem saveItem = subMenu.add(2, 202, 2, "保存文件");
        saveItem.setIcon(R.drawable.save);
        menu.add(2, 203, 3, "退出应用");
        menu.add(2, 204, 4, "关于...");
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 201: //读文件
                readFile();
                break;
            case 202:    //保存文件后，重置文件内容被修改标志
                writeFile();
                ischanged = false;
                break;
            case 203://退出处理：若有改动需确认是否保存
                if (ischanged) {    //当有改动时
                    AlertDialog.Builder alterDialog = new AlertDialog.Builder(TextBookActivity.this);
                    alterDialog.setTitle("提示")
                            .setMessage("文件有改动，是否保存")
                            .setPositiveButton("保存", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    writeFile();
                                    finish();
                                }
                            })
                            .setNegativeButton("不保存", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    finish();
                                }
                            })
                            .create()
                            .show();
                } else {
                    finish();
                    Toast.makeText(TextBookActivity.this, "安全退出",
                            Toast.LENGTH_SHORT).show();
                }
                break;
            case 204:
                AlertDialog.Builder alterDialog = new AlertDialog.Builder(TextBookActivity.this);
                alterDialog.setTitle("提示")
                        .setMessage("通过这个 Demo，学会各种菜单。")
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .create()
                        .show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    //写入文件
    private void writeFile() {
        String str = editFileEdt.getText().toString();
        try {
            FileOutputStream fos = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            fos.write(str.getBytes());
            fos.close();
        } catch (FileNotFoundException e) {    //异常捕获（由 IDE 自动生成）
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFile() {
        try {
            FileInputStream fis = openFileInput(FILE_NAME);
            byte[] buff = new byte[1024];
            int hasRead = 0;
            StringBuilder sb = new StringBuilder();
            while ((hasRead = fis.read(buff)) > 0) {
                sb.append(new String(buff, 0, hasRead));
            }
            fis.close();
            editFileEdt.setText(sb);
            fielPathEdt.setText(getFilesDir().toString());
        } catch (FileNotFoundException e) {    //异常捕获（由 IDE 自动生成）
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //定义编辑框中文本改变的监听器（框架由 IDE  辅助生成）
    private TextWatcher textChangedListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            ischanged = true;
        }
    };
}