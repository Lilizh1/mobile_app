package com.he.week10_advanceui2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class GridViewActivity extends AppCompatActivity {

    //用于填充 GridView 的图片
    private int[] imageIds = {R.drawable.img_1, R.drawable.img_2,
            R.drawable.img_3, R.drawable.img_4, R.drawable.img_5,
            R.drawable.img_6, R.drawable.img_7, R.drawable.img_8,
            R.drawable.img_9};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);
        //定义 GridView 组件并初始化
        GridView gridView = (GridView) findViewById(R.id.gridview);
        //定义适配器
        ImageAdapter imageAdapter = new ImageAdapter(this, imageIds);
        //为 GridView 设置适配器
        gridView.setAdapter(imageAdapter);

        //定义单击 GridView  中元素时用于响应的事件监听器：转到相应的应用中去
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                switch (position) {
                    case 1: //备忘录（Menu 版）
                        intent.setClass(GridViewActivity.this, TextBookActivity.class);
                        startActivity(intent);
                        break;
                    case 4: //备忘录（ContextMenu 版）
                        intent.setClass(GridViewActivity.this, TextBookContextMenuActivity.class);
                        startActivity(intent);
                        break;
                    case 6: //校园一点事（TabHost）
                        intent.setClass(GridViewActivity.this, TabHostActivity.class);
                        startActivity(intent);
                        break;
                    case 8: //新闻系统（fragment）
                        intent.setClass(GridViewActivity.this, NewsActivity.class);//NewsActivity
                        startActivity(intent);
                        break;
                }
            }
        });
    }
}

