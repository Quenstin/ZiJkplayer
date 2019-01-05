package com.kotlin.mkotlin.ui.fragment

import com.kotlin.mkotlin.R
import com.kotlin.mkotlin.base.BaseFragment

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
    }

    override fun initLoad() {
    }
}