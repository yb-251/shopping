package com.umeng.soexample.ui.home;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseActivity;
import com.umeng.soexample.interfaces.home.IHotGood;
import com.umeng.soexample.model.home.GoodsHotBean;
import com.umeng.soexample.model.home.HotGoodListBean;
import com.umeng.soexample.presenter.home.HotGoodPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NewGoodsActivity extends BaseActivity<IHotGood.Presenter> implements IHotGood.View, View.OnClickListener {

    private static final String ASC = "asc";
    private static final String DESC = "desc";
    private static final String DEFAULT = "default";
    private static final String PRICE = "price";
    private static final String CATEGORY = "category";

    private ImageView img_hotgood;
    private TextView txt_info;
    private ConstraintLayout layout_info;
    private TextView txt_all;
    private TextView txt_price;
    private ImageView imgIcno;
    private LinearLayout layout_price;
    private TextView txt_sort;
    private ConstraintLayout layout_sort;
    private RecyclerView recy_goodList;

    private PopupWindow popupWindow;
    private List<HotGoodListBean.DataBeanX.FilterCategoryBean> filterCategory;
    private List<HotGoodListBean.DataBeanX.DataBean> data;
    private NewGoodsDetailsAdapter newGoodsDetailsAdapter;
    private List<HotGoodListBean.DataBeanX.GoodsListBean> goodsList;

    @Override
    protected int getLayout() {
        return R.layout.activity_new_goods;
    }

    protected void initView() {
        img_hotgood = (ImageView) findViewById(R.id.img_hotgood);
        txt_info = (TextView) findViewById(R.id.txt_info);
        layout_info = (ConstraintLayout) findViewById(R.id.layout_info);
        txt_all = (TextView) findViewById(R.id.txt_all);
        txt_price = (TextView) findViewById(R.id.txt_price);
        imgIcno = (ImageView) findViewById(R.id.img_icno);
        layout_price = (LinearLayout) findViewById(R.id.layout_price);
        txt_sort = (TextView) findViewById(R.id.txt_sort);
        layout_sort = (ConstraintLayout) findViewById(R.id.layout_sort);
        recy_goodList = (RecyclerView) findViewById(R.id.recy_goodList);

        setIconType(type);
        data = new ArrayList<>();
        newGoodsDetailsAdapter = new NewGoodsDetailsAdapter(this, data);
        recy_goodList.setLayoutManager(new GridLayoutManager(this,2));
        recy_goodList.setAdapter(newGoodsDetailsAdapter);

        layout_price.setOnClickListener(this);
        txt_all.setOnClickListener(this);
        txt_sort.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        presenter.getHotGood(initMap());
        presenter.getGoodsHotData();
    }

    @Override
    protected HotGoodPresenter createPrenter() {
        return new HotGoodPresenter();
    }

    private int isNew = 1;
    private int page = 1;
    private int size = 100;
    private String order=ASC;
    private String sort=DEFAULT;
    private int categoryId=0;
    private HashMap<String,String> initMap() {
        HashMap<String, String> map = new HashMap();
        map.put("isNew",String.valueOf(isNew));
        map.put("page",String.valueOf(page));
        map.put("size",String.valueOf(size));
        map.put("order",order);
        map.put("sort",sort);
        map.put("category",String.valueOf(categoryId));
        return map;
    }


    int type=0;
    @SuppressLint("ResourceType")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.layout_price:
                resetPriceState(txt_info);
                if (type==1){
                    type=2;
                    setIconType(type);
                    sort=PRICE;
                    order=DESC;
                    presenter.getHotGood(initMap());
                }else if (type==0){
                    type=1;
                    setIconType(type);
                    order=ASC;
                    sort=PRICE;
                    presenter.getHotGood(initMap());
                }else {
                    type=1;
                    setIconType(type);
                    order=ASC;
                    sort=PRICE;
                    presenter.getHotGood(initMap());
                }
                if (popupWindow!=null){
                    popupWindow.dismiss();
                }
                break;
            case R.id.txt_all:
                resetPriceState(txt_info);
                type=0;
                setIconType(type);
                categoryId=1005001;
                sort=DEFAULT;
                order=ASC;
                presenter.getHotGood(initMap());
                if (popupWindow!=null){
                    popupWindow.dismiss();
                }
                break;
            case R.id.txt_sort:
                resetPriceState(txt_info);
                type=0;
                setIconType(type);
                if (data!=null&&data.size()>0){
                    setPopw();
                }
                break;
        }
    }

    @SuppressLint("NewApi")
    private void setPopw() {
        Resources res = getResources();
        View inflate = LayoutInflater.from(this).inflate(R.layout.pop_item, null);
        popupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout li1 = inflate.findViewById(R.id.ll);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
        for (int i = 0; i < filterCategory.size(); i++) {
            View inflate1 = LayoutInflater.from(this).inflate(R.layout.popw_item, null);
            TextView tv = inflate1.findViewById(R.id.item_tv);
            tv.setText(filterCategory.get(i).getName());
            tv.setLayoutParams(layoutParams);
            tv.setGravity(Gravity.CENTER);
            int finalI = i;
            tv.setTag(i);
            inflate1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HotGoodListBean.DataBeanX.FilterCategoryBean filterCategoryBean = filterCategory.get(finalI);
                    categoryId=filterCategoryBean.getId();
                    HashMap<String, String> stringStringHashMap = new HashMap<>();
                    stringStringHashMap.put("categoryId",filterCategoryBean.getId()+"");
                    stringStringHashMap.put("isNew",1+"");
                   presenter.getHotGood(stringStringHashMap);
                    popupWindow.dismiss();
                }
            });
            Drawable drawable = res.getDrawable(R.drawable.stroke_black);
            tv.setBackground(drawable);
            li1.addView(tv);
        }
        popupWindow.showAsDropDown(li1,0,10);
    }

    /**
     * 重置条件选择的所有状态
     */
    @SuppressLint("ResourceType")
    private void resetPriceState(TextView txt_info){
        if (txt_info.equals(txt_all)){
            txt_info.setTextColor(Color.parseColor(getString(R.color.colorRed)));
            txt_price.setTextColor(Color.parseColor(getString(R.color.colorBlack)));
            txt_sort.setTextColor(Color.parseColor(getString(R.color.colorBlack)));
        }else if (txt_info.equals(txt_price)){
            txt_info.setTextColor(Color.parseColor(getString(R.color.colorRed)));
            txt_all.setTextColor(Color.parseColor(getString(R.color.colorBlack)));
            txt_sort.setTextColor(Color.parseColor(getString(R.color.colorBlack)));
        }else {
            txt_info.setTextColor(Color.parseColor(getString(R.color.colorRed)));
            txt_all.setTextColor(Color.parseColor(getString(R.color.colorBlack)));
            txt_price.setTextColor(Color.parseColor(getString(R.color.colorBlack)));
        }
    }

    private void setIconType(int type) {
        switch (type){
            case 0:
                Glide.with(this).load(R.mipmap.aa).into(imgIcno);
                break;
            case 1:
                Glide.with(this).load(R.mipmap.up).into(imgIcno);
                break;
            case 2:
                Glide.with(this).load(R.mipmap.dwon).into(imgIcno);
                break;
        }
    }

    @Override
    public void getHotGood(HotGoodListBean result) {
        HotGoodListBean.DataBeanX beanX = result.getData();
        filterCategory = result.getData().getFilterCategory();
        goodsList = result.getData().getGoodsList();
        this.data.clear();
        this.data.addAll(beanX.getData());
        newGoodsDetailsAdapter.notifyDataSetChanged();
    }

    @Override
    public void getGoodsHotData(GoodsHotBean goodsHotBean) {
        Glide.with(this).load(goodsHotBean.getData().getBannerInfo().getImg_url()).into(img_hotgood);
        txt_info.setText(goodsHotBean.getData().getBannerInfo().getName());
    }
}
