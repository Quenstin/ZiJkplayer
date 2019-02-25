package com.kotlin.mkotlin.ui.fragment

import android.graphics.Rect
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE
import android.util.Log
import android.view.MenuItem
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.zijkplayer.voidplayer.VideoViewManager
import com.kotlin.mkotlin.R
import com.kotlin.mkotlin.adapter.AutoRecycleAdapter
import com.kotlin.mkotlin.base.BaseFragment
import com.kotlin.mkotlin.bean.DataUtil
import kotlinx.android.synthetic.main.auto_rec.*
import kotlinx.android.synthetic.main.auto_rec.view.*
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_tixi.*


/**
 * 创建作者:zhuguoqing
 * 创建日期:2019/1/5
 * 作用:
 */
class HomeFragment : BaseFragment() {

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
        val layoutManager = LinearLayoutManager(this.activity)
        zrcView.layoutManager = layoutManager
        val recyAdapter =AutoRecycleAdapter(R.layout.auto_rec, DataUtil.getVideoList())
        recyAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN)
        recyAdapter.isFirstOnly(false)
        zrcView.adapter = recyAdapter

        zrcView.addOnChildAttachStateChangeListener(object : RecyclerView.OnChildAttachStateChangeListener {
            override fun onChildViewDetachedFromWindow(p0: View) {
                val ijkview = p0.video_player
                if (ijkview != null && !ijkview.isFullScreen)
                    ijkview.stopPlayback()

            }

            override fun onChildViewAttachedToWindow(p0: View) {

            }

        })
        zrcView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            var firstVisibleItem: Int = 0
            var lastVisibleItem: Int = 0
            var visibleCount: Int = 0
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                when (newState) {
                    SCROLL_STATE_IDLE //滚动停止
                    -> autoPlayVideo(recyclerView)
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                firstVisibleItem = layoutManager.findFirstVisibleItemPosition()
                lastVisibleItem = layoutManager.findLastVisibleItemPosition()
                visibleCount = lastVisibleItem - firstVisibleItem//记录可视区域item个数
            }

            fun autoPlayVideo(view: RecyclerView?) {
                //循环遍历可视区域videoview,如果完全可见就开始播放
                for (i in 0 until visibleCount) {
                    if (view?.getChildAt(i) == null) continue
                    val ijkVideoView = view.getChildAt(i).video_player
                    if (ijkVideoView != null) {
                        val rect = Rect()
                        ijkVideoView.getLocalVisibleRect(rect)
                        val videoHeight = ijkVideoView.height
                        if (rect.top == 0 && rect.bottom == videoHeight) {
                            ijkVideoView.start()
                            return
                        }
                    }
                }
            }

        })

        zrcView.post {
            //自动播放第一个

            val ijkVideoView = zrcView.getChildAt(0).video_player
            ijkVideoView.start()
        }


    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)

        if (video_player != null){
            if (isVisibleToUser) {
                video_player.start()
                Log.e("播放器", "----home------star--")


            } else {
                if (video_player.isPlaying) {
                    video_player.stopPlayback()
                    video_player.release()
                    Log.e("播放器", "----home------stop--")

                }
            }
        }

    }


    override fun initLoad() {


    }

    override fun onPause() {
        super.onPause()
        VideoViewManager.instance().releaseVideoPlayer()
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


}