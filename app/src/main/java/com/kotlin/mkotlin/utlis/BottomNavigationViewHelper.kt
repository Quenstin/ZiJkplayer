package com.kotlin.mkotlin.utlis

import android.annotation.SuppressLint
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView

/**
 * 创建作者:zhuguoqing
 * 创建日期:2019/1/5
 * 作用:
 */
object BottomNavigationViewHelper {

    @SuppressLint("RestrictedApi")
    fun disableShiftMode(view: BottomNavigationView) {

        val menuView = view.getChildAt(0) as BottomNavigationMenuView

        try {
            val shiftingMode = menuView.javaClass.getDeclaredField("mShiftingMode")
            shiftingMode.isAccessible = true
            shiftingMode.setBoolean(menuView, false)
            shiftingMode.isAccessible = false
            for (i in 0 until menuView.getChildCount()) {
                val item = menuView.getChildAt(i) as BottomNavigationItemView

                item.setShifting(false)
                // set once again checked value, so view will be updated

                item.setChecked(item.getItemData().isChecked())
            }
        } catch (e: NoSuchFieldException) {
        } catch (e: IllegalAccessException) {
        }

    }

}
