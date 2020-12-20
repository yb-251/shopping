package com.umeng.soexample.ui.home;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseAdapter;
import com.umeng.soexample.base.BaseFragment;
import com.umeng.soexample.base.home.IHome;
import com.umeng.soexample.model.HomeData;
import com.umeng.soexample.presenter.HomePresenter;
import com.umeng.soexample.ui.adapter.BrandAdapter;
import com.umeng.soexample.ui.adapter.CategoryListAdapter;
import com.umeng.soexample.ui.adapter.HotGoodsAdapter;
import com.umeng.soexample.ui.adapter.NewGoodsAdapter;
import com.umeng.soexample.ui.adapter.TopicAdapter;
import com.umeng.soexample.ui.home.activity.CarActivity;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment<HomePresenter> implements IHome.View, View.OnClickListener {


    private Banner banner;
    private LinearLayout layout_tab;
    private TextView tv_brand_title;
    private RecyclerView rlv_brand;
    private TextView tv_newgood_title;
    private RecyclerView rlv_newgood;
    private ImageView img_channel;
    private TextView txt_channel;
    private List<HomeData.DataBean.BrandListBean> brandList;
    private BrandAdapter brandAdapter;
    private List<HomeData.DataBean.NewGoodsListBean> newGoodsListBean;
    private NewGoodsAdapter newGoodsAdapter;
    private TextView tv_hotgoods_title;
    private RecyclerView rlv_hotgoods;
    private TextView tv_topic_title;
    private RecyclerView rlv_topic;
    private TextView tv_category_title;
    private LinearLayout rlv_category;
    private List<HomeData.DataBean.HotGoodsListBean> hotGoodsListBean;
    private HotGoodsAdapter hotGoodsAdapter;
    private List<HomeData.DataBean.TopicListBean> topicListBean;
    private TopicAdapter topicAdapter;
    private ImageView img_seach;
    private TextView search;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected HomePresenter createPrenter() {
        return new HomePresenter();
    }

    @Override
    protected void initView() {
        banner = getActivity().findViewById(R.id.banner);
        layout_tab = getActivity().findViewById(R.id.layout_tab);
        tv_brand_title = getActivity().findViewById(R.id.tv_brand_title);
        rlv_brand = getActivity().findViewById(R.id.rlv_brand);
        tv_newgood_title = getActivity().findViewById(R.id.tv_newgood_title);
        tv_hotgoods_title = getActivity().findViewById(R.id.tv_hotgoods_title);
        rlv_newgood = getActivity().findViewById(R.id.rlv_newgood);
        rlv_hotgoods = getActivity().findViewById(R.id.rlv_hotgoods);
        rlv_topic = getActivity().findViewById(R.id.rlv_topic);
        rlv_category = getActivity().findViewById(R.id.rlv_category);
        img_seach = getActivity().findViewById(R.id.img_seach);
        search = getActivity().findViewById(R.id.search);

        String s = tv_brand_title.getText().toString();
        Log.d("tag", "initView: "+s);
        rlv_brand.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        brandList = new ArrayList<>();
        brandAdapter = new BrandAdapter(getActivity(), brandList);
        rlv_brand.setAdapter(brandAdapter);

        rlv_newgood.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        newGoodsListBean = new ArrayList<>();
        newGoodsAdapter = new NewGoodsAdapter(getActivity(), newGoodsListBean);
        rlv_newgood.setAdapter(newGoodsAdapter);

        rlv_hotgoods.setLayoutManager(new LinearLayoutManager(getActivity()));
        hotGoodsListBean = new ArrayList<>();
        hotGoodsAdapter = new HotGoodsAdapter(getActivity(), hotGoodsListBean);
        rlv_hotgoods.setAdapter(hotGoodsAdapter);

        rlv_topic.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        topicListBean = new ArrayList<>();
        topicAdapter = new TopicAdapter(getActivity(), topicListBean);
        rlv_topic.setAdapter(topicAdapter);

        tv_brand_title.setOnClickListener(this);
        tv_newgood_title.setOnClickListener(this);
    }    @Override
    protected void initData() {
        presenter.getHomeData();
    }

    @Override
    public void getHomeReturn(HomeData homeData) {
        if (homeData != null) {
            initBanner(homeData.getData().getBanner());
            initChannel(homeData.getData().getChannel());
            initBrand(homeData.getData().getBrandList());
            initNewGoods(homeData.getData().getNewGoodsList());
            initHotGoods(homeData.getData().getHotGoodsList());
            initTopic(homeData.getData().getTopicList());
            initCategory(homeData.getData().getCategoryList());
        }
    }

    private void initCategory(List<HomeData.DataBean.CategoryListBean> list) {
        for (int i = 0; i < list.size(); i++) {
            View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.item_category, null);
            TextView tv_category_title = inflate.findViewById(R.id.txt_category_title);
            RecyclerView recy_category = inflate.findViewById(R.id.recy_category);
            List<HomeData.DataBean.CategoryListBean.GoodsListBean> listBean = list.get(i).getGoodsList();
            recy_category.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            tv_category_title.setText(list.get(i).getName());
            CategoryListAdapter categoryListAdapter = new CategoryListAdapter(getActivity(), listBean);
            recy_category.setAdapter(categoryListAdapter);
            rlv_category.addView(inflate);

            categoryListAdapter.setOnItemClickListener(new CategoryListAdapter.OnItemClickListener() {
                @Override
                public void onClick(int pos) {
                    Intent intent = new Intent(getActivity(), CarActivity.class);
                    intent.putExtra("goodid",Integer.parseInt(listBean.get(pos).getId()));
                    getActivity().startActivity(intent);
                }
            });

        }
    }

    private void initTopic(List<HomeData.DataBean.TopicListBean> list) {
        topicListBean.clear();
        topicListBean.addAll(list);
        topicAdapter.notifyDataSetChanged();
    }

    private void initHotGoods(List<HomeData.DataBean.HotGoodsListBean> list) {
        hotGoodsListBean.clear();
        hotGoodsListBean.addAll(list);
        hotGoodsAdapter.notifyDataSetChanged();
    }

    private void initBrand(List<HomeData.DataBean.BrandListBean> list) {
        brandList.clear();
        brandList.addAll(list);
        brandAdapter.notifyDataSetChanged();
    }

    private void initNewGoods(List<HomeData.DataBean.NewGoodsListBean> list) {
        newGoodsListBean.clear();
        newGoodsListBean.addAll(list);
        newGoodsAdapter.notifyDataSetChanged();
    }

    private void initChannel(List<HomeData.DataBean.ChannelBean> list) {
        layout_tab.removeAllViews();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT,1);
        for(HomeData.DataBean.ChannelBean item:list){
            View channel = LayoutInflater.from(getContext()).inflate(R.layout.layout_channel,layout_tab,false);
            ImageView img = channel.findViewById(R.id.img_channel);
            TextView txtChannel = channel.findViewById(R.id.txt_channel);
            Glide.with(getActivity()).load(item.getIcon_url()).into(img);
            txtChannel.setText(item.getName());
            txtChannel.setGravity(Gravity.CENTER);
            channel.setLayoutParams(params);
            layout_tab.addView(channel);
            channel.setTag(item);
            channel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int curId = ((HomeData.DataBean.ChannelBean)v.getTag()).getCategoryid();
                    Intent intent = new Intent(mContext,ChannelListActivity.class);
                    intent.putExtra("categoryid",curId);
                    startActivity(intent);
                }
            });
        }

    }

    private void initBanner(List<HomeData.DataBean.BannerBean> list) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                banner.setImages(list)
                        .setImageLoader(new ImageLoader() {
                            @Override
                            public void displayImage(Context context, Object path, ImageView imageView) {
                                HomeData.DataBean.BannerBean bannerBean = (HomeData.DataBean.BannerBean) path;
                                Glide.with(context).load(bannerBean.getImage_url()).into(imageView);
                            }
                        }).start();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_brand_title:
                startActivity(new Intent(getActivity(), BrandDetailsActivity.class));
                break;
            case R.id.tv_newgood_title:
                startActivity(new Intent(getActivity(), NewGoodsActivity.class));
                break;
        }
    }
}
