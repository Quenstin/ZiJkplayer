package com.example.zijkplayer.listener;

/**
 * 创建作者:zhuguoqing
 * 创建日期:2019/2/23
 * 作用: 播放监听
 */

public interface OnVideoViewStateChangeListener {
    void onPlayerStateChanged(int playerState);
    void onPlayStateChanged(int playState);
}
