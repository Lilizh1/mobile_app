package com.he.contentprovidertest;

import android.Manifest;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManageMediaActivity extends AppCompatActivity {
    //用于取得授权的请求码
    private final int PERMISSION_REQUEST_CODE = 1;
    //定义相关组件
    Button addMediaBnt;
    Button viewMediaBnt;
    ListView mediaList;
    //定义用于存储查询结果的 ArrayList
    ArrayList<String> ids = new ArrayList<>();
    ArrayList<String> fileNames = new ArrayList<>();
    ArrayList<String> imageData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_media);
        //初始化组件
        addMediaBnt = (Button) findViewById(R.id.addMediaBnt);
        viewMediaBnt = (Button) findViewById(R.id.viewMediaBnt);
        mediaList = (ListView) findViewById(R.id.mediaList);
//设置事件监听器，将创建指定自定义类的匿名事件监听对象
        viewMediaBnt.setOnClickListener(new ViewMediaOnClickListener());
        addMediaBnt.setOnClickListener(new AddMediaOnClickListener());
//动态授权访问外部媒体
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
//若无权限，启动授权程序
            ActivityCompat.requestPermissions(
                    ManageMediaActivity.this, new String[]{
                            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }else{
//若有权限，使相关按钮变得有效
            viewMediaBnt.setEnabled(true); //查看
            addMediaBnt.setEnabled(true); //增加
        }
    }
    //对授权后的工作进行处理
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode){
            case PERMISSION_REQUEST_CODE:
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//若授权中选择允许（ALLOW）,则使相关按钮变得有效
                    viewMediaBnt.setEnabled(true); //查看
                    addMediaBnt.setEnabled(true); //增加
                }else{
//若选择了拒绝，给出提示信息
                    Toast.makeText(this, "你没有同意授权", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    //定义“添加”的事件监听器类
    private class AddMediaOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
//创建 ContentValues 对象，准备插入数据
            ContentValues values = new ContentValues();
//为媒体文件命名
            values.put(MediaStore.Images.Media.DISPLAY_NAME, "belltower");
//设置多媒体类型为 image/jpeg
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
//用 ContentResolver 对象的 insert()方法插入数据，返回所插入数据对应的uri
            Uri uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
//加载应用程序 drawable 目录中的 belltower.jpeg 图片
            Bitmap bitmap = BitmapFactory.decodeResource(
                    ManageMediaActivity.this.getResources(), R.drawable.belltower);
//定义输出流
            OutputStream os = null;
            try {
//获取刚插入的数据的 Uri 对应的输出流
                os = getContentResolver().openOutputStream(uri);
//将 Bitmap 图片保存到 Uri 对应的数据节点中
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
                os.close(); //关闭输出流
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    //定义处理“查看”的事件监听器类
    private class ViewMediaOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
//清空 ids、names、fileName 集合里原有的数据
            ids.clear();
            fileNames.clear();
            imageData.clear();
//通过 ContentResolver 查询外部存储 SD 卡中的图片文件
            Cursor cursor = getContentResolver().query(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
//逐个处理查询到的图片
            while (cursor.moveToNext()) {
//获取图片的 ID
                String id = cursor.getString((int) cursor.getColumnIndex(MediaStore.Images.Media._ID));
//获取图片的 TITLE
                String title = cursor.getString((int) cursor.getColumnIndex(MediaStore.Images.Media.TITLE));
//获取图片的保存位置的数据
                byte[] data = cursor.getBlob((int) cursor.getColumnIndex(MediaStore.Images.Media.DATA));
//将图片名添加到 ids 集合中
                ids.add(id);
//将图片 TITLE 添加到 flieNames 集合中
                fileNames.add(title);
//将图片保存路径添加到 filePaths 集合中
                imageData.add(new String(data, 0, data.length - 1));
            }
            cursor.close(); //关闭游标
//至此，获取数据的工作结束
//需要用到的图片信息存储在
//下面开始处理显示查询结果的功能
//第一步：创建一个 List 集合 listItems，将要显示的内容加入其中
            List<Map<String, Object>> listItems = new ArrayList<>();
//将 ids、names、fileNames 三个集合对象的数据转换到 Map 集合中
            for (int i = 0; i < ids.size(); i++) {
                Map<String, Object> listItem = new HashMap<>();
                listItem.put("id", ids.get(i));
                listItem.put("title", fileNames.get(i) + ".jpg");
                listItems.add(listItem);
            }
//第二步：创建一个 SimpleAdapter，将其设置为要显示结果的ListView 的数据适配器
            SimpleAdapter simpleAdapter = new SimpleAdapter(
                    ManageMediaActivity.this,
                    listItems, R.layout.line, //使用 line.xml 作为其显示布局
                    new String[]{"id", "title"},
                    new int[]{R.id.pic_id, R.id.title});
            //创建一个 SimpleAdapter 适配器成为 ListView 组件 mediaList 数据适配器
            mediaList.setAdapter(simpleAdapter);
//第三步：为 show ListView 的列表项单击事件添加监听器，监控击后在对话框中将显示图片
            mediaList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View source, int position, long id) {
//加载 view.xml 布局代表的视图
                    View viewDialog = getLayoutInflater().inflate(R.layout.view, null);
//获取 viewDialog 中 ID 为 image 的组件
                    ImageView image = (ImageView) viewDialog.findViewById(R.id.image);
//设置 image 显示指定图片
                    image.setImageBitmap(BitmapFactory.decodeFile(imageData.get(position)));
//使用对话框显示用户单击的图片
                    new AlertDialog.Builder(ManageMediaActivity.this). setView(viewDialog). setPositiveButton("确定", null).show();
                }
            });
        }
    }
}
