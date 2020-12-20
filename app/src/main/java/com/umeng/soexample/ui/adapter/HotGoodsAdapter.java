package com.umeng.soexample.ui.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseAdapter;
import com.umeng.soexample.model.HomeData;
import com.umeng.soexample.utils.TxtUtils;

import java.util.List;

public class HotGoodsAdapter extends BaseAdapter {
    public HotGoodsAdapter(Context context, List<HomeData.DataBean.HotGoodsListBean> data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.layout_hotgoods;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        TextView tv_hotgoods_title = (TextView) vh.getViewById(R.id.tv_hotgoods_title);
        TextView tv_hotgoods_desc = (TextView) vh.getViewById(R.id.tv_hotgoods_desc);
        TextView tv_hotgoods_price = (TextView) vh.getViewById(R.id.tv_hotgoods_price);
        ImageView img_hotgoods = (ImageView) vh.getViewById(R.id.img_hotgoods);
        HomeData.DataBean.HotGoodsListBean bean = (HomeData.DataBean.HotGoodsListBean) data;
        TxtUtils.setTextView(tv_hotgoods_title,bean.getName());
        TxtUtils.setTextView(tv_hotgoods_desc,bean.getGoods_brief());
        TxtUtils.setTextView(tv_hotgoods_price,"￥"+bean.getRetail_price());
        Glide.with(context).load(bean.getList_pic_url()).into(img_hotgoods);
    }
}
