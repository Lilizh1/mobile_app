package com.he.contentprovidertest;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ReadContectActivity extends AppCompatActivity {
    //用于取得授权的请求码
    private final int PERMISSION_REQUEST_CODE = 1;
    //定义用于显示结果的文本框
    TextView contactsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_contect);
        //获得布局文件中的 TextView 组件
        contactsText = (TextView) findViewById(R.id.contactsText);
        //判断用户是否已经授权
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            //若未授权，利用 ActivityCompat 类的 requestPermissions()方法授予读联系人权限
            ActivityCompat.requestPermissions(
                    ReadContectActivity.this, new String[]{
                            Manifest.permission.READ_CONTACTS}, PERMISSION_REQUEST_CODE);
        }else{
//若已经授权，将查询联系人，并设置为要显示的文本
            contactsText.setText(getQueryData());
        }
    }
    //动态授权的回调方法，用于区分用户选择的同意授权和拒绝授权，分别作出处理
    // 用现在的方案，在拒绝授权后，下次启动功能，将不会再提示授权，而一直按拒绝授权处理
    // 处理方法有二：(1)在应用程序中手工授权；(2)有通过代码的解决方案，过程过于复杂，不便于在此细究，暂时放过
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch(requestCode){
            case PERMISSION_REQUEST_CODE:
                //如果同意授权（即选择 ALLOW），将取得查询结果，并设置为要显示的文本
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    contactsText.setText(getQueryData());
                }else{
                    //拒绝授权后给出提示
                    Toast.makeText(this, "你没有同意授权", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    //实现查询联系人，并返回查询到的联系人清单
    private CharSequence getQueryData() {
    //定义用于展示查询结果的字符串
        StringBuilder string = new StringBuilder("联系人清单\n");
    //获得 ContentResolver 对象，以便使用 ContentResolver 对象查找联系人数据
        ContentResolver resolver = getContentResolver();
    //执行查询：从内容提供者 ContactsContract.Contacts.CONTENT_URI 处获得所有数据
        Cursor cursor = resolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
    //获得记录联系人姓名和号码记录的索引值
        int displayNameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
    //从查询结果中取得联系人姓名，添加到结果字符串中
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            String displayName = cursor.getString(displayNameIndex); //取得联系人姓名
            string.append(" - " + displayName + "\n"); //加入结果字符串
        }
        cursor.close(); //关闭 Cursor
        return string.toString(); //返回查询结果
    }

}
