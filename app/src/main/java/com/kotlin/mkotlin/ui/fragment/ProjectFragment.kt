package com.kotlin.mkotlin.ui.fragment

import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.kotlin.mkotlin.R
import com.kotlin.mkotlin.adapter.AutoRecycleAdapter
import com.kotlin.mkotlin.base.BaseFragment
import com.kotlin.mkotlin.bean.DataUtil
import kotlinx.android.synthetic.main.auto_rec.*
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_project.*

/**
 * 创建作者:zhuguoqing
 * 创建日期:2019/1/5
 * 作用:
 */
class ProjectFragment : BaseFragment() {

    companion object {
        fun newInstance() = ProjectFragment()
    }

    override fun initLayoutResID(): Int {
        return R.layout.fragment_project
    }

    override fun initData() {
    }

    override fun initView() {
        val layoutManagerStagger = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        zrcViewWaterFall.layoutManager = layoutManagerStagger
        val recyAdapterStagger = AutoRecycleAdapter(R.layout.auto_rec, DataUtil.getVideoList())
        recyAdapterStagger.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT )
        recyAdapterStagger.isFirstOnly(false)
        zrcViewWaterFall.adapter = recyAdapterStagger
        recyAdapterStagger.onItemClickListener = BaseQuickAdapter.OnItemClickListener { _, _, _ -> video_player.start() }


    }

    override fun initLoad() {
    }
}