package com.he.contentprovidertest;

import android.Manifest;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ManageContactsActivity extends AppCompatActivity {
    //定义组件对象
    private Button searchBnt, addBnt;
    private TextView resultTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_contacts);
        //初始化组件
        searchBnt = (Button)findViewById(R.id.searchContactBnt);
        addBnt = (Button)findViewById(R.id.addContactBnt);
        resultTxt = (TextView)findViewById(R.id.searchResultTxt);
//为按钮设置事件监听
//这是为每一个按钮的事件监听定义一个新类并建立一个匿名对象的写法
// 这同以前建立事件监听对象的方法一样的效果，
        searchBnt.setOnClickListener(new SearchOnClickListener());
        addBnt.setOnClickListener(new AddOnClickListener());
//首先检查并授权，授权不通过时，按钮将保持无效状态
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CONTACTS)
                        != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    ManageContactsActivity.this, new String[]{
                            Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS}, 1); //未授权时进行授权
        } else {
            searchBnt.setEnabled(true); //已经获得授权，则使按钮变为有效
            addBnt.setEnabled(true);
        }
    }
    //定义授权操作后的回调方法
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    searchBnt.setEnabled(true); //获得授权，则使按钮变为有效
                    addBnt.setEnabled(true);
                } else {
//未获得授权时，提示信息
                    Toast.makeText(this, "你没有同意授权", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    //定义响应查询功能的事件监听类
//将实现按姓名查询联系人的功能
    private class SearchOnClickListener implements View.OnClickListener {
        @Override
         public void onClick(View view) {
//定义及初始化查询结果的显示内容
          String resultStr = "查询结果：";
//定义组件，并获得输入的姓名
            EditText nameEdit = (EditText)findViewById(R.id.nameEdit);
            String nameTxt = nameEdit.getText().toString();
//定义查询条件，默认为 null，表示姓名为空是查询所有联系人
            String condition = null;
            String[] selectionArgs = null;
//若姓名处不为空，则根据输入内容构造条件
            if (!nameTxt.isEmpty()) {
//随 SDK 版本升级，联系人内容提供者的 ID 有过变化，故构造条件时需要分情况对待
             condition =
                    (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) ?
                        (ContactsContract.Contacts.DISPLAY_NAME_PRIMARY + " LIKE?") :
                        (ContactsContract.Contacts.DISPLAY_NAME + " LIKE ?");
//定义用于匹配条件中?的参数
            selectionArgs = new String[1];
            selectionArgs[0] = "%" + nameTxt + "%"; //输入姓名前后可为任意
                }
//使用 ContentResolver 的 query()方法执行查询，结果保存在cursor 对象中
            Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, condition, selectionArgs, null);
//获得联系人 ID 和姓名的索引值
                int idIndex = cursor.getColumnIndex(ContactsContract.Contacts._ID);

                int nameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
//对查询到的每一位联系人进行遍历
                while (cursor.moveToNext()) {
//获取联系人 ID
                    String contractId = cursor.getString(idIndex);
//获取联系人的名字
                    String name = cursor.getString(nameIndex);
//构造要显示的内容
                    resultStr = resultStr + "\n===" + contractId + ". " + name + "===";
//继续使用 ContentResolver 查找联系人的电话号码
                    Cursor phones =
                            getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contractId, null, null);
//遍历该联系人的多个电话号码
                    while (phones.moveToNext()) {
//获得电话号码所在列的索引
                        int numberIndex = phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
//获取查询结果中电话号码列中的数据
                        String phoneNumber = phones.getString(numberIndex);
//构造要显示的内容
                        resultStr = resultStr + "\n 电话号码：" + phoneNumber;
                    }
                    phones.close(); //关闭电话号码的查询结果
//使用 ContentResolver 查找联系人的 E-mail 地址
                    Cursor emails =
                            getContentResolver().query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null, ContactsContract.CommonDataKinds.Email.CONTACT_ID + "=" + contractId, null, null);
//遍历该联系人的多个 E-mail 地址
                    while (emails.moveToNext()) {
//获取 Email 所在列的索引
                        int emailIndex = emails.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA);
//获取查询结果中的 E-mail 地址列中的数据

                        String emailAddress = emails.getString(emailIndex);
//构造要显示的内容
                        resultStr = resultStr + "\n 邮件地址：" + emailAddress;
                    }
                    emails.close(); //关闭电子邮件的查询结果
                }
                cursor.close(); //关闭联系人的查询结果
//将查询结果显示在文本框中
                resultTxt.setText(resultStr);
            }
        }
        //定义单击 add 按钮的事件监听类
        private class AddOnClickListener implements View.OnClickListener {
            @Override
            public void onClick(View view) {
//定义界面中的三个文本框对应的组件并获得其值
                EditText nameEdit = (EditText) findViewById(R.id.nameEdit);
                String name = nameEdit.getText().toString();
                EditText phoneEdit = (EditText) findViewById(R.id.phoneEdit);
                String phoneNumber = phoneEdit.getText().toString();
                EditText emailEdit = (EditText) findViewById(R.id.emailEdit);
                String emailAddress = emailEdit.getText().toString();
//创建一个空的 ContentValues，用于构造要插入的值
                ContentValues values = new ContentValues();
//向 RawContacts.CONTENT_URI 执行一个空值插入，目的是获取系统返回的rawContactId
                Uri rawContactUri = getContentResolver().insert(ContactsContract.RawContacts.CONTENT_URI, values);
                long rawContactId = ContentUris.parseId(rawContactUri);
//加入 name
                values.clear();
                values.put(ContactsContract.Contacts.Data.RAW_CONTACT_ID, rawContactId);
//设置内容类型
                values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
//设置联系人名字
                values.put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, name);
//向联系人 URI 添加联系人名字
                getContentResolver().insert(ContactsContract.Data.CONTENT_URI, values);

//加入 phone
                values.clear();
                values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);
                values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
//设置联系人的电话号码
                values.put(ContactsContract.CommonDataKinds.Phone.NUMBER, phoneNumber);
//设置电话类型
                values.put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);
//向联系人电话号码 URI 添加电话号码
                getContentResolver().insert(ContactsContract.Data.CONTENT_URI, values);
//加入 E_mail
                values.clear();
                values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);
                values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE);
//设置联系人的 E-mail 地址
                values.put(ContactsContract.CommonDataKinds.Email.DATA, emailAddress);
//设置该电子邮件的类型
                values.put(ContactsContract.CommonDataKinds.Email.TYPE, ContactsContract.CommonDataKinds.Email.TYPE_WORK);
//向联系人 E-mail URI 添加 E-mail 数据
                getContentResolver().insert(ContactsContract.Data.CONTENT_URI, values);
//提示加入成功消息
                resultTxt.setText("成功添加联系人：" +
                        "\n===" + rawContactId + ". " + name + "===" +
                        "\n 电话号码：" + phoneNumber +
                        "\n 邮件地址：" + emailAddress);
            }
        }
}
