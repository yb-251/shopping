package com.umeng.soexample.ui.shop;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseActivity;
import com.umeng.soexample.interfaces.IBasePresenter;

import butterknife.BindView;

public class SubmitActivity extends BaseActivity {

    @BindView(R.id.tv_coupon)
    TextView tvCoupon;
    @BindView(R.id.img_couponRt)
    ImageView imCouponRt;
    @BindView(R.id.tv_couponNumber)
    TextView tvCouponNumber;
    @BindView(R.id.rcy_order_rcy)
    RecyclerView rcyOrderRcy;
    @BindView(R.id.txt_actual_payment)
    TextView txtActualPayment;
    @BindView(R.id.txt_go_pay)
    TextView txtGoPay;

    @BindView(R.id.total_products)//商品合计
            ConstraintLayout layoutTotal;
    @BindView(R.id.cost)//运费
            ConstraintLayout layoutCost;
    @BindView(R.id.coupon)//优惠券
            ConstraintLayout layoutCoupon;
    private TextView totalLt,totalRt,costLt,costRt,couponLt,couponRt;
    @Override
    protected int getLayout() {
        return R.layout.activity_submit;
    }

    @Override
    protected IBasePresenter createPrenter() {
        return null;
    }

    @Override
    protected void initView() {
        totalLt = layoutTotal.findViewById(R.id.tv_order_lt);
        totalRt = layoutTotal.findViewById(R.id.tv_order_rt);
        costLt = layoutCost.findViewById(R.id.tv_order_lt);
        costRt = layoutCost.findViewById(R.id.tv_order_rt);
        couponLt = layoutCoupon.findViewById(R.id.tv_order_lt);
        couponRt = layoutCoupon.findViewById(R.id.tv_order_rt);
//        totalLt.setText("1");
//        costLt.setText("2");
//        couponLt.setText("3");
    }



    @Override
    protected void initData() {

    }
}