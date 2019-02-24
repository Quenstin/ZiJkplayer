package com.kotlin.mkotlin.ui.fragment

import com.example.zijkplayer.voidcontroller.LiveVideoController
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
    var controller: LiveVideoController? = null
    private val LIVE_URL = "http://ivi.bupt.edu.cn/hls/cctv6.m3u8"


    companion object {
        fun newInstance() = TiXiFragment()
    }

    override fun initData() {
        controller = LiveVideoController(this.activity!!)
        controller?.setLive()
        zLiveView.setPlayerConfig(PlayerConfig.Builder()
                .autoRotate()//自动旋转屏幕
//                    .enableCache()//启用边播边存
                .enableMediaCodec()//启动硬解码
//                    .usingSurfaceView()//使用SurfaceView
//                    .setCustomMediaPlayer(new ExoMediaPlayer(this))
//                    .setCustomMediaPlayer(new AndroidMediaPlayer(this))
                .build())
        zLiveView.setUrl(LIVE_URL)
        zLiveView.setVideoController(controller)
        zLiveView.start()
    }

    override fun initView() {
    }

    override fun initLoad() {
    }

    override fun initLayoutResID(): Int {
        return R.layout.fragment_tixi
    }

    override fun onPause() {
        super.onPause()
        zLiveView.pause()
    }

    override fun onResume() {
        super.onResume()
        zLiveView.resume()
    }

    override fun onDestroy() {
        super.onDestroy()
        zLiveView.release()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (zLiveView != null)
            if (isVisibleToUser) {
                zLiveView.start()
            } else {
                if (zLiveView.isPlaying)
                    zLiveView.release()
            }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)


    }
}