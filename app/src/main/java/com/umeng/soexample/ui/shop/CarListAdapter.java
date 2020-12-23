package com.umeng.soexample.ui.shop;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseAdapter;
import com.umeng.soexample.model.shop.CarBean;
import com.umeng.soexample.utils.ImageLoader;
import com.umeng.soexample.widget.NumberSelect;

import java.security.PublicKey;
import java.util.List;

public class CarListAdapter extends BaseAdapter {

    private boolean isEdit; //是否是编辑状态
    private boolean isCheck; //是否是编辑状态

    public void setEditState(boolean bool){
        isEdit = bool;
    }
    public void setCheck(boolean all){
        isCheck = all;
    }

    public CarListAdapter(Context context, List<CarBean.DataBean.CartListBean> data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.item_layout_car;
    }

    @Override
    protected void bindData(Object bean, VH vh) {
        CheckBox checkBox = (CheckBox) vh.getViewById(R.id.checkbox);
        ImageView imgItem = (ImageView) vh.getViewById(R.id.img_item);
        TextView txtName = (TextView) vh.getViewById(R.id.txt_name);
        TextView txtPrice = (TextView) vh.getViewById(R.id.txt_price);
        TextView txtNumber = (TextView) vh.getViewById(R.id.txt_number);
        TextView txtEditTitle = (TextView) vh.getViewById(R.id.txt_edit_title);
        NumberSelect numberSelect = (NumberSelect) vh.getViewById(R.id.layout_change);

        txtName.setVisibility(isEdit? View.GONE:View.VISIBLE);
        txtNumber.setVisibility(isEdit?View.GONE:View.VISIBLE);
        txtEditTitle.setVisibility(isEdit?View.VISIBLE:View.GONE);
        numberSelect.setVisibility(isEdit?View.VISIBLE:View.GONE);

        CarBean.DataBean.CartListBean  data= (CarBean.DataBean.CartListBean) bean;

        // 设置选中状态
        checkBox.setChecked(isEdit?data.selectEdit:data.selectOrder);
        Glide.with(context).load(data.getList_pic_url()).into(imgItem);
        txtPrice.setText("￥"+data.getRetail_price());
        txtNumber.setText("X"+String.valueOf(data.getNumber()));
        txtName.setText(data.getGoods_name());
        numberSelect.addPage(R.layout.layout_number_change);
        numberSelect.addChangeNumber(new NumberSelect.ChangeNumber() {
            @Override
            public void change(int number) {
                //修改本地数据得值
                data.setNumber(number);
            }
        });

        checkBox.setTag(data.getId());
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (iItemViewClick != null){
                    int id = (int) buttonView.getTag();
                    iItemViewClick.itemViewClick(id,isChecked);
                }
            }
        });

        //全选
        if (isCheck){
            checkBox.setChecked(true);
        }else {
            checkBox.setChecked(false);
        }

        //显示和隐藏
        if(isEdit){
            txtName.setVisibility(View.VISIBLE);
            txtNumber.setVisibility(View.GONE);
            txtEditTitle.setVisibility(View.VISIBLE);
            numberSelect.setVisibility(View.VISIBLE);

        }else{
            txtName.setVisibility(View.GONE);
            txtNumber.setVisibility(View.VISIBLE);
            txtEditTitle.setVisibility(View.GONE);
            numberSelect.setVisibility(View.GONE);
        }



    }
}
