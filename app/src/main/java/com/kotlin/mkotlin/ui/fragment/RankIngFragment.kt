package com.kotlin.mkotlin.ui.fragment

import com.kotlin.mkotlin.R
import com.kotlin.mkotlin.base.BaseFragment

/**
 * 创建作者:zhuguoqing
 * 创建日期:2019/3/11
 * 作用:
 */
class RankIngFragment :BaseFragment() {
    companion object {
        fun getRankFragment()=RankIngFragment()
    }
    override fun initLayoutResID(): Int {
        return  R.layout.fragment_rank
    }

    override fun initData() {
    }

    override fun initView() {
    }

    override fun initLoad() {
    }
}