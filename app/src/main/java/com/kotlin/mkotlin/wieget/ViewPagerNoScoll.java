package com.kotlin.mkotlin.wieget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 创建作者:zhuguoqing
 * 创建日期:2019/2/24
 * 作用:
 */
public class ViewPagerNoScoll extends ViewPager {
    private boolean isScoll=true;

    public ViewPagerNoScoll(@NonNull Context context) {
        super(context);
    }

    public ViewPagerNoScoll(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 是否需要viewpager左右滑动效果
     * @param isCanScroll  false  否    true  是
     */
    public void setScanScroll(boolean isCanScroll) {
        this.isScoll = isCanScroll;
    }

    /**
     * 根据view事件分发机制来处理
     * @param ev
     * @return
     */

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return isScoll && super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return isScoll && super.onTouchEvent(ev);

    }

}
