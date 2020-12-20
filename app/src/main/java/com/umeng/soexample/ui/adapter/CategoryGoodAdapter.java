package com.umeng.soexample.ui.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseAdapter;
import com.umeng.soexample.model.CategoryGoodBean;
import com.umeng.soexample.utils.TxtUtils;

import java.util.List;

public class CategoryGoodAdapter extends BaseAdapter<CategoryGoodBean.DataBeanX.GoodsListBean> {
    public CategoryGoodAdapter(Context context, List<CategoryGoodBean.DataBeanX.GoodsListBean> data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.layout_categorygood_item;
    }

    @Override
    protected void bindData(CategoryGoodBean.DataBeanX.GoodsListBean data, VH vh) {
        ImageView imgCategory = (ImageView) vh.getViewById(R.id.img_categorygood);
        Glide.with(context).load(data.getList_pic_url()).into(imgCategory);
        TextView txtCategoryName = (TextView) vh.getViewById(R.id.txt_categoryname);
        TxtUtils.setTextView(txtCategoryName,data.getName());
        TextView txtCategoryPrice = (TextView) vh.getViewById(R.id.txt_categoryprice);
        String price = "$"+data.getRetail_price();
        TxtUtils.setTextView(txtCategoryPrice,price);
    }
}
