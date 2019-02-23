package com.kotlin.mkotlin.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.kotlin.mkotlin.R
import com.shehuan.statusview.StatusView
import com.shehuan.statusview.StatusViewBuilder
import kotlinx.android.synthetic.main.layout_toobar.*

/**
 * 创建作者:zhuguoqing
 * 创建日期:2019/1/4
 * 作用:activity  基类
 */
abstract class BaseActivity : AppCompatActivity() {
    protected lateinit var statusView: StatusView

    lateinit var mContext:BaseActivity

    abstract fun getLayoutId():Int
    abstract fun initData()
    abstract fun initView()
    abstract fun initLoad()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        mContext=this

        initView()
        initData()
        initLoad()
    }

    protected fun initStatusView(id:Int,error:(View)->Unit){
        statusView=StatusView.init(this,id).apply {
            setLoadingView(R.layout.dialog_loading_layout)
            config(StatusViewBuilder.Builder()
                    .showEmptyRetry(false)
                    .setOnErrorRetryClickListener(error)
                    .build())
        }

    }
    protected  fun initToobar(titleString: Int){
        initToobar(getString(titleString))

    }
    protected fun initToobar(string: String){
        toobar.run {
            title=string
            setSupportActionBar(this)
            setNavigationOnClickListener {
                finish()
            }
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

}