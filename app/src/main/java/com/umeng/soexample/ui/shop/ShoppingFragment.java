package com.umeng.soexample.ui.shop;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.umeng.soexample.R;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShoppingFragment extends Fragment {


    @BindView(R.id.iv_bg)
    ImageView ivBg;
    @BindView(R.id.tv_delete)
    TextView tvDelete;
    @BindView(R.id.tv_mobile)
    TextView tvMobile;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.tv_jh)
    TextView tvJh;
    @BindView(R.id.cb)
    CheckBox cb;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_settlement)
    TextView tvSettlement;

    public ShoppingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shopping, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {

    }

}
