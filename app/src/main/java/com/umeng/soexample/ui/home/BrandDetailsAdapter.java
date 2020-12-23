package com.umeng.soexample.ui.home;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseAdapter;
import com.umeng.soexample.model.home.BrandDetailsData;
import com.umeng.soexample.utils.TxtUtils;

import java.util.List;

public class BrandDetailsAdapter extends BaseAdapter {
    public BrandDetailsAdapter(Context context, List<BrandDetailsData.DataBeanX.DataBean> data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.brand_details_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        BrandDetailsData.DataBeanX.DataBean bean = (BrandDetailsData.DataBeanX.DataBean) data;
        ImageView img_brand = (ImageView) vh.getViewById(R.id.img_brand);
        TextView tv_branddetails_title = (TextView) vh.getViewById(R.id.tv_branddetails_title);
        TextView tv_branddetails_price = (TextView) vh.getViewById(R.id.tv_branddetails_price);
        TxtUtils.setTextView(tv_branddetails_title,bean.getName());
        TxtUtils.setTextView(tv_branddetails_price,bean.getFloor_price()+"元起");
        Glide.with(context).load(bean.getApp_list_pic_url()).into(img_brand);
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener!= null){
                    onItemClickListener.onClick(vh.getLayoutPosition());
                }
            }
        });
    }

    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onClick(int pos);
    }
}
