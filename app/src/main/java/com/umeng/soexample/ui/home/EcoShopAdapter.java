package com.umeng.soexample.ui.home;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseAdapter;
import com.umeng.soexample.model.shop.ShopAllData;

import java.util.List;

public class EcoShopAdapter extends BaseAdapter<ShopAllData.DataBeanX.DataBean> {
    Context context;
    public EcoShopAdapter(Context context, List<ShopAllData.DataBeanX.DataBean> data) {
        super(context, data);
        this.context = context;
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.layout_shopall_item;
    }

    @Override
    protected void bindData(ShopAllData.DataBeanX.DataBean data, VH vh) {
        TextView name = (TextView) vh.getViewById(R.id.shop_good_name);
        ImageView img = (ImageView) vh.getViewById(R.id.shop_img);
        TextView price = (TextView) vh.getViewById(R.id.shop_good_price);
        Glide.with(context).load(data.getList_pic_url()).into(img);
        name.setText(data.getName());
        price.setText(String.valueOf(data.getRetail_price()));
    }
}
