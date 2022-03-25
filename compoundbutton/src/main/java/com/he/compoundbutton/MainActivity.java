package com.he.compoundbutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    //定义显示结果的文本框
    private TextView textReslut;

    //定义单选按钮组
    private RadioGroup radioGroupId;

    //定义复选框按钮
    private CheckBox checkBoxKexing, checkBoxGuoyao, checkBoxKangxinuo;

    //定义开关组件
    private Switch switchMonitorCAll, switchRoomLeaderCall;

    //定义ToggleButton组件
    private ToggleButton toggleButtonWideScreen;

    //定义第1部分的布局对象
    private LinearLayout linearLayoutIDVacccine;

    //定义按钮对象
    private Button buttonShow, buttonClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化显示结果的文本框组件
        textReslut = (TextView) findViewById(R.id.textResult);

        //初始化按钮组对象并设置事件监听
        radioGroupId = (RadioGroup) findViewById(R.id.radioGroupId);
        radioGroupId.setOnCheckedChangeListener(radioGroupChangeListener);

        //初始化复选框按钮
        checkBoxKexing = (CheckBox) findViewById(R.id.kexing);
        checkBoxGuoyao = (CheckBox)findViewById(R.id.guoyao);
        checkBoxKangxinuo = (CheckBox)findViewById(R.id.kangxinuo);

        //设置复选框按钮的事件监听
        checkBoxKexing.setOnCheckedChangeListener(compoundButtonChangeListeners);
        checkBoxGuoyao.setOnCheckedChangeListener(compoundButtonChangeListeners);
        checkBoxKangxinuo.setOnCheckedChangeListener(compoundButtonChangeListeners);

        //初始化开关组件
        switchMonitorCAll = (Switch)findViewById(R.id.switchMonitorCall);
        switchRoomLeaderCall = (Switch)findViewById(R.id.switchRoomLeaderCall);

        //注册开关组件的事件监听
        switchMonitorCAll.setOnCheckedChangeListener(compoundButtonChangeListeners);
        switchRoomLeaderCall.setOnCheckedChangeListener(compoundButtonChangeListeners);

        //初始化toggleButton组件
        toggleButtonWideScreen = (ToggleButton)findViewById(R.id.toggleButtonWideScreen);

        //注册toggleButton组件的事件监听
        toggleButtonWideScreen.setOnCheckedChangeListener(compoundButtonChangeListeners);

        //初始化要操作的第一部分的布局对象
        linearLayoutIDVacccine = (LinearLayout)findViewById(R.id.linearLayoutIDVaccine);

        //初始化按钮对象
        buttonShow = (Button)findViewById(R.id.buttonShow);
        buttonClear = (Button)findViewById(R.id.buttonClear);

        //注册按钮组件的事件监听
        buttonShow.setOnClickListener(buttonOnClickListener);
        buttonClear.setOnClickListener(buttonOnClickListener);


    }


    //定义单选按钮改变值的监听事件
    private RadioGroup.OnCheckedChangeListener radioGroupChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {

            //用getCheckedRadioButtonId()获得按钮组中改变的按钮
            int id = radioGroup.getCheckedRadioButtonId();
            //按下不同的按钮，在信息区显示不同的信息
            switch (id) {
                case R.id.radioTeacher:
                    textReslut.setText(getResources().getText(R.string.display_teacher));
                    break;
                case R.id.radioStudent:
                    textReslut.setText(getResources().getText(R.string.display_student));
                    break;
                default:
                    textReslut.setText(getResources().getText(R.string.display_identity));
                    break;
            }
        }
    };

    //定义复选框的监听事件对象
    private CompoundButton.OnCheckedChangeListener compoundButtonChangeListeners
            = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            //获得改变后的复选框
            int id = compoundButton.getId();
            switch (id) {
                case R.id.kexing:
                    if (b) {
                        textReslut.setText(getResources().getText(R.string.Y_kexing));
                    } else {
                        textReslut.setText(getResources().getText(R.string.N_kexing));
                    }
                    break;
                case R.id.guoyao:
                    if (b) {
                        textReslut.setText(getResources().getText(R.string.Y_guoyao));
                    } else {
                        textReslut.setText(getResources().getText(R.string.N_kexing));
                    }
                    break;
                case R.id.kangxinuo:
                    if (b) {
                        textReslut.setText(getResources().getText(R.string.Y_kangxinuo));
                    } else {
                        textReslut.setText(getResources().getText(R.string.N_kangxinuo));
                    }
                    break;
                case R.id.switchMonitorCall:
                    if (b) {
                        textReslut.setText(getResources().getText(R.string.monitor));
                    } else {
                        textReslut.setText(getResources().getText(R.string.N_monitor));
                    }
                    break;
                case R.id.switchRoomLeaderCall:
                    if (b) {
                        textReslut.setText(getResources().getText(R.string.housemates));
                    } else {
                        textReslut.setText(getResources().getText(R.string.N_housemates));
                    }
                    break;
                case R.id.toggleButtonWideScreen:
                    if (b) {
                        textReslut.setText(getResources().getText(R.string.narrow));
                        linearLayoutIDVacccine.setOrientation(LinearLayout.VERTICAL);
                        linearLayoutIDVacccine.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
                    } else {
                        textReslut.setText(getResources().getText(R.string.wide));
                        linearLayoutIDVacccine.setOrientation(LinearLayout.HORIZONTAL);
                        linearLayoutIDVacccine.setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
                    }
                    break;
            }
        }
    };

//    //定义开关组件的监听事件对象
//    private CompoundButton.OnCheckedChangeListener compoundButtonChangeListeners
//            = new CompoundButton.OnCheckedChangeListener() {
//        @Override
//        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//
//            //获得改变后的开关组件
//            int id = compoundButton.getId();
//            switch (id) {
//                case R.id.switchMonitorCall:
//                    if (b) {
//                        textReslut.setText("忘记了上网课时间让班长叫");
//                    } else {
//                        textReslut.setText("忘记了上网课时间不让班长叫");
//                    }
//                    break;
//                case R.id.switchRoomLeaderCall:
//                    if (b) {
//                        textReslut.setText("忘记了上网课时间让舍长叫");
//                    } else {
//                        textReslut.setText("忘记了上网课时间不让舍长叫");
//                    }
//                    break;
//                case R.id.toggleButtonWideScreen:
//                    if (b) {
//                        textReslut.setText("窄屏设置");
//                        linearLayoutIDVacccine.setOrientation(LinearLayout.VERTICAL);
//                        linearLayoutIDVacccine.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
//                    } else {
//                        textReslut.setText("宽屏设置");
//                        linearLayoutIDVacccine.setOrientation(LinearLayout.HORIZONTAL);
//                        linearLayoutIDVacccine.setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
//                    }
//                    break;
//
//            }
//        }
//    };
    //响应按钮事件的代码
    private View.OnClickListener buttonOnClickListener
            =new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int id = view.getId();
            switch (id){
                case R.id.buttonShow:
                    String str = (String) getResources().getText(R.string.selection);
                    String str1 = (String) getResources().getText(R.string.first);
                    String str2 = (String) getResources().getText(R.string.second);
                    String str3 = (String) getResources().getText(R.string.third);
                    String str4 = (String) getResources().getText(R.string.fourth);
                    String narrow = (String) getResources().getText(R.string.narrow);
                    String wide = (String) getResources().getText(R.string.wide);
                    String teacher = (String) getResources().getText(R.string.teacher);
                    String student = (String) getResources().getText(R.string.student);
                    String other = (String) getResources().getText(R.string.other);
                    String kexing = (String) getResources().getText(R.string.kexing);
                    String guoyao = (String) getResources().getText(R.string.guoyao);
                    String kangxinuo = (String) getResources().getText(R.string.kangxinuo);
                    String tip1 = (String) getResources().getText(R.string.tip1);
                    String tip2 = (String) getResources().getText(R.string.tip2);
                    String tip3 = (String) getResources().getText(R.string.tip3);
                    String monitor = (String) getResources().getText(R.string.monitor);
                    String head = (String) getResources().getText(R.string.dormitory_n);



                    str += "\n"+ str1;
                    switch(radioGroupId.getCheckedRadioButtonId()){
                        case R.id.radioTeacher:
                            str += teacher;
                            break;
                        case R.id.radioStudent:
                            str += student;
                            break;
                        case R.id.radioOthers:
                            str += other;
                            break;
                        default:
                            str += tip1;
                            break;
                    }

                    str += "\n" + str2;
                    String strTemp = new String("");
                    if(checkBoxGuoyao.isChecked())
                        strTemp += guoyao;
                    if(checkBoxKexing.isChecked())
                        strTemp += kexing;
                    if(checkBoxKangxinuo.isChecked())
                        strTemp += kangxinuo;
                    if(strTemp.isEmpty())
                        strTemp += tip2;
                    str += (strTemp + "\n");

                    str = str + str3;
                    strTemp = new String("");
                    if(switchMonitorCAll.isChecked())
                        strTemp += monitor;
                    if(switchRoomLeaderCall.isChecked())
                        strTemp += head;
                    if(strTemp.isEmpty())
                        strTemp += tip3;
                    str += (strTemp + "\n");

                    str = str + str4;
                    if(toggleButtonWideScreen.isChecked())
                        str += narrow;
                    else
                        str += wide;

                    textReslut.setText(str);
                    textReslut.setTextColor(getResources().getColor(R.color.text_color));
                    //textReslut.setTextSize(getResources().getDimension(R.dimen.dp2));
                    //textReslut.setBackground(getResources().getDrawable(R.drawable.ytu_bg));

                    break;
                case R.id.buttonClear:
                    radioGroupId.check(-1);
                    checkBoxGuoyao.setChecked(false);
                    checkBoxKexing.setChecked(false);
                    checkBoxKangxinuo.setChecked(false);
                    switchMonitorCAll.setChecked(false);
                    switchRoomLeaderCall.setChecked(false);
                    toggleButtonWideScreen.setChecked(false);
                    textReslut.setText(getResources().getText(R.string.no));
                    break;
            }
        }
    };

}
