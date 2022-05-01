package com.he.week9_advanceui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class SpinnerActivity extends AppCompatActivity {
    //定义需要显示的文本数组
    private String[] learningSiteArray = {"逸夫图书馆", "承先图书馆", "综合楼", "四教", "五教", "六教"};
    //定义文本数组
    private String[] playSiteArray;
    //定义文本框组件
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);
        //初始化组件
        resultText = (TextView) findViewById(com.he.week9_advanceui.R.id.sp_result);
        //初始化下拉模式的列表框
        intiDropdownSpinner();
        //初始化对话框样式的列表框
        intiDialogSpinner();
    }

    //初始化下拉模式的列表框
    private void intiDropdownSpinner() {
        //初始化：从布局文件中获取名叫 sp_dropdown 的下拉框
        Spinner sp_dropdown = (Spinner) findViewById(R.id.sp_dropdown);
        //设置下拉框的标题（对话框模式才显示标题，下拉模式不显示标题）
        sp_dropdown.setPrompt("请选择学习场所");
        //  先声明一个下拉列表的数组适配器
        ArrayAdapter<String> learningSiteAdapter = new ArrayAdapter<String>(this,
                R.layout.item_select, learningSiteArray);

        sp_dropdown.setAdapter(learningSiteAdapter); //  设置下拉框的数组适配器

        sp_dropdown.setSelection(0); //  设置下拉框默认显示第一项

        //  给下拉框设置选择监听器，一旦用户选中某一项，就触发监听器的 onItemSelected 方法
        sp_dropdown.setOnItemSelectedListener(onItemSelectedListener);
    }

    //初始化对话框样式的列表框
    private void intiDialogSpinner() {
        //从数组资源文件 arrays.xml 中获得数组元素
        //请结合前面对字符资源文件、尺寸资源文件的体验，说出这样做的好处
        playSiteArray = getResources().getStringArray(R.array.palySites);

        // 从布局文件中获取名叫 sp_dialog 的下拉框
        Spinner sp_dialog = (Spinner) findViewById(R.id.sp_dialog);
        //设置下拉框的标题。对话框模式才显示标题，下拉模式不显示标题
        sp_dialog.setPrompt("请选择运动场所");

        // 声明一个下拉列表的数组适配器
        ArrayAdapter<String> palySiteAdapter = new ArrayAdapter<String>(this,
                R.layout.item_select, playSiteArray);

        sp_dialog.setAdapter(palySiteAdapter); //  设置下拉框的数组适配器

        // 给下拉框设置选择监听器
        sp_dialog.setOnItemSelectedListener(onItemSelectedListener);
    }
    // 定义一个选择监听器
    private AdapterView.OnItemSelectedListener onItemSelectedListener =
            new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                    switch (adapterView.getId()) {
                        case R.id.sp_dropdown:
                            resultText.setText("学习在" + learningSiteArray[position]);
                            break;
                        case R.id.sp_dialog:
                            resultText.setText("运动在" + playSiteArray[position]);
                            break;
                    }
                }

                // 未选择时的处理方法，通常无需关注
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            };
}
