package com.kotlin.mkotlin.ui.fragment

import android.annotation.SuppressLint
import android.content.Intent
import com.kotlin.mkotlin.R
import com.kotlin.mkotlin.base.BaseFragment
import kotlinx.android.synthetic.main.fragmnet_daohang.*

/**
 * 创建作者:zhuguoqing
 * 创建日期:2019/1/5
 * 作用:
 */
class DaoHangFragment : BaseFragment() {

    companion object {
        fun newInstance() = DaoHangFragment()
    }

    override fun initLayoutResID(): Int {
        return R.layout.fragmnet_daohang
    }

    override fun initData() {
        minIcon.setImageResource(R.mipmap.circle_heart_circle)
        txtName.text = "姓名： 朱国庆"
        txtWork.text="职位： Android开发"
        txtWorkAge.text="开发工龄： 4年"


    }

    @SuppressLint("WrongConstant")
    override fun initView() {
        rlGitHub.setOnClickListener { startActivity(Intent.parseUri("https://github.com/Quenstin", Intent.FLAG_ACTIVITY_NEW_TASK)) }
        rlJianShu.setOnClickListener { startActivity(Intent.parseUri("https://www.jianshu.com/u/e571392e8679", Intent.FLAG_ACTIVITY_NEW_TASK)) }
    }

    override fun initLoad() {
    }
}