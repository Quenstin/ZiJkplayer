package com.kotlin.mkotlin.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
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
    private Context mContext;


    public AutoRecycleAdapter(int layoutResId, @Nullable List<VideoBean> data,Context context) {
        super(layoutResId, data);
        this.mBeans=data;
        this.mContext=context;

    }

    @Override
    protected void convert(BaseViewHolder helper, VideoBean item) {
        VideoBean videoBean=mBeans.get(helper.getLayoutPosition());

//        GlideApp.with(mContext)
//                .load(videoBean.getThumb())
//                .placeholder(android.R.color.white)
//                .into(helper.getView(0));


    }
}
