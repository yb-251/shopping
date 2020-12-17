package com.umeng.soexample.text;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.umeng.soexample.R;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    List<ListData.DataBean> list;

    public ListAdapter(Context context, List<ListData.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.btn_item, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ListData.DataBean dataBean = list.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.cb.setChecked(dataBean.isSelect());
        viewHolder.cb.setText(dataBean.getName());


            viewHolder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if (onItemClickListener != null){
                        onItemClickListener.onClick(position,isChecked,buttonView);
                    }
                }
            });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public CheckBox cb;

        public ViewHolder(View rootView) {
            super(rootView);
            this.cb = (CheckBox) rootView.findViewById(R.id.cb);
        }

    }
    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    interface OnItemClickListener{
        void onClick(int pos,boolean bool,CompoundButton compoundButton);
    }
}
