package com.he.week9_advanceui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;

public class ListView4_Activity extends AppCompatActivity {
    //要展示的文字
    private String[] texts = new String[]{"钟楼", "桥", "南门", "东门", "飞腾", "三元湖"};
    //要展示的图片
    private Integer[] images = new Integer[]{R.mipmap.belltower, R.mipmap.bridge,
            R.mipmap.southgate, R.mipmap.eastgate, R.mipmap.flyball, R.mipmap.lake
    };
    //定义 ListView 组件
    ListView mListView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view4_);
        //设置 ListView 作为显示
        mListView = (ListView) findViewById(R.id.listview4);

        TextImageAdapter adapter = new TextImageAdapter(this, Arrays.asList(texts),
                Arrays.asList(images));

        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(ListView4_Activity.this,
                        "您选择了" + texts[position], Toast.LENGTH_LONG).show();
            }
        });

    }
}
