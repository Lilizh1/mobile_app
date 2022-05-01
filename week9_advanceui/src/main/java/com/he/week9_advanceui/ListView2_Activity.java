package com.he.week9_advanceui;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListView2_Activity extends ListActivity {
    // 定义下拉列表需要显示的文本数组
    private String[] learningSiteArray = {"逸夫图书馆", "承先图书馆", "综合楼", "四教", "五教", "六教"};
    //为方便后面可以进行”添加“等动态操作，不能用静态数组做数据源，而要用动态的 ArrayList  表示数据
    //拓展建议：是否可以将数据源改为 SharedPreferences,甚至数据库？可以自己做一下
    ArrayList arrayList = new ArrayList();
    //定义 ListView 对象
    ListView listView = null;
    //定义用于”添加“的编辑框对象
    EditText addEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view2_);
        //请自行拓展，尝试从文件、SharedPreferences、数据库中动态获得数据
        for (int i = 0; i < learningSiteArray.length; i++) {
            arrayList.add(learningSiteArray[i]);
        }
        listView = getListView();
        final ArrayAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, arrayList);
        setListAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()

        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(ListView2_Activity.this,
                        "您选择了" + learningSiteArray[position], Toast.LENGTH_LONG)
                        .show();
            }
        });
        //初始化组件
        addEdt = (EditText) findViewById(R.id.addListView2Edt);
        //设置”添加“按钮的事件监听
        findViewById(R.id.addListView2Btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayList.add(addEdt.getText().toString());
                adapter.notifyDataSetInvalidated();
            }
        });
    }
}

