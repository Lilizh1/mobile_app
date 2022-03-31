package com.he.eventcallback;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    int sharpe = 0; //要画的形状，0 为圆，1 为框，2 为小兔兔
    int colorIndex = 0; //标识画图背景颜色
    int[] colors = {Color.WHITE, Color.CYAN, Color.YELLOW};
    DrawView drawView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        drawView = new DrawView(this); // 初始化自定义的View
        setContentView(drawView);
    }
    //重写 onKeyDown 方法
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
//改变形状
                sharpe = (sharpe + 1) % 3;
                break;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
//改变背景色
                colorIndex = (colorIndex + 1) % 3;
                break;
        }
        drawView.postInvalidate(); //刷新视图
        return super.onKeyDown(keyCode, event);
    }
    // ②重写的 onTouchEvent 回调方法
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN://手指按下
                drawView.x = (int) event.getX(); // 改变 x 坐标
                drawView.y = (int) event.getY() - 100; // 改变y 坐标break;
            case MotionEvent.ACTION_MOVE: //手指移动
                drawView.x = (int) event.getX(); // 改变x 坐标
                drawView.y = (int) event.getY() - 100; // 改变y 坐标break;
            case MotionEvent.ACTION_UP://手指抬起
                drawView.x = -360; // 改变 x 坐标
                drawView.y = -360; // 改变 y 坐标
                break;
        }
        drawView.postInvalidate(); // 重绘
        return super.onTouchEvent(event);
    }
    //①自定义用于绘图的 View 类
    class DrawView extends View {
        Paint paint; // 画笔
        int x = -100; // x 坐标
        int y = -100; // y 坐标
        int width = 100; // 矩形的宽度

        public DrawView(MainActivity context) {
            super(context);
            paint = new Paint(); // 初始化画笔
            paint.setColor(Color.BLUE);
        }
        //重载绘制方法
        @Override
        protected void onDraw(Canvas canvas) {
            drawView.setBackgroundColor(colors[colorIndex]);
            switch (sharpe) {
                case 0: //绘制圆圈
                    canvas.drawCircle(x - width / 2, y - width / 2, width / 2, paint);
                    break;
                case 1: //绘制矩形
                    canvas.drawRect(x - width / 2, y - width / 2, x + width / 2, y + width / 2, paint);
                    break;
                case 2: //绘制小兔兔
                    Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),
                            R.mipmap.rabbit);
                    canvas.drawBitmap(bitmap, x, y, paint);
                    break;
            }
            super.onDraw(canvas);
        }
    }
}
