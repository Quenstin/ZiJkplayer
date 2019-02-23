package com.example.zijkplayer.controller;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.example.zijkplayer.R;
import com.example.zijkplayer.util.PlayerUtils;
import com.example.zijkplayer.views.ErrorView;
import com.example.zijkplayer.voidplayer.BaseZIjkVideoView;
import com.example.zijkplayer.voidplayer.IjkVideoView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;

/**
 * 创建作者:zhuguoqing
 * 创建日期:2019/2/23
 * 作用: 控制器的基类
 */
public abstract class BaseController extends FrameLayout{
    protected View mControllerView;//控制器视图
    protected MediaPlayerControl mMediaPlayer;//播放器
    protected boolean mShowing;//控制器是否处于显示状态
    protected boolean mIsLocked;
    protected int mDefaultTimeout = 4000;
    private StringBuilder mFormatBuilder;
    private Formatter mFormatter;
    protected int mCurrentPlayState;
    protected ErrorView mErrorView;


    public BaseController(@NonNull Context context) {
        this(context, null);
    }

    public BaseController(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public BaseController(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    protected void initView() {
        mControllerView = LayoutInflater.from(getContext()).inflate(getLayoutId(), this);
        mFormatBuilder = new StringBuilder();
        mFormatter = new Formatter(mFormatBuilder, Locale.getDefault());
        mErrorView = new ErrorView(getContext());
        setClickable(true);
        setFocusable(true);
    }

    /**
     * 设置控制器布局文件，子类必须实现
     */
    protected abstract int getLayoutId();

    /**
     * 显示
     */
    public void show() {
    }

    /**
     * 隐藏
     */
    public void hide() {
    }

    public void setPlayState(int playState) {
        mCurrentPlayState = playState;
        hideStatusView();
        switch (playState) {
            case IjkVideoView.STATE_ERROR:
                mErrorView.setMessage(getResources().getString(R.string.error_message));
                mErrorView.setButtonTextAndAction(getResources().getString(R.string.zplayer_retry), new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        hideStatusView();
                        mMediaPlayer.retry();
                    }
                });
                this.addView(mErrorView, 0);
                break;
        }
    }

    public void showStatusView() {
        this.removeView(mErrorView);
        mErrorView.setMessage(getResources().getString(R.string.wifi_tip));
        mErrorView.setButtonTextAndAction(getResources().getString(R.string.continue_play), new OnClickListener() {
            @Override
            public void onClick(View v) {
                hideStatusView();
                BaseZIjkVideoView.IS_PLAY_ON_MOBILE_NETWORK = true;
                mMediaPlayer.start();
            }
        });
        this.addView(mErrorView);
    }

    public void hideStatusView() {
        this.removeView(mErrorView);
    }

    public void setPlayerState(int playerState) {
    }

    protected void doPauseResume() {
        if (mCurrentPlayState == IjkVideoView.STATE_BUFFERING) return;
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
        } else {
            mMediaPlayer.start();
        }
    }

    /**
     * 横竖屏切换
     */
    protected void doStartStopFullScreen() {
        Activity activity = PlayerUtils.scanForActivity(getContext());
        if (activity == null) return;
        if (mMediaPlayer.isFullScreen()) {
            mMediaPlayer.stopFullScreen();
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            mMediaPlayer.startFullScreen();
        }
    }


    protected Runnable mShowProgress = new Runnable() {
        @Override
        public void run() {
            int pos = setProgress();
            if (mMediaPlayer.isPlaying()) {
                postDelayed(mShowProgress, 1000 - (pos % 1000));
            }
        }
    };

    protected final Runnable mFadeOut = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };

    protected int setProgress() {
        return 0;
    }

    /**
     * 获取当前系统时间
     */
    protected String getCurrentSystemTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        Date date = new Date();
        return simpleDateFormat.format(date);
    }



    protected String stringForTime(int timeMs) {
        int totalSeconds = timeMs / 1000;

        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hours = totalSeconds / 3600;

        mFormatBuilder.setLength(0);
        if (hours > 0) {
            return mFormatter.format("%d:%02d:%02d", hours, minutes, seconds).toString();
        } else {
            return mFormatter.format("%02d:%02d", minutes, seconds).toString();
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        post(mShowProgress);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(mShowProgress);
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        if (visibility == VISIBLE) {
            post(mShowProgress);
        }
    }

    /**
     * 改变返回键逻辑，用于activity
     */
    public boolean onBackPressed() {
        return false;
    }

    public void setMediaPlayer(MediaPlayerControl mediaPlayer) {
        this.mMediaPlayer = mediaPlayer;
    }
}
