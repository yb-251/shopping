package com.umeng.soexample.ui.adapter;

import android.content.Context;
import android.widget.TextView;

import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseAdapter;
import com.umeng.soexample.model.data.GoodDetailBean;

import java.util.List;

public class GoodAttributeAdapter extends BaseAdapter {
    Context context;
    public GoodAttributeAdapter(Context context, List<GoodDetailBean.DataBeanX.AttributeBean> data) {
        super(context, data);
        this.context = context;
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.layour_good_attribute_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        TextView title = (TextView) vh.getViewById(R.id.tv_attribute_title);
        TextView desc = (TextView) vh.getViewById(R.id.tv_attribute_desc);
        GoodDetailBean.DataBeanX.AttributeBean data1 = (GoodDetailBean.DataBeanX.AttributeBean) data;
        title.setText(data1.getName());
        desc.setText(data1.getValue());
    }
}
