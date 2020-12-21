package com.umeng.soexample.ui.shop.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.umeng.soexample.R;
import com.umeng.soexample.app.MyApp;
import com.umeng.soexample.model.data.GreendaoBean;
import com.umeng.soexample.ui.shop.db.GreendaoBeanDao;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class GwcAdapter extends RecyclerView.Adapter<GwcAdapter.ViewHolder> {
    private Context context;
    public List<GreendaoBean> list = new ArrayList<>();
    GreendaoBeanDao db = MyApp.getInstance().getDaoSession().getGreendaoBeanDao();

    public void setList(List<GreendaoBean> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public GwcAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.shop_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GreendaoBean greendaoBean = list.get(position);
        Glide.with(context).load(greendaoBean.getImg()).into(holder.mImageIv);
        holder.mNameTv.setText(greendaoBean.getDesc());
        holder.mTotalPriceTv.setText("￥" + greendaoBean.getPrice());
        holder.mNumTv.setText(greendaoBean.getNumber() + "");
        holder.mCb.setChecked(greendaoBean.getCb());

        holder.mAddTv.setOnClickListener(v -> {
            int num = greendaoBean.getNumber();
            num++;
            holder.mNumTv.setText(num + "");
            greendaoBean.setNumber(num);
            notifyItemChanged(position, true);
            EventBus.getDefault().postSticky(list);
            db.update(greendaoBean);
        });
        holder.mReduceTv.setOnClickListener(v -> {
            int num = greendaoBean.getNumber();
            if (num > 1) {
                num--;
            }
            holder.mNumTv.setText(num + "");
            greendaoBean.setNumber(num);
            notifyItemChanged(position, true);
            EventBus.getDefault().postSticky(list);
            greendaoBean.setNumber(num);
            db.update(greendaoBean);
        });
        holder.mCb.setOnCheckedChangeListener((buttonView, isChecked) -> {
            greendaoBean.setCb(isChecked);
            db.update(greendaoBean);
            EventBus.getDefault().postSticky(list);
        });
    }

    @Override
    public int getItemCount() {
        Log.d("GwcAdapter", "list.size():" + list.size());
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CheckBox mCb;
        private ImageView mImageIv;
        private TextView mNameTv;
        private TextView mTotalPriceTv;
        private TextView mAddTv;
        private TextView mNumTv;
        private TextView mReduceTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mCb = (CheckBox) itemView.findViewById(R.id.cb_item);
            /*mImageIv = (ImageView) itemView.findViewById(R.id.iv_image);
            mNameTv = (TextView) itemView.findViewById(R.id.tv_name);
            mTotalPriceTv = (TextView) itemView.findViewById(R.id.tv_totalPrice);
            mAddTv = (TextView) itemView.findViewById(R.id.tv_add);
            mNumTv = (TextView) itemView.findViewById(R.id.tv_num);
            mReduceTv = (TextView) itemView.findViewById(R.id.tv_reduce);*/
        }
    }
}
