package com.example.zijkplayer.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zijkplayer.R;

/**
 * 创建作者:zhuguoqing
 * 创建日期:2019/2/23
 * 作用:  错误提示
 */
public class ErrorView extends LinearLayout{

    private TextView tvMessage;
    private TextView btnAction;
    private float downX;
    private float downY;
    public ErrorView(Context context) {
        this(context,null);
    }

    public ErrorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    private void init(){
        View rootView= LayoutInflater.from(getContext()).inflate(R.layout.zijkplayer_error,this);
        tvMessage = rootView.findViewById(R.id.message);
        btnAction = rootView.findViewById(R.id.status_btn);
        this.setBackgroundResource(android.R.color.black);
        setClickable(true);
    }

    public void setMessage(String msg) {
        if (tvMessage != null) tvMessage.setText(msg);
    }

    public void setButtonTextAndAction(String text, OnClickListener listener) {
        if (btnAction != null) {
            btnAction.setText(text);
            btnAction.setOnClickListener(listener);
        }
    }

    /**
     * 处理点击事件
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = ev.getX();
                downY = ev.getY();
                // True if the child does not want the parent to intercept touch events.
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                float absDeltaX = Math.abs(ev.getX() - downX);
                float absDeltaY = Math.abs(ev.getY() - downY);
                if (absDeltaX > ViewConfiguration.get(getContext()).getScaledTouchSlop() ||
                        absDeltaY > ViewConfiguration.get(getContext()).getScaledTouchSlop()) {
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
