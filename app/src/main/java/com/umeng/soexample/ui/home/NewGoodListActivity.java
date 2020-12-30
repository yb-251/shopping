package com.umeng.soexample.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseActivity;
import com.umeng.soexample.interfaces.home.IHotGood;
import com.umeng.soexample.model.home.GoodsHotBean;
import com.umeng.soexample.model.home.HotGoodListBean;
import com.umeng.soexample.presenter.home.HotGoodPresenter;
import com.umeng.soexample.ui.shop.CarActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class NewGoodListActivity extends BaseActivity<IHotGood.Presenter> implements IHotGood.View, View.OnClickListener{
    private static final String ASC = "asc";
    private static final String DESC = "desc";
    private static final String DEFAULT = "default";
    private static final String PRICE = "price";
    private static final String CATEGORY = "category";
    private static final String TAG = "NewGoodListActivity";
    //    isNew=1&page=1&size =1000&order=asc&sort=default&categoryId=0
    private int isNew = 1;
    private int page = 1;
    private int size = 1000;
    private String order;
    private String sort;
    private int categoryId = 0;


    @BindView(R.id.img_hotgood)
    ImageView imgNewGoodList;
    @BindView(R.id.txt_info)
    TextView newGoodListName;
    @BindView(R.id.txt_all)
    TextView newGoodListAll;
    @BindView(R.id.txt_price)
    TextView newGoodListPrice;
    @BindView(R.id.txt_sort)
    TextView newGoodListSort;
    @BindView(R.id.layout_price)
    LinearLayout newGoodListTabSelect;
    @BindView(R.id.recy_goodList)
    RecyclerView newGoodListRcy;
    @BindView(R.id.img_icno)
    ImageView newGoodSelected;
    private List<HotGoodListBean.DataBeanX.FilterCategoryBean> filterCategory;
    private List<HotGoodListBean.DataBeanX.GoodsListBean> data;

    @Override
    protected int getLayout() {
        return R.layout.activity_new_goods;
    }

    @Override
    protected IHotGood.Presenter createPrenter() {
        return new HotGoodPresenter();
    }

    @Override
    protected void initView() {
        newGoodListPrice.setTag(0);

        newGoodListAll.setOnClickListener(this);
        newGoodListPrice.setOnClickListener(this);
        newGoodListSort.setOnClickListener(this);
    }

    //    https://cdplay.cn/api/goods/list?isNew=1&page=1&size =1000&order=asc&sort=default&categoryId=0
    private Map<String, String> getParameter(String older, String sort) {
        Map<String, String> map = new HashMap<>();
        map.put("isNew", String.valueOf(isNew));
        map.put("page", String.valueOf(page));
        map.put("size", String.valueOf(size));
        map.put("order", older);
        map.put("sort", sort);
        map.put("categoryId", String.valueOf(categoryId));
        return map;
    }
    private Map<String, String> getParameter(String categoryId) {
        Map<String, String> map = new HashMap<>();
        map.put("isNew", String.valueOf(isNew));
        map.put("page", String.valueOf(page));
        map.put("size", String.valueOf(size));
        map.put("order", ASC);
        map.put("sort", DEFAULT);
        map.put("categoryId", categoryId);
        return map;
    }

    private Map<String, String> getParameters() {
        Map<String, String> map = new HashMap<>();
        map.put("isNew", String.valueOf(isNew));
        map.put("page", String.valueOf(page));
        map.put("size", String.valueOf(size));
        return map;
    }

    @SuppressLint("ResourceType")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_price:
                int tag = (int) newGoodListPrice.getTag();
                switch (tag) {
                    case 0:
                        initSelectUpAndDown();
                        selectUp();
                        newGoodListPrice.setTag(1);
                        order = ASC;
                        break;
                    case 1:
                        initSelectUpAndDown();
                        selectDown();
                        newGoodListPrice.setTag(0);
                        order = DESC;
                        break;
                }
                sort = PRICE;
                Map<String, String> map = getParameter(order, sort);
                presenter.getHotGood(map);
                break;
            case R.id.txt_all:
                initSelectUpAndDown();
                newGoodListAll.setTextColor(Color.parseColor(getString(R.color.colorRed)));
                Map<String, String> parameters = getParameters();
                presenter.getHotGood(parameters);
                break;
            case R.id.txt_sort:
                initSelectUpAndDown();
                newGoodListSort.setTextColor(Color.parseColor(getString(R.color.colorRed)));
                Map<String, String> sortMap = getParameters();
                presenter.getGoodsHotData();

                if (data != null && data.size() > 0) {
                    popup();
                }
                break;
        }
    }

    private void popup() {
        List<HotGoodListBean.DataBeanX.FilterCategoryBean> list = new ArrayList<>();
        View popup = LayoutInflater.from(this).inflate(R.layout.layout_newgood_popup, null);
        LinearLayout ll = popup.findViewById(R.id.ll);
        RecyclerView rcy = popup.findViewById(R.id.rcy_popup);
        PopupWindow popupWindow = new PopupWindow(popup, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.setOutsideTouchable(true);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
        for (int i = 0; i < filterCategory.size(); i++) {
            HotGoodListBean.DataBeanX.FilterCategoryBean filterCategoryBean = filterCategory.get(i);
            list.add(filterCategoryBean);
            String name = filterCategory.get(i).getName();
            int id = filterCategory.get(i).getId();
            Log.d(TAG, "popup: " + id);
            TextView rcy_popup = (TextView) LayoutInflater.from(NewGoodListActivity.this).inflate(R.layout.popup_item, null);
//            TextView tv_popup_name = inflate.findViewById(R.id.tv_popup_name);
            rcy_popup.setText(name);
            rcy_popup.setLayoutParams(layoutParams);
            ll.addView(rcy_popup);
            rcy_popup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id1 = filterCategoryBean.getId();
                    Map<String, String> parameters = getParameter(String.valueOf(id1));
                    presenter.getHotGood(parameters);
                    popupWindow.dismiss();
                }
            });

        }
//        rcy.setLayoutManager(new LinearLayoutManager(this));
//        PopupAdapter popupAdapter = new PopupAdapter(this, list);
//        rcy.setAdapter(popupAdapter);
//        popupAdapter.notifyDataSetChanged();
//        ll.addView(rcy);
        popupWindow.showAsDropDown(newGoodListTabSelect);
    }

    @SuppressLint("ResourceType")
    private void selectDown() {
        newGoodListPrice.setTextColor(Color.parseColor(getString(R.color.colorRed)));
        newGoodSelected.setImageResource(R.mipmap.dwon);
    }

    @SuppressLint("ResourceType")
    private void selectUp() {
        newGoodListPrice.setTextColor(Color.parseColor(getString(R.color.colorRed)));
        newGoodSelected.setImageResource(R.mipmap.up);

    }

    @SuppressLint("ResourceType")
    private void initSelectUpAndDown() {
        newGoodListPrice.setTag(0);
        newGoodSelected.setImageResource(R.mipmap.aa);
        newGoodListPrice.setTextColor(Color.parseColor(getString(R.color.colorBlack)));
        newGoodListSort.setTextColor(Color.parseColor(getString(R.color.colorBlack)));
        newGoodListAll.setTextColor(Color.parseColor(getString(R.color.colorBlack)));
    }

    @Override
    protected void initData() {
        presenter.getGoodsHotData();
        Map<String, String> parameters = getParameters();
        presenter.getHotGood(parameters);
    }

    @Override
    public void getGoodsHotData(GoodsHotBean goodsHotBean) {
        Glide.with(this).load(goodsHotBean.getData().getBannerInfo().getImg_url()).into(imgNewGoodList);
        newGoodListName.setText(goodsHotBean.getData().getBannerInfo().getName());
    }

    @Override
    public void getHotGood(HotGoodListBean result) {
        if (result != null) {
            data = result.getData().getGoodsList();
            filterCategory = result.getData().getFilterCategory();
            initAllData(data);
            List<HotGoodListBean.DataBeanX.GoodsListBean> goodsList = result.getData().getGoodsList();
            initBindData(goodsList);
        }
    }

    private void initAllData(List<HotGoodListBean.DataBeanX.GoodsListBean> data) {
        newGoodListRcy.setLayoutManager(new GridLayoutManager(this, 2));
        NewGoodOlderAdapter newGoodAllAdapter = new NewGoodOlderAdapter(this, data);
        newGoodListRcy.setAdapter(newGoodAllAdapter);
        newGoodAllAdapter.notifyDataSetChanged();

        newGoodAllAdapter.setOnItemClickListener(new BrandAdapter.OnItemClickListener() {
            @Override
            public void onClick(int pos) {
                Intent intent = new Intent(NewGoodListActivity.this, CarActivity.class);
                intent.putExtra("goodid",data.get(pos).getId());
                startActivity(intent);
            }
        });
    }

    private void initBindData(List<HotGoodListBean.DataBeanX.GoodsListBean> goodsList) {
        newGoodListRcy.setLayoutManager(new GridLayoutManager(this, 2));
        NewGoodOlderAdapter newGoodOlderAdapter = new NewGoodOlderAdapter(this, goodsList);
        newGoodListRcy.setAdapter(newGoodOlderAdapter);
        newGoodOlderAdapter.notifyDataSetChanged();
        newGoodOlderAdapter.setOnItemClickListener(new BrandAdapter.OnItemClickListener() {
            @Override
            public void onClick(int pos) {
                Intent intent = new Intent(NewGoodListActivity.this, CarActivity.class);
                intent.putExtra("goodid",data.get(pos).getId());
                startActivity(intent);
            }
        });
    }



    class PopupAdapter extends RecyclerView.Adapter {
        Context context;
        List<HotGoodListBean.DataBeanX.FilterCategoryBean> list;

        public PopupAdapter(Context context, List<HotGoodListBean.DataBeanX.FilterCategoryBean> list) {
            this.context = context;
            this.list = list;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_text_popup, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            HotGoodListBean.DataBeanX.FilterCategoryBean filterCategoryBean = list.get(position);
            String name = filterCategoryBean.getName();
            ViewHolder vh = (ViewHolder) holder;
            vh.mTvText.setText(name);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            public View rootView;
            public TextView mTvText;

            public ViewHolder(View rootView) {
                super(rootView);
                this.rootView = rootView;
                this.mTvText = (TextView) rootView.findViewById(R.id.tv_text);
            }

        }
    }

}