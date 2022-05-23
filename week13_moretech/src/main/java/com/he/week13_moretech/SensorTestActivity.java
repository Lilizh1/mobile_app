package com.he.week13_moretech;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

public class SensorTestActivity extends AppCompatActivity implements SensorEventListener {

    EditText gravityEdt, lightEdt;

    private SensorManager sensorManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_test);

        gravityEdt = (EditText) findViewById(R.id.gravityEdt);
        lightEdt = (EditText) findViewById(R.id.lightEdt);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    }
    @Override
    protected void onResume() {
        super.onResume();

        sensorManager.registerListener( this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_GAME);

        sensorManager.registerListener( this,
                sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT),
                SensorManager.SENSOR_DELAY_GAME);
    }
    //重写 Activity 的 onPause 方法，在该方法中注销传感器事件
    //在页面推失去焦点时取消注册的监听器，使之不再起作用（否则就是捣乱了）
    @Override
    protected void onPause() {
        sensorManager.unregisterListener( this);
        super.onPause();
    }

    @Override
    protected void onStop() {
        sensorManager.unregisterListener(this);
        super.onStop();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] values = event.values; //获取 X、Y、Z 三轴的输出信息
        int sensorType = event.sensor.getType(); //获取传感器类型
        StringBuilder stringBuilder = null;
        switch (sensorType) {
            case Sensor.TYPE_ACCELEROMETER:
                stringBuilder = new StringBuilder();
                stringBuilder.append("X 轴横向重力值:");
                stringBuilder.append(values[0]);
                stringBuilder.append("\nY 轴纵向重力值:");
                stringBuilder.append(values[1]);
                stringBuilder.append("\nZ 轴向上重力值:");
                stringBuilder.append(values[2]);
                gravityEdt.setText(stringBuilder.toString());
                break;
            case Sensor.TYPE_LIGHT:
                stringBuilder = new StringBuilder();
                stringBuilder.append("光的强度值:");
                stringBuilder.append(values[0]);
                lightEdt.setText(stringBuilder.toString());
                break;
        }
    }
    //这个方法也是实现 SensorEventListener 接口时必须实现的方法
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}