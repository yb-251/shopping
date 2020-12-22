package com.umeng.soexample.ui.home.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseActivity;
import com.umeng.soexample.interfaces.home.IShop;
import com.umeng.soexample.model.data.GoodDetailBean;
import com.umeng.soexample.model.data.ShopAllData;
import com.umeng.soexample.presenter.ShopPresenter;
import com.umeng.soexample.ui.adapter.EcoShopAdapter;
import com.umeng.soexample.ui.adapter.GoodAttributeAdapter;
import com.umeng.soexample.ui.adapter.GoodRcyProblemAdapter;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CarActivity extends BaseActivity<IShop.Presenter> implements IShop.View {

    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.txt_assess)
    TextView txtAssess;
    @BindView(R.id.scrollView)
    NestedScrollView scrollView;
    @BindView(R.id.img_collect)
    ImageView imgCollect;
    @BindView(R.id.layout_collect)
    FrameLayout layoutCollect;
    @BindView(R.id.img_car)
    ImageView imgCar;
    @BindView(R.id.txt_number)
    TextView txtNumber;
    @BindView(R.id.layout_car)
    FrameLayout layoutCar;
    @BindView(R.id.txt_buy)
    TextView txtBuy;
    @BindView(R.id.txt_addCar)
    TextView txtAddCar;
    @BindView(R.id.layout_shop)
    ConstraintLayout layoutShop;
    @BindView(R.id.goods_number)
    ConstraintLayout goodsNumber;
    @BindView(R.id.tv_return)
    TextView tvReturn;
    @BindView(R.id.tv_refund)
    TextView tvRefund;
    @BindView(R.id.tv_postage)
    TextView tvPostage;
    @BindView(R.id.postage)
    LinearLayout postage;
    @BindView(R.id.tv_good_names)
    TextView tvGoodNames;
    @BindView(R.id.tv_good_descs)
    TextView tvGoodDescs;
    @BindView(R.id.tv_good_prices)
    TextView tvGoodPrices;
    @BindView(R.id.rel_goods)
    RelativeLayout relGoods;
    @BindView(R.id.assess)
    ConstraintLayout assess;
    @BindView(R.id.tv_attribute)
    TextView tvAttribute;
    @BindView(R.id.rcy_attribute)
    RecyclerView rcyAttribute;
    @BindView(R.id.tv_people_see)
    TextView tvPeopleSee;
    @BindView(R.id.good_rcy_see_see)
    RecyclerView goodRcySeeSee;
    @BindView(R.id.tv_problem)
    TextView tvProblem;
    @BindView(R.id.good_rcy_problem)
    RecyclerView goodRcyProblem;
    private ImageView img_ppopup;


    private String h5 = "<html>\n" +
            "            <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no\"/>\n" +
            "            <head>\n" +
            "                <style>\n" +
            "                    p{\n" +
            "                        margin:0px;\n" +
            "                    }\n" +
            "                    img{\n" +
            "                        width:100%;\n" +
            "                        height:auto;\n" +
            "                    }\n" +
            "                </style>\n" +
            "            </head>\n" +
            "            <body>\n" +
            "                word\n" +
            "            </body>\n" +
            "        </html>";

    private long category_id;
    private String page;
    private String size;
    private long goods_sn;
    private ProgressBar ProgressBar;

    @Override
    protected int getLayout() {
        return R.layout.activity_car;
    }

    @Override
    protected IShop.Presenter createPrenter() {
        return new ShopPresenter();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent.hasExtra("goodid")) {
            int id = getIntent().getIntExtra("goodid", 0);
            if (id > 0) {
                presenter.getGoodDetail(id);
                presenter.resultGoodSeeSeeReturn(String.valueOf(id),page,size);
            } else {
                tips(getString(R.string.tips_error_goodid));
            }
        }

        goodsNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPopup();
            }
        });
    }

    private void initPopup() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.popu_good_detail, null);
        int[] ints = new int[2];
        layoutShop.getLocationOnScreen(ints);
        PopupWindow popupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(layoutShop, Gravity.NO_GRAVITY, 0, ints[1] - 570);
        initPopupView(inflate, popupWindow);
    }

    private void initPopupView(View view, PopupWindow popupWindow) {
        img_ppopup = view.findViewById(R.id.good_detail_img);
        ImageView cancel = view.findViewById(R.id.good_detail_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    @Override
    public void getGoodDetail(GoodDetailBean goodDetailBean) {
        //h5 商品详情
        initBanner(goodDetailBean.getData().getGallery());
        initGoodDetail(goodDetailBean.getData().getInfo());
        initPopupWindow(goodDetailBean.getData());
        initAttribute(goodDetailBean.getData().getAttribute());
        initVebView(goodDetailBean.getData().getInfo().getGoods_desc());
        initGoodRcyProblem(goodDetailBean.getData().getIssue());
        initSeeSee(goodDetailBean.getData().getInfo());

    }

    @Override
    public void getGoodSeeSeeReturn(ShopAllData result) {
        if (result != null) {
            List<ShopAllData.DataBeanX.DataBean> data = result.getData().getData();
            goodRcySeeSee.setLayoutManager(new GridLayoutManager(this, 2));
            EcoShopAdapter ecoShopAdapter = new EcoShopAdapter(this, data);
            goodRcySeeSee.setAdapter(ecoShopAdapter);
            ecoShopAdapter.notifyDataSetChanged();
        }
    }

    private void initGoodRcyProblem(List<GoodDetailBean.DataBeanX.IssueBean> issue) {
        if (issue != null) {
            goodRcyProblem.setLayoutManager(new LinearLayoutManager(this));
            GoodRcyProblemAdapter goodRcyProblemAdapter = new GoodRcyProblemAdapter(this, issue);
            goodRcyProblem.setAdapter(goodRcyProblemAdapter);
            goodRcyProblemAdapter.notifyDataSetChanged();
        }
    }

    private void initGoodDetail(GoodDetailBean.DataBeanX.InfoBean info) {
        String name = info.getName();
        String goods_brief = info.getGoods_brief();
        double retail_price = info.getRetail_price();
        tvGoodNames.setText(name);
        tvGoodDescs.setText(goods_brief);
        tvGoodPrices.setText(String.valueOf("￥ " + retail_price));
    }

    private void initSeeSee(GoodDetailBean.DataBeanX.InfoBean info) {
        category_id = info.getCategory_id();
        goods_sn = info.getGoods_sn();
        page = "1";
        size = "100";
    }

    private void initPopupWindow(GoodDetailBean.DataBeanX data) {

    }

    private void initAttribute(List<GoodDetailBean.DataBeanX.AttributeBean> attribute) {
        rcyAttribute.setLayoutManager(new LinearLayoutManager(this));
        GoodAttributeAdapter goodAttributeAdapter = new GoodAttributeAdapter(this, attribute);
        rcyAttribute.setAdapter(goodAttributeAdapter);
        goodAttributeAdapter.notifyDataSetChanged();
    }

    private void initBanner(List<GoodDetailBean.DataBeanX.GalleryBean> list) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                banner.setImages(list).setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        GoodDetailBean.DataBeanX.GalleryBean path1 = (GoodDetailBean.DataBeanX.GalleryBean) path;
                        Glide.with(context).load(path1.getImg_url()).into(imageView);
                    }
                }).start();
            }
        });
    }

    /**
     * 商品详情数据  h5
     *
     * @param webData
     */
    private void initVebView(String webData) {
        getHtmlImgs(webData);
        String content = h5.replace("word", webData);
        Log.i("TAG", content);
        webView.loadDataWithBaseURL("about:blank", content, "text/html", "utf-8", null);
        webView.setWebViewClient(new WebViewClient());
    }

    private void getHtmlImgs(String content){
        String img = "<img[\\s\\S]*?>";
        Pattern pattern = Pattern.compile(img);
        Matcher matcher = pattern.matcher(content);
        List<String> list = new ArrayList<>();
        while(matcher.find()){
            String word = matcher.group();
            int start = word.indexOf("\"")+1;
            int end = word.indexOf(".jpg");
            String url = word.substring(start,end);
            url = url +".jpg";
            list.add(url);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
