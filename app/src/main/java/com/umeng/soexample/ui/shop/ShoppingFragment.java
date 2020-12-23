package com.umeng.soexample.ui.shop;


import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseAdapter;
import com.umeng.soexample.base.BaseFragment;
import com.umeng.soexample.interfaces.shop.ICar;
import com.umeng.soexample.model.shop.CarBean;
import com.umeng.soexample.presenter.shop.CarPresenter;
import com.umeng.soexample.utils.SpUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShoppingFragment extends BaseFragment<ICar.Presenter> implements ICar.View, View.OnClickListener {


    TextView tvReturn;
    TextView tvRefund;
    TextView tvPostage;
    LinearLayout postage;
    RecyclerView recyGood;
    NestedScrollView scrollView;
    CheckBox checkboxAll;
    TextView txtTotalPrice;
    TextView txtEdit;
    TextView txtSubmit;
    ConstraintLayout layoutCommon;

    private CarBean carBean;

    private boolean isEdit; //是否是编辑状态
    private List<CarBean.DataBean.CartListBean> list;
    private CarListAdapter carListAdapter;

    public ShoppingFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_shopping;
    }

    @Override
    protected ICar.Presenter createPrenter() {
        return new CarPresenter();
    }

    @Override
    protected void initView() {
        checkboxAll = getActivity().findViewById(R.id.checkbox_all);
        txtEdit = getActivity().findViewById(R.id.txt_edit);
        txtSubmit = getActivity().findViewById(R.id.txt_submit);
        tvReturn = getActivity().findViewById(R.id.tv_return);
        tvPostage = getActivity().findViewById(R.id.tv_postage);
        tvRefund = getActivity().findViewById(R.id.tv_refund);
        recyGood = getActivity().findViewById(R.id.recy_good);
        txtTotalPrice = getActivity().findViewById(R.id.txt_totalPrice);
        tvRefund = getActivity().findViewById(R.id.tv_refund);

        recyGood.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<>();
        carListAdapter = new CarListAdapter(getActivity(), list);
        recyGood.setAdapter(carListAdapter);

        txtEdit.setOnClickListener(this);
        txtSubmit.setOnClickListener(this);
        checkboxAll.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        String token = SpUtils.getInstance().getString("token");
        if (!TextUtils.isEmpty(token)) {
            presenter.getCarList();
        } else {
            gotoLogin();
        }

        carListAdapter.addItemViewClick(new BaseAdapter.IItemViewClick() {
            @Override
            public void itemViewClick(int id, Object data) {
                for (CarBean.DataBean.CartListBean item : carBean.getData().getCartList()) {
                    if (item.getId() == id) {
                        if (!isEdit) {
                            item.selectOrder = (boolean) data;
                        }else {
                            item.selectEdit = (boolean) data;
                        }
                        break;
                    }
                }
                boolean isSelectAll;
                if (!isEdit){
                    isSelectAll =totalSelectOrder();
                }else {
                    isSelectAll = totalSelectEdit();
                }
                checkboxAll.setChecked(isSelectAll);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.getCarList();
    }

    @Override
    public void getCarListReturn(CarBean carBean) {
        this.carBean = carBean;
        list.clear();
        list.addAll(carBean.getData().getCartList());
        carListAdapter.notifyDataSetChanged();
    }

    /**
     * 下单状态的数据刷新
     *
     * @param bool
     */
    private void updateGoodSelectStateOrder(boolean bool) {
        for (CarBean.DataBean.CartListBean item : carBean.getData().getCartList()) {
            item.selectOrder = bool;
        }
        totalSelectOrder();
        carListAdapter.notifyDataSetChanged();
    }

    /**
     * 编辑状态下的数据刷新
     *
     * @param bool
     */
    private void updateGoodSelectStateEdit(boolean bool) {
        for (CarBean.DataBean.CartListBean item : carBean.getData().getCartList()) {
            item.selectEdit = bool;
        }
        totalSelectOrder();
    }

    /**
     * 下单状态下的总数和价格的计算
     */
    private boolean totalSelectOrder() {
        int num = 0;
        int totalPrice = 0;
        boolean isSelectAll = true;
        for (CarBean.DataBean.CartListBean item : carBean.getData().getCartList()) {
            if (item.selectOrder) {
                num += item.getNumber();
                totalPrice += item.getNumber() * item.getRetail_price();
            } else {
                if (isSelectAll) {
                    isSelectAll = false;
                }
            }
        }
        String strAll = "全选($)";
        strAll = strAll.replace("$", String.valueOf(num));
        checkboxAll.setText(strAll);
        txtTotalPrice.setText("￥" + totalPrice);
        return isSelectAll;
    }

    private boolean totalSelectEdit() {
        int num = 0;
        int totalPrice = 0;
        boolean isSelectAll = true;
        for(CarBean.DataBean.CartListBean item:carBean.getData().getCartList()){
            if(item.selectEdit){
                num += item.getNumber();
                totalPrice += item.getNumber()*item.getRetail_price();
            }else{
                if(isSelectAll){
                    isSelectAll = false;
                }
            }
        }
        String strAll = "全选($)";
        strAll = strAll.replace("$",String.valueOf(num));
        checkboxAll.setText(strAll);
        txtTotalPrice.setText("￥"+totalPrice);
        return isSelectAll;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_edit:
                changeEdit();
                break;
            case R.id.txt_submit:
                submit();
                break;
            case R.id.checkbox_all:
                initAll();
                break;
        }
    }

    private void initAll() {

        Log.i("TAG", "checkboxall");
        boolean bool = checkboxAll.isChecked();
        if (isEdit) {
            updateGoodSelectStateEdit(!bool);
        } else {
            updateGoodSelectStateOrder(!bool);
        }

    }

    /**
     * 修改编辑和完成的状态
     */
    private void changeEdit() {
        if ("编辑".equals(txtEdit.getText().toString())) {
            txtEdit.setText("完成");
            txtSubmit.setText("删除所选");
            isEdit = true;
        } else if ("完成".equals(txtEdit.getText().toString())) {
            txtEdit.setText("编辑");
            txtSubmit.setText("下单");
            isEdit = false;
        }
        carListAdapter.setEditState(isEdit);
        carListAdapter.notifyDataSetChanged();
    }

    /**
     * 提交
     */
    private void submit() {
        if ("下单".equals(txtSubmit.getText().toString())) {
            //下单
        } else if ("删除所选".equals(txtSubmit.getText().toString())) {
            //删除购物车所选数据
        }
    }

}
