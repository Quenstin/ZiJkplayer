package com.example.zijkplayer.voidcontroller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * 创建作者:zhuguoqing
 * 创建日期:2019/2/24
 * 作用: 用来获取电量
 */
public class BatteryMsReceiver extends BroadcastReceiver {

    private ImageView mImageView;

    public BatteryMsReceiver(ImageView pow) {
        this.mImageView = pow;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras == null) return;
        int current = extras.getInt("level");// 获得当前电量
        int total = extras.getInt("scale");// 获得总电量
        int percent = current * 100 / total;
        mImageView.getDrawable().setLevel(percent);
    }
}
