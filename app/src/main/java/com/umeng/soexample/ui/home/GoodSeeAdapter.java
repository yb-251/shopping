package com.umeng.soexample.ui.home;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseAdapter;
import com.umeng.soexample.model.home.GoodDetailBean;

import java.util.List;

public class GoodSeeAdapter extends BaseAdapter {
    public GoodSeeAdapter(Context context, List<GoodDetailBean.DataBeanX.InfoBean> data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.layout_newgoods;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        TextView goods_see_title = (TextView) vh.getViewById(R.id.newgoods_title);
        TextView goods_see_price = (TextView) vh.getViewById(R.id.newgoods_price);
        ImageView goods_see_img = (ImageView) vh.getViewById(R.id.newgoods_img);

        GoodDetailBean.DataBeanX.InfoBean bean = (GoodDetailBean.DataBeanX.InfoBean) data;
        goods_see_title.setText(bean.getName());
        goods_see_price.setText("￥"+bean.getRetail_price());
        Glide.with(context).load(bean.getList_pic_url()).into(goods_see_img);

        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
