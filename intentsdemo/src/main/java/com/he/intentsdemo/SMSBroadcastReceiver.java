package com.he.intentsdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.util.Log;

public class SMSBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("提示", "收到了短信");
        //处理收到信息
        Object [] pdus = (Object[]) intent.getExtras().get("pdus");
        for (Object pdu:pdus){
            //取出一条短信
            SmsMessage smsMessage = SmsMessage.createFromPdu((byte[])pdu);
            String sender = smsMessage.getDisplayOriginatingAddress();  //发信人地址
            String content = smsMessage.getMessageBody();               //短信内容
            Log.d("提示", "来自：" + sender + "的信息，内容是：" + content);
        }
    }
}
