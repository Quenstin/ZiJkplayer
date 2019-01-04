package com.shehuan.wanandroid.base.net.observer

import android.content.Context
import com.blankj.utilcode.util.ToastUtils
import com.kotlin.mkotlin.KotlinApp
import com.shehuan.wanandroid.base.net.exception.ResponseException
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.lang.ref.WeakReference

abstract class BaseObserver<E>(private val showErrorTip: Boolean = true) : Observer<E> {
    private val wrContext: WeakReference<Context> = WeakReference(KotlinApp.getApp())

    private lateinit var disposable: Disposable

    override fun onSubscribe(d: Disposable) {
        disposable = d
    }

    override fun onNext(data: E) {
        onSuccess(data)
    }

    override fun onError(e: Throwable) {
        val responseException: ResponseException = e as ResponseException
        if (showErrorTip) {
            ToastUtils.showShort(responseException.getErrorMessage())
        }
        onError(responseException)
    }

    override fun onComplete() {

    }

    fun getDisposable() = disposable

    abstract fun onSuccess(data: E)

    abstract fun onError(e: ResponseException)
}