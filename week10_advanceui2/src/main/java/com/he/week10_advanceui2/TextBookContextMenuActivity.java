package com.he.week10_advanceui2;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class TextBookContextMenuActivity extends AppCompatActivity {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    EditText editTitleEdt, editContentEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_book_context_menu);
        //初始化组件
        editTitleEdt = (EditText) findViewById(R.id.editTitleEdt);
        editContentEdt = (EditText) findViewById(R.id.editContentEdt);
        this.registerForContextMenu(editContentEdt);
        preferences = getSharedPreferences("my_private", MODE_PRIVATE);
        editor = preferences.edit();
    }

/*    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("秘密操作");
        menu.add(0, 201, 1, "找出小秘密");
        menu.add(0, 202, 2, "保存小秘密");
        menu.add(0, 203, 3, "退出应用");
    }*/

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("秘密操作");
        getMenuInflater().inflate(R.menu.menu_textbook, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.contextMemuRead: //从 SP 中找出小秘密
                readPrivacy();
                break;
            case R.id.contextMemuSave:    //保存小秘密
                savePrivacy();
                break;
            case R.id.contextMemuExit://退出处理
                savePrivacy();
                finish();
                break;
        }
        return super.onContextItemSelected(item);
    }

/*    @Override
    public boolean onContextItemSelected(MenuItem item) {
        //Log.d("提醒", Integer.toString(item.getItemId()));
        switch (item.getItemId()) {
            case 201: //从 SP 中找出小秘密
                readPrivacy();
                break;
            case 202:    //保存小秘密
                savePrivacy();
                break;
            case 203://退出处理：
                savePrivacy();
                finish();
                break;
        }
        return super.onContextItemSelected(item);
    }*/

    private void savePrivacy() {
        editor.putString("title", editTitleEdt.getText().toString());
        editor.putString("content", editContentEdt.getText().toString());
        editor.commit();
    }

    private void readPrivacy() {
        editTitleEdt.setText(preferences.getString("title", null));
        editContentEdt.setText(preferences.getString("content", null));
    }
}

