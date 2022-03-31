package com.he.univeersityflags;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.icu.util.Calendar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    static Calendar c = Calendar.getInstance();
    //校徽对应的 ImageView
    private ImageView flagImageView;
    private TextView flagTxt;
    //上一页和下一页
    private ImageButton backImageBtn;
    private ImageButton forwardImageBtn;
    //校徽数组（图片及文字）
    private int[] flag = {R.drawable.tsinghua_200, R.drawable.pku_200, R.drawable.ytu_200};
    private String[] universityNames = {"清华大学", "北京大学","烟台大学" };
    //当前页 默认第一页
    private int currentPage = 0;
    //我的大学相关的按钮及显示结果的文本框
    private Button chooseMyUniBtn;
    private TextView chooseMyUniTxt;
    //用于保存选中的我的大学
    private String myUniversity;
    //喜欢的大学相关的按钮及显示结果的文本框
    private Button likeUniBtn;
    private TextView likeUniTxt;
    //用于保存选中的我喜欢的大学
    private boolean[] likeUniversitys = {false, false, false};
    //调整 Logo 大小的开关
    private Switch logoSizeSwitch;
    //为操作学校 Logo 的大小，需要定义对应ImageView 组件
    private ImageView universityImageView;
    //决定是否显示日期时间的 ToggleButton
    private ToggleButton dateTimeToggleBnt;
    //要控制的两个布局
    private LinearLayout dateLayout, timeLayout;
    //与选择日期相关的按钮及显示结果的文本框
    private Button dateBtn;
    private TextView dateTxt;
    //与选择时间相关的按钮及显示结果的文本框
    private Button timeBtn;
    private TextView timeTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化图片、校徽名称组件
        flagImageView = (ImageView) findViewById(R.id.universityImageView);
        flagTxt = (TextView) findViewById(R.id.universityTxt);
        //初始化上一页、下一页按钮组件并注册监听器
        backImageBtn = (ImageButton) findViewById(R.id.backImageBtn);
        forwardImageBtn = (ImageButton) findViewById(R.id.forwardImageBtn);
        backImageBtn.setOnClickListener(onClickListener);
        forwardImageBtn.setOnClickListener(onClickListener);
        //初始化显示"我的大学"的按钮并注册监听器
        chooseMyUniBtn = (Button) findViewById(R.id.chooseUniBtn);
        chooseMyUniTxt = (TextView) findViewById(R.id.chooseUniTxt);
        chooseMyUniBtn.setOnClickListener(onClickListener);
        //初始化显示"我喜欢的大学"的按钮并注册监听器
        likeUniBtn = (Button)findViewById(R.id.likeUniBtn);
        likeUniTxt = (TextView)findViewById(R.id.likeUniTxt);
        likeUniBtn.setOnClickListener(onClickListener);
        //初始化切换 Logo 大小的开关组件并注册监听器
        logoSizeSwitch = (Switch) findViewById(R.id.logoSizeSwitch);
        universityImageView = (ImageView)findViewById(R.id.universityImageView);
        logoSizeSwitch.setOnCheckedChangeListener(onCompoundButtonOnCheckedChange);
        //初始化是否显示日期时间的 ToggleButton
        dateTimeToggleBnt = (ToggleButton)findViewById(R.id.dateTimeToggleBnt);
        //初始化要控制的两个布局
        dateLayout = (LinearLayout) findViewById(R.id.dateLayout);
        timeLayout = (LinearLayout) findViewById(R.id.timeLayout);
        //注册 ToggleButton 开关的事件监听器
        dateTimeToggleBnt.setOnCheckedChangeListener(onCompoundButtonOnCheckedChange);
        //初始化显示"选择日期"的按钮并注册监听器
        dateBtn = (Button) findViewById(R.id.dateBtn);
        dateTxt = (TextView) findViewById(R.id.dateTxt);
        dateBtn.setOnClickListener(onClickListener);
        //初始化显示"选择时间"的按钮并注册监听器
        timeBtn = (Button) findViewById(R.id.timeBtn);
        timeTxt = (TextView) findViewById(R.id.timeTxt);
        timeBtn.setOnClickListener(onClickListener);
    }
    //定义处理上一页、下一页按钮等点击事件的逻辑
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.backImageBtn: //处理“上一页”
       /*             if (currentPage == 0) {
                        //Toast.makeText(MainActivity.this, "第一页，前面没有了", Toast.LENGTH_SHORT).show();
                        //改用普通对话框提示，定义对话框对象
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage("第一页，前面没有了");
                        builder.setTitle("提示");
                        //定义确认按钮的属性及单击后事件的处理逻辑
                        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i){
                                dialog.dismiss();
                            }
                        });
                        //在第一轮改造程序并运行体验对话框后，在此处加入附后的代码继续体验
                        //定义取消按钮的属性及单击后事件的处理逻辑
                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            Toast.makeText(MainActivity.this, "两个钮的对话框", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    });
                        //定义查看祥情按钮的属性及单击后事件的处理逻辑
                        builder.setNeutralButton("查看详情", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                    // 处理逻辑
                        }
                    });
                        builder.create().show();
                        return;
                    }*/
                    if (currentPage == 0) {
                        //Toast.makeText(MainActivity.this, "第一页，前面没有了", Toast.LENGTH_SHORT).show();
                        //改用普通对话框提示，定义对话框对象
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage("第一页，前面没有了")
                                .setTitle("提示")
                                .setPositiveButton("确认", //定义确认按钮的属性及单击后事件的处理逻辑
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int i) {
                                                dialog.dismiss();
                                            }
                                        })
                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int i) {
                                        Toast.makeText(MainActivity.this, "两个钮的对话框", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                    }
                                })
                                .setNeutralButton("查看详情", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
// 处理逻辑
                                    }
                                })
                                .create()
                                .show();
                        return;
                    }
                    //上翻一页
                    currentPage--;
                    //设置校徽图片，在代码中设置和改变图片的方法
                    flagImageView.setImageResource(flag[currentPage]);
                    //设置学校名字
                    flagTxt.setText(universityNames[currentPage]);
                    break;
                case R.id.forwardImageBtn: //处理“下一页”
                    if (currentPage == (flag.length - 1)) {
                        Toast.makeText(MainActivity.this, "最后一页，后面没有了", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    //下翻一页
                    currentPage++;
                    //设置校徽图片
                    flagImageView.setImageResource(flag[currentPage]);
                    //设置学校名字
                    flagTxt.setText(universityNames[currentPage]);
                    break;
                case R.id.chooseUniBtn: //处理“我的大学”
//在下面体验内容型对话框
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    myUniversity = "";
                    builder.setTitle("选择我的大学")
                            .setIcon(R.drawable.university)
                            .setSingleChoiceItems(universityNames, //选项来自于universityNames 数组
                                    -1, //默认为 0 表示选中第一个项目, -1 代表无预选项目
                                    new DialogInterface.OnClickListener() {

                                        @Override
                                        public void onClick(DialogInterface dialog, int which){
                                            myUniversity = universityNames[which].toString();
                                        }
                                    })
                            .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (myUniversity == "") {
                                        chooseMyUniTxt.setText("选了个寂寞");
                                    } else {
                                        chooseMyUniTxt.setText(myUniversity);
                                        dialog.dismiss();
                                    }
                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    chooseMyUniTxt.setText("放弃了选择");
                                    dialog.dismiss();
                                }
                            })
                            .create()
                            .show();
                    break;
                case R.id.likeUniBtn: //处理“我喜欢的大学”
                //在下面体验内容型对话框（复选框）
                    AlertDialog.Builder builder2 = new AlertDialog.Builder(MainActivity.this);
                    builder2.setTitle("选择我喜欢的大学")
                            .setIcon(R.drawable.university)
                            .setMultiChoiceItems(universityNames, likeUniversitys, new DialogInterface.OnMultiChoiceClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i, boolean b)
                                {
                                    likeUniversitys[i] = b;
                                }
                            })
                            .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String univesitysChoosed ="";
                                    for (int i = 0; i < likeUniversitys.length; i++) {
                                        if (likeUniversitys[i] == true)
                                        {
                                            univesitysChoosed += (universityNames[i] + " ");
                                        }
                                    }
                                    if(univesitysChoosed =="")
                                        univesitysChoosed ="静待你有所喜欢";
                                    likeUniTxt.setText(univesitysChoosed);
                                }
                            })
                            .create()
                            .show();
                    break;
                default:
                    break;
                case R.id.dateBtn: //处理“选择日期”
                //在下面体验选择日期的对话框
                    DatePickerDialog datePicker = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                            //月份是从 0 开始，所以+1
                            String select_month = String.valueOf(month + 1);
                            String select_day = String.valueOf(day);
                            //月份、日小于 10，前面加一个0 保持2 位数显
                            if ((month + 1) < 10) {
                                select_month = "0" + (month + 1);
                            }
                            if (day < 10) {
                                select_day = "0" + day;
                            }
                            //把选择的日期填写到布局中EditText 中
                            dateTxt.setText(year + "年" + select_month + "月" + select_day + "日");
                        }
                    },
                            c.get(Calendar.YEAR),
                            c.get(Calendar.MONTH),
                            c.get(Calendar.DAY_OF_MONTH));
                    datePicker.show();
                    break;
                case R.id.timeBtn: //处理“选择时间”
                    //在下面体验选择日期的对话框
                    TimePickerDialog timePicker = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            String select_hour = String.valueOf(hourOfDay);
                            String select_minute = String.valueOf(minute);
                            if (hourOfDay < 10) {
                                select_hour = "0" + hourOfDay;
                            }
                            if (minute < 10) {
                                select_minute = "0" + minute;
                            }
                            timeTxt.setText(select_hour + ":" + select_minute);
                        }
                    },
                            c.get(Calendar.HOUR_OF_DAY),
                            c.get(Calendar.MINUTE), true);
                    timePicker.show();
                    break;
            }
        }
    };
    //定义复合按钮事件监听对象，设置处理逻辑
    private CompoundButton.OnCheckedChangeListener onCompoundButtonOnCheckedChange= new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
            //取出触发事件的组件的 ID
            int id = compoundButton.getId();
            switch (id){
                case R.id.logoSizeSwitch: //响应大小 Logo
                    if (checked) {
                        //设置大 Logo
                        //核心是设置校徽图片的宽和高
                        //创建一个 ViewGroup.LayoutParams 类的对象，获得图片的参数
                        ViewGroup.LayoutParams params = universityImageView.getLayoutParams();
                        //将参数对象的宽和高设置为大 Logo 的尺寸
                        params.width = (int) getResources().getDimension(R.dimen.bigLogoSize);

                        params.height = (int) getResources().getDimension(R.dimen.bigLogoSize);
                        //将修改后的参数设置给图片
                        universityImageView.setLayoutParams(params);
                    } else {
                        //设置小 Logo
                        ViewGroup.LayoutParams params = universityImageView.getLayoutParams();
                        params.width = (int) getResources().getDimension(R.dimen.smallLogoSize);
                        params.height = (int) getResources().getDimension(R.dimen.smallLogoSize);
                        universityImageView.setLayoutParams(params);
                    }
                    break;
                case R.id.dateTimeToggleBnt: //响应日期时间是否可见
                    if(checked){
                        dateLayout.setVisibility(View.VISIBLE);
                        timeLayout.setVisibility(View.VISIBLE);
//setVisibility()有三个参数：VISIBLE, INVISIBLE, or GONE//VISIBLE:0 意思是可见的
//INVISIBILITY:4 意思是不可见的，但还占着原来的空间//GONE:8 意思是不可见的，不占用原来的布局空间
                    }else{
                        dateLayout.setVisibility(View.GONE);
                        timeLayout.setVisibility(View.GONE);
                    }
                    break;
            }
        }
    };
}
