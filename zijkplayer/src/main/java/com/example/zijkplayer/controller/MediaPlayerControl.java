package com.example.zijkplayer.controller;

import android.graphics.Bitmap;

public interface MediaPlayerControl {

    //开始
    void start();
    //暂停
    void pause();
    //时长
    long getDuration();
    //获取当前位置
    long getCurrentPosition();
    //快进
    void seekTo(long pos);
    //是否播放
    boolean isPlaying();

    int getBufferedPercentage();

    void startFullScreen();

    void stopFullScreen();

    boolean isFullScreen();

    String getTitle();

    void setMute(boolean isMute);

    boolean isMute();

    void setLock(boolean isLocked);

    void setScreenScale(int screenScale);

    void retry();

    void setSpeed(float speed);

    long getTcpSpeed();

    void refresh();

    void setMirrorRotation(boolean enable);

    Bitmap doScreenShot();

    int[] getVideoSize();

    void setRotation(float rotation);
}