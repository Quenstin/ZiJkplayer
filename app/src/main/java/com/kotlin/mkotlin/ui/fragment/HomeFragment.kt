package com.kotlin.mkotlin.ui.fragment

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import com.kotlin.mkotlin.R
import com.kotlin.mkotlin.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_main.*
import tv.danmaku.ijk.media.player.IjkMediaPlayer


/**
 * 创建作者:zhuguoqing
 * 创建日期:2019/1/5
 * 作用:
 */
class HomeFragment : BaseFragment() {

    private val url5 = "https://aweme.snssdk.com/aweme/v1/play/?video_id=97022dc18711411ead17e8dcb75bccd2&line=0&ratio=720p&media_type=4&vr_type=0"

    //伴生对象
    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun initLayoutResID(): Int {
        return R.layout.fragment_main
    }

    override fun initData() {


    }

    override fun initView() {
        //加载so文件
        try {
            IjkMediaPlayer.loadLibrariesOnce(null)
            IjkMediaPlayer.native_profileBegin("libijkplayer.so")
        } catch (e: Exception) {
            activity!!.finish()
        }


        if (ContextCompat.checkSelfPermission(activity!!, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(activity!!, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
        }

    }

    override fun initLoad() {
        ijkView.setVideoURI(Uri.parse(url5))
        ijkView.start()

//        homeIjkView.setUrl(mVideoList.get(position).getUrl());

    }

    override fun onStop() {
        super.onStop()
        IjkMediaPlayer.native_profileEnd()
    }
}