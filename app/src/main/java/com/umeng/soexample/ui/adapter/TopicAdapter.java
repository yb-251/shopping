package com.umeng.soexample.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseAdapter;
import com.umeng.soexample.model.HomeData;
import com.umeng.soexample.utils.TxtUtils;

import java.util.List;

public class TopicAdapter extends BaseAdapter {
    public TopicAdapter(Context context, List<HomeData.DataBean.TopicListBean> data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.item_topic;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        TextView tv_topic_titles = (TextView) vh.getViewById(R.id.tv_topic_titles);
        TextView tv_topic_subtitle = (TextView) vh.getViewById(R.id.tv_topic_subtitle);
        TextView tv_topic_price = (TextView) vh.getViewById(R.id.tv_topic_price);
        ImageView img_topic = (ImageView) vh.getViewById(R.id.img_topic);
        HomeData.DataBean.TopicListBean bean = (HomeData.DataBean.TopicListBean) data;
        TxtUtils.setTextView(tv_topic_titles,bean.getTitle());
        TxtUtils.setTextView(tv_topic_subtitle,bean.getSubtitle());
        TxtUtils.setTextView(tv_topic_price,"￥"+bean.getPrice_info());
        Glide.with(context).load(bean.getScene_pic_url()).into(img_topic);
    }
}
