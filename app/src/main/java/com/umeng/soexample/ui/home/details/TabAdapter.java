package com.umeng.soexample.ui.home.details;

import android.content.Context;
import android.widget.TextView;

import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseAdapter;
import com.umeng.soexample.model.data.ShotTabBean;
import com.umeng.soexample.utils.TxtUtils;

import java.util.List;

public class TabAdapter extends BaseAdapter {
    public TabAdapter(Context context, List<ShotTabBean.DataBean.CategoryListBean> data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.layout_tab;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        TextView tv_detail_name = (TextView) vh.getViewById(R.id.tv_detail_name);
        TextView tv_detail_desc = (TextView) vh.getViewById(R.id.tv_detail_desc);
        ShotTabBean.DataBean.CategoryListBean bean = (ShotTabBean.DataBean.CategoryListBean) data;
        TxtUtils.setTextView(tv_detail_name,bean.getName());
        TxtUtils.setTextView(tv_detail_desc,bean.getFront_desc());
    }
}
