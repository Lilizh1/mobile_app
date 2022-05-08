package com.he.week10_advanceui2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

public class TabHostActivity extends AppCompatActivity {
    //定义 TabHost 对象
    TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_host);

        tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();
        TabHost.TabSpec spec1, spec2, spec3;
        spec1 = tabHost.newTabSpec("tab1").
                setIndicator("", getResources().getDrawable(R.drawable.learningsite)).
                setContent(R.id.content_1);
        tabHost.addTab(spec1);
        setTab1Business();
        spec2 = tabHost.newTabSpec("tab2").
                setIndicator("", getResources().getDrawable(R.drawable.campus)).
                setContent(R.id.content_2);
        tabHost.addTab(spec2);

        spec3 = tabHost.newTabSpec("tab3").
                setIndicator("要点提示", getResources().getDrawable(R.drawable.keypoint)).setContent(R.id.content_3);
        tabHost.addTab(spec3);

        tabHost.setOnTabChangedListener(onTabChangedListener);
    }

    private void setTab1Business() {
        final String[] learningSiteArray = {"逸夫图书馆", "承先图书馆", "综合楼", "四教", "五教", "六教"};
        ListView listView1;
        listView1 = (ListView) findViewById(R.id.listView1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, learningSiteArray);
        listView1.setAdapter(adapter);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(TabHostActivity.this,
                        "您选择了" + learningSiteArray[position], Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

    private TabHost.OnTabChangeListener onTabChangedListener
            = new TabHost.OnTabChangeListener() {
        @Override
        public void onTabChanged(String tabId) {
            if (tabId.equals("tab1")) {
                Toast.makeText(TabHostActivity.this,
                        "选择了学习场所,本提示可以换为需要的业务处理",
                        Toast.LENGTH_SHORT)
                        .show();
            }
            if (tabId.equals("tab2")) {    //第二个标签
                Toast.makeText(TabHostActivity.this,
                        "选择了校园风光,本提示可以换为需要的业务处理",
                        Toast.LENGTH_SHORT)
                        .show();
            }
            if (tabId.equals("tab2")) {    //第三个标签
                Toast.makeText(TabHostActivity.this,
                        "选择了要点提示,本提示可以换为需要的业务处理",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        }

    };
}
