package com.kotlin.mkotlin.base

import android.os.Bundle
import com.kotlin.mkotlin.base.base_p.IPresenter

abstract class BaseMvpFragment<P : IPresenter<*>> : BaseFragment() {
    lateinit var presenter: P

    abstract fun initPresenter(): P

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        presenter = initPresenter()
        super.onActivityCreated(savedInstanceState)
    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }
}