package com.kotlin.mkotlin.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.zijkplayer.voidcontroller.LiveVideoController;
import com.example.zijkplayer.voidplayer.IjkVideoView;
import com.example.zijkplayer.voidplayer.PlayerConfig;
import com.kotlin.mkotlin.R;
import com.kotlin.mkotlin.bean.VideoBean;
import com.kotlin.mkotlin.utlis.GlideApp;
import com.kotlin.mkotlin.utlis.ZGlideModule;

import java.util.List;

/**
 * 创建作者:zhuguoqing
 * 创建日期:2019/2/24
 * 作用:自动播放列表adapter
 */
public class AutoRecycleAdapter extends BaseQuickAdapter<VideoBean, BaseViewHolder> {
    private List<VideoBean> mBeans;
    private IjkVideoView ijkVideoView;
    private LiveVideoController controller;
    private TextView title;
    private PlayerConfig mPlayerConfig;


    public AutoRecycleAdapter(int layoutResId, @Nullable List<VideoBean> data) {
        super(layoutResId, data);
        this.mBeans=data;

    }

    @Override
    protected void convert(BaseViewHolder helper, VideoBean item) {
        ijkVideoView=new IjkVideoView(mContext);
        controller=new LiveVideoController(mContext);
        mPlayerConfig = new PlayerConfig.Builder()
                .autoRotate()
                .addToPlayerManager()//required
//                        .savingProgress()
                .build();
        GlideApp.with(mContext)
                .load(item.getThumb())
                .placeholder(android.R.color.white)
                .into(controller.getThumb());

        IjkVideoView ijkVideoView=helper.getView(R.id.video_player);
        TextView textView=helper.getView(R.id.tv_title);
        ijkVideoView.setPlayerConfig(mPlayerConfig);
        ijkVideoView.setUrl(item.getThumb());
        ijkVideoView.setTitle(item.getTitle());
        ijkVideoView.setVideoController(controller);
        textView.setText(item.getTitle());


    }
}
