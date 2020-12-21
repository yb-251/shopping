package com.umeng.soexample.ui.sort;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseAdapter;
import com.umeng.soexample.model.data.ShotItemData;

import java.util.List;

public class SortAdapter extends BaseAdapter {


    public SortAdapter(Context context, List<ShotItemData.DataBean.CurrentCategoryBean.SubCategoryListBean> data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.sort_layout;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        ShotItemData.DataBean.CurrentCategoryBean.SubCategoryListBean data1 = (ShotItemData.DataBean.CurrentCategoryBean.SubCategoryListBean) data;
        ImageView img_item = (ImageView) vh.getViewById(R.id.img_item);
        TextView tv_sort_item = (TextView) vh.getViewById(R.id.tv_sort_item);
        tv_sort_item.setText(data1.getName());
        Glide.with(context).load(data1.getWap_banner_url()).into(img_item);
    }
}
