package com.he.week9_advanceui;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by lenovo on 2022/4/25.
 */

public class ListView1_Activity extends ListActivity {
    // 定义下拉列表需要显示的文本数组
    private String[] learningSiteArray = {"逸夫图书馆", "承先图书馆", "综合楼", "四教", "五教", "六教"};
    ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取系统默认的 ListView 组件
        listView = getListView();
        //设置列表视图的数据适配器：
        //	外观采用系统提供的布局  simple_list_item_1，数据来自自定义数组
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, learningSiteArray));

        //设置 ListView 事件监听
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(ListView1_Activity.this,
                        "您选择了" + learningSiteArray[position], Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

}
