package com.he.week11_service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomNumService extends Service {
    public RandomNumService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    public class MyBinder extends Binder {
        public RandomNumService getService() {    //创建获取 Service 的方法
            return RandomNumService.this;
        }
    }

    public List getRandomNumber() {
        List resArr = new ArrayList();
        String strNumber = "";
        for (int i = 0; i < 7; i++) {
            int number = new Random().nextInt(33) + 1;
            if (number < 10) {    //在数字 1~9 前加 0
                strNumber = "0" + String.valueOf(number);
            } else {
                strNumber = String.valueOf(number);
            }
            resArr.add(strNumber);
        }
        return resArr;    //将数组返回
    }

    @Override
    public void onDestroy() {    //销毁该 Service
        super.onDestroy();
    }
}
