package com.example.zijkplayer.listener;

/**
 * 创建作者:zhuguoqing
 * 创建日期:2019/2/23
 * 作用:
 */

public interface PlayerEventListener {

    void onError();

    void onCompletion();

    void onInfo(int what, int extra);

    void onPrepared();

    void onVideoSizeChanged(int width, int height);

}
