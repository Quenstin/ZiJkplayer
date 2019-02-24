package com.kotlin.mkotlin.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.example.zijkplayer.voidcontroller.LiveVideoController
import com.example.zijkplayer.voidplayer.PlayerConfig
import com.kotlin.mkotlin.R
import com.kotlin.mkotlin.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_main.*


/**
 * 创建作者:zhuguoqing
 * 创建日期:2019/1/5
 * 作用:
 */
class HomeFragment : BaseFragment() {

    //伴生对象
    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun initLayoutResID(): Int {
        return R.layout.fragment_main
    }

    override fun initData() {



    }

    override fun initView() {
        zrcView.layoutManager=LinearLayoutManager(this.activity)




    }

    override fun initLoad() {



    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            activity!!.finish()
        }
        return super.onOptionsItemSelected(item)
    }


}