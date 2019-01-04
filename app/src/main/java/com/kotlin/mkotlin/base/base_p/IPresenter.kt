package com.kotlin.mkotlin.base.base_p

import android.content.Context
import com.kotlin.mkotlin.base.BaseFragment
import com.kotlin.mkotlin.base.base_v.BaseView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


/**
 * 创建作者:zhuguoqing
 * 创建日期:2019/1/4
 * 作用:
 */
abstract class IPresenter<out V : BaseView>(val view: V) {
    protected val context: Context = if (view is BaseFragment) {
        view.mContext
    } else {
        view as Context
    }

    private val compositeDisposable:CompositeDisposable=CompositeDisposable()

    fun addDisposable(disposable: Disposable) {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.add(disposable)
        }
    }

    fun removeDisposable(disposable: Disposable) {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.remove(disposable)
        }
    }

    fun detach() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.clear()
        }
    }


}