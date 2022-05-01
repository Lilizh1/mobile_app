package com.he.week9_advanceui;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileBrowseActivity extends AppCompatActivity {
    //定义组件
    ListView listView;
    TextView textView;
    //记录当前的父文件夹
    File currentParent;
    //记录当前路径下所有文件的文件数组
    File[] currentFiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_browse_);
        ListView listView = (ListView) findViewById(R.id.fileList);
        textView = (TextView) findViewById(R.id.filePath);
        File root = Environment.getExternalStorageDirectory();
        if (root.exists()) {
            currentParent = root;
            currentFiles = root.listFiles();
            inflateListView(currentFiles);
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if (currentFiles[position].isFile())
                    return;
                File[] tmp = currentFiles[position].listFiles();
                if (tmp == null || tmp.length == 0) {
                    Toast.makeText(FileBrowseActivity.this,
                            "当前路径不可访问或该路径下没有文件", Toast.LENGTH_LONG)
                            .show();
                } else {    //当文件夹不为空时
                    currentParent = currentFiles[position];
                    currentFiles = tmp;
                    inflateListView(currentFiles);
                }
                Button parent = (Button) findViewById(R.id.fileParent);
                parent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            if (!currentParent.getCanonicalPath().equals("/storage/emulated/0")) {
                                currentParent = currentParent.getParentFile();
                                currentFiles = currentParent.listFiles();
                                inflateListView(currentFiles);

                            }
                        } catch (Exception e) {
                            e.printStackTrace();

                        }
                    }
                });
            }
        });
    }

    private void inflateListView(File[] files) {
        List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
        if (files == null) {
            Toast.makeText(FileBrowseActivity.this,
                    "当前路径不可访问或该路径下没有文件哦", Toast.LENGTH_LONG)
                    .show();
        } else {

            for (int i = 0; i < files.length; i++) {
                Map<String, Object> listItem = new HashMap<>();
                if (files[i].isDirectory()) {
                    listItem.put("icon", R.drawable.folder);
                } else {
                    listItem.put("icon", R.drawable.file);
                }
                listItem.put("fileName", files[i].getName());
                listItems.add(listItem);
            }

            SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems,
                    R.layout.fileitem,
                    new String[]{"icon", "fileName"},
                    new int[]{R.id.fileIcon, R.id.file_name});

            listView.setAdapter(simpleAdapter);

            try {
                textView.setText("当前路径为：" + currentParent.getCanonicalPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

