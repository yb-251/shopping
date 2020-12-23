package com.umeng.soexample.ui.home;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseAdapter;
import com.umeng.soexample.model.home.HotGoodListBean;

import java.util.List;

public class NewGoodsDetailsAdapter extends BaseAdapter<HotGoodListBean.DataBeanX.DataBean> {
    private Context context;
    public NewGoodsDetailsAdapter(Context context, List<HotGoodListBean.DataBeanX.DataBean> data) {
        super(context, data);
        this.context = context;
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.item_newgoods;
    }

    @Override
    protected void bindData(HotGoodListBean.DataBeanX.DataBean data, VH vh) {
        ImageView imageView=vh.itemView.findViewById(R.id.img);
        TextView name=vh.itemView.findViewById(R.id.tv_name);
        TextView tv=vh.itemView.findViewById(R.id.tv_price);
        Glide.with(context).load(data.getList_pic_url()).into(imageView);
        name.setText(data.getName());
        tv.setText(data.getRetail_price()+"");
    }
}
