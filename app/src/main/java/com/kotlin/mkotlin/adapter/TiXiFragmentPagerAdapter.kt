package com.kotlin.mkotlin.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.blankj.utilcode.util.ToastUtils
import com.kotlin.mkotlin.base.BaseFragment

/**
 * 创建作者:zhuguoqing
 * 创建日期:2019/3/11
 * 作用:
 */
class TiXiFragmentPagerAdapter(framentm: FragmentManager, private val fragmens: ArrayList<BaseFragment>) : FragmentPagerAdapter(framentm) {
    private val titleTabs = arrayOf("聊天室", "排行榜")

    override fun getItem(position: Int): Fragment {
        return fragmens[position]
    }

    override fun getCount(): Int {
        return fragmens.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        ToastUtils.showShort(""+titleTabs[position])
        return titleTabs[position]
    }

}