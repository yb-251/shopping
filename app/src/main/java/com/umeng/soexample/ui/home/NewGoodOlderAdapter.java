package com.umeng.soexample.ui.home;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseAdapter;
import com.umeng.soexample.model.home.HotGoodListBean;

import java.util.List;

public class NewGoodOlderAdapter extends BaseAdapter {

    public NewGoodOlderAdapter(Context context, List<HotGoodListBean.DataBeanX.GoodsListBean> data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.item_newgoods;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        ImageView imageView=vh.itemView.findViewById(R.id.img);
        TextView name=vh.itemView.findViewById(R.id.tv_name);
        TextView tv=vh.itemView.findViewById(R.id.tv_price);

        HotGoodListBean.DataBeanX.GoodsListBean data1 = (HotGoodListBean.DataBeanX.GoodsListBean) data;

        Glide.with(context).load(data1.getList_pic_url()).into(imageView);
        name.setText(data1.getName());
        tv.setText("￥"+String.valueOf(data1.getRetail_price()));

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
