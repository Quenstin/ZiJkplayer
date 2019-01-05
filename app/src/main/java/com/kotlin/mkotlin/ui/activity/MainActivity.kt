package com.kotlin.mkotlin.ui.activity

import android.support.design.widget.BottomNavigationView
import android.support.design.widget.NavigationView
import android.support.v4.view.ViewPager
import android.view.MenuItem
import com.kotlin.mkotlin.R
import com.kotlin.mkotlin.adapter.MainViewPagerAdapter
import com.kotlin.mkotlin.base.BaseActivity
import com.kotlin.mkotlin.base.BaseFragment
import com.kotlin.mkotlin.ui.fragment.DaoHangFragment
import com.kotlin.mkotlin.ui.fragment.HomeFragment
import com.kotlin.mkotlin.ui.fragment.ProjectFragment
import com.kotlin.mkotlin.ui.fragment.TiXiFragment
import com.kotlin.mkotlin.utlis.BottomNavigationViewHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private lateinit var menuItem: MenuItem
    override fun initData() {
    }

    override fun initView() {
        //初始化viewpager
        val fragments = arrayListOf<BaseFragment>().apply {
            add(HomeFragment.newInstance())
            add(ProjectFragment.newInstance())
            add(TiXiFragment.newInstance())
            add(DaoHangFragment.newInstance())
        }
        //初始化适配器
        val viewPagerAdapter = MainViewPagerAdapter(supportFragmentManager)
        viewPagerAdapter.setFragments(fragments)
        mainViewPager.adapter = viewPagerAdapter
        mainViewPager.offscreenPageLimit = fragments.size


        //初始化底部导航
        mBottomView.setOnNavigationItemSelectedListener { p0 ->
            menuItem = p0



            when (p0.itemId) {

                R.id.navigation_home -> mainViewPager.currentItem = 0
                R.id.navigation_dashboard -> mainViewPager.currentItem=1
                R.id.navigation_notifications -> mainViewPager.currentItem=2
                R.id.navigation_person -> mainViewPager.currentItem=3


            }
            false
        }

        BottomNavigationViewHelper.disableShiftMode(mBottomView)
        mainViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {
            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
            }

            override fun onPageSelected(p0: Int) {

                if (menuItem != null) {
                    menuItem.isChecked = false
                } else {
                    mBottomView.menu.getItem(0).isChecked = false
                }
                menuItem = mBottomView.menu.getItem(p0)
                menuItem.isChecked = true

            }

        })

    }

    override fun initLoad() {
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }


}
