package com.kotlin.mkotlin

import android.app.Application
import org.litepal.LitePal

/**
 * 创建作者:zhuguoqing
 * 创建日期:2019/1/4
 * 作用:
 */
class KotlinApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        LitePal.initialize(this)
    }

    companion object {
        private lateinit var instance: KotlinApp
        fun getApp(): KotlinApp {
            return instance
        }
    }
}