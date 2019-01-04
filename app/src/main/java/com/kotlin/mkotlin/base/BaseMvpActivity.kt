package com.kotlin.mkotlin.base

import android.os.Bundle
import com.kotlin.mkotlin.base.base_p.IPresenter

/**
 * 创建作者:zhuguoqing
 * 创建日期:2019/1/4
 * 作用:
 */
abstract class BaseMvpActivity<P : IPresenter<*>> :BaseActivity() {

    lateinit var presenter :P

    abstract fun initPresenter(): P


    override fun onCreate(savedInstanceState: Bundle?) {
        presenter=initPresenter()
        super.onCreate(savedInstanceState)

    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }

}