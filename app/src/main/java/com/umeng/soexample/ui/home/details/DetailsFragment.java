package com.umeng.soexample.ui.home.details;


import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseFragment;
import com.umeng.soexample.base.home.IDetails;
import com.umeng.soexample.model.DetailsGoodData;
import com.umeng.soexample.presenter.DetailsPresenter;
import com.umeng.soexample.utils.TxtUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends BaseFragment<DetailsPresenter> implements IDetails.View {


    private int id;
    private RecyclerView rlv_tab;
    private RecyclerView rlv_details;
    private List<DetailsGoodData.DataBeanX.DataBean> dataBean;
    private DetailsAdapter detailsAdapter;
    private List<DetailsTabBean.DataBean.CategoryListBean> list;
    private TabAdapter tabAdapter;

    public DetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getArguments().getInt("id");
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_details;
    }

    @Override
    protected DetailsPresenter createPrenter() {
        return new DetailsPresenter();
    }

    @Override
    protected void initView() {
        rlv_tab = getActivity().findViewById(R.id.rlv_tab);
        rlv_details = getActivity().findViewById(R.id.rlv_details);

        rlv_details.setLayoutManager(new GridLayoutManager(getActivity(),2));
        dataBean = new ArrayList<>();
        detailsAdapter = new DetailsAdapter(getActivity(), dataBean);
        rlv_details.setAdapter(detailsAdapter);

        rlv_tab.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<>();
        tabAdapter = new TabAdapter(getActivity(), list);
        rlv_tab.setAdapter(tabAdapter);
    }

    @Override
    protected void initData() {
        presenter.getDetailsGoodsData(id);
    }

    @Override
    public void getDetailsTabReturn(DetailsTabBean detailsTabBean) {
        list.clear();
        list.addAll(detailsTabBean.getData().getCategoryList());
        tabAdapter.notifyDataSetChanged();
    }

    @Override
    public void getDetailsGoodsReturn(DetailsGoodData detailsGoodData) {
        dataBean.clear();
        dataBean.addAll(detailsGoodData.getData().getData());
        detailsAdapter.notifyDataSetChanged();
    }
}
