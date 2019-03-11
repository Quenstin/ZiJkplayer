package com.kotlin.mkotlin.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import com.kotlin.mkotlin.base.BaseFragment

/**
 * 创建作者:zhuguoqing
 * 创建日期:2019/1/5
 * 作用: ArrayList<Fragment> fragments, String[] titleTabs
 */
class MainViewPagerAdapter(framentm:FragmentManager) : FragmentStatePagerAdapter(framentm) {

    private lateinit var fragments:List<Fragment>
    private lateinit var titles:List<String>
    fun setFragments(fragments: List<Fragment>) {
        this.fragments = fragments
    }

    fun setFragmentsAndTitles(fragments: List<Fragment>, titles: List<String>) {
        this.fragments = fragments
        this.titles = titles
    }

    override fun getItem(p0: Int): Fragment {
        return fragments[p0]

    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }
    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }
}