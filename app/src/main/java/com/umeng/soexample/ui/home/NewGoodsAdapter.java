package com.umeng.soexample.ui.home;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseAdapter;
import com.umeng.soexample.model.home.HomeData;
import com.umeng.soexample.utils.TxtUtils;

import java.util.List;

public class NewGoodsAdapter extends BaseAdapter {
    public NewGoodsAdapter(Context context, List<HomeData.DataBean.NewGoodsListBean> data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.layout_newgoods;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        TextView newgoods_title = (TextView) vh.getViewById(R.id.newgoods_title);
        TextView newgoods_price = (TextView) vh.getViewById(R.id.newgoods_price);
        ImageView newgoods_img = (ImageView) vh.getViewById(R.id.newgoods_img);
        HomeData.DataBean.NewGoodsListBean bean = (HomeData.DataBean.NewGoodsListBean) data;
        TxtUtils.setTextView(newgoods_title,bean.getName());
        TxtUtils.setTextView(newgoods_price,"￥"+bean.getRetail_price());
        Glide.with(context).load(bean.getList_pic_url()).into(newgoods_img);

        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null){
                    onItemClickListener.onClick(vh.getLayoutPosition());
                }
            }
        });
    }

    BrandAdapter.OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(BrandAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    interface OnItemClickListener{
        void onClick(int pos);
    }

}
