package com.kotlin.mkotlin.ui.fragment

import android.util.Log
import android.view.MenuItem
import com.example.zijkplayer.voidcontroller.LiveVideoController
import com.example.zijkplayer.voidplayer.IjkVideoView
import com.example.zijkplayer.voidplayer.PlayerConfig
import com.kotlin.mkotlin.R
import com.kotlin.mkotlin.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_tixi.*

/**
 * 创建作者:zhuguoqing
 * 创建日期:2019/1/5
 * 作用:直播
 */
class TiXiFragment : BaseFragment() {
    //标志位，标志已经初始化完成
    var isInit = false
    var controller: LiveVideoController? = null
    protected var mHasLoadedOnce: Boolean = false
    private val LIVE_URL = "http://ivi.bupt.edu.cn/hls/cctv6.m3u8"


    companion object {
        fun newInstance() = TiXiFragment()
    }

    override fun initData() {
        controller = LiveVideoController(this.activity!!)
        controller?.setLive()
        zLiveView.setPlayerConfig(PlayerConfig.Builder()
                .autoRotate()//自动旋转屏幕
                .build())
        zLiveView.setScreenScale(IjkVideoView.SCREEN_SCALE_16_9)
        zLiveView.setUrl(LIVE_URL)
        zLiveView.setVideoController(controller)
//        zLiveView.start()
    }

    override fun initView() {
        isInit = true
        isCanLoadData()
    }

    override fun initLoad() {
    }

    override fun initLayoutResID(): Int {
        return R.layout.fragment_tixi
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            activity!!.finish()
            if (zLiveView.isPlaying){
                zLiveView.stopPlayback()
                zLiveView.release()
                Log.e("播放器","----------stop--")

            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        Log.e("播放器","----------ss--")

    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        isCanLoadData()
        if (zLiveView != null)
            if (isVisibleToUser) {
                zLiveView.start()
                Log.e("播放器","----------star--")


            } else {
                if (zLiveView.isPlaying){
                    zLiveView.stopPlayback()
                    zLiveView.release()
                    Log.e("播放器","----------stop--")

                }


            }

    }

    /**
     * 禁止预加载
     */
    private fun isCanLoadData() {
        if (!isInit) {
            return
        }
        if (getUserVisibleHint() && !mHasLoadedOnce) {
            loadData()
        }
    }

    fun loadData() {
        //数据加载成功后
        mHasLoadedOnce = true
    }


}