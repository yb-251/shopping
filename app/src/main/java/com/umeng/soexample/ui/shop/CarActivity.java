package com.umeng.soexample.ui.shop;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
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
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseActivity;
import com.umeng.soexample.interfaces.shop.IShop;
import com.umeng.soexample.model.home.GoodDetailBean;
import com.umeng.soexample.model.shop.AddCarBean;
import com.umeng.soexample.model.shop.ShopAllData;
import com.umeng.soexample.presenter.shop.ShopPresenter;
import com.umeng.soexample.ui.home.EcoShopAdapter;
import com.umeng.soexample.ui.home.GoodAttributeAdapter;
import com.umeng.soexample.ui.home.GoodRcyProblemAdapter;
import com.umeng.soexample.ui.my.LoginActivity;
import com.umeng.soexample.utils.SpUtils;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class CarActivity extends BaseActivity<IShop.Presenter> implements IShop.View, View.OnClickListener {

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
    TextView txtNumber;
    FrameLayout layoutCar;
    @BindView(R.id.txt_buy)
    TextView txtBuy;
    TextView txtAddCar;
    @BindView(R.id.layout_shop)
    ConstraintLayout layoutShop;
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

    private PopupWindow popupWindow;
    GoodDetailBean goodDetailBean;
    public static final int RECOMMEND_CAR = 1000; //打开购物车的指令
    int buyNumber=1; //购买的数 量



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
    private GoodDetailBean.DataBeanX.InfoBean info;
    private Realm realm;

    @Override
    protected int getLayout() {
        realm = Realm.getDefaultInstance();
        return R.layout.activity_car;
    }

    @Override
    protected IShop.Presenter createPrenter() {
        return new ShopPresenter();
    }

    @Override
    protected void initView() {
        txtNumber = findViewById(R.id.txt_number);
        layoutCar = findViewById(R.id.layout_car);
        txtAddCar = findViewById(R.id.txt_addCar);
        goodsNumber = findViewById(R.id.goods_number);

        txtAddCar.setTag(1);
        txtAddCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tag = (int) txtAddCar.getTag();
                if (tag == 1){
                    initPopup();
                    txtAddCar.setTag(2);
                }
                if (tag == 2){
                    popupWindow.dismiss();
                    Toast.makeText(CarActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                    txtAddCar.setTag(1);
                }

            }
        });

        layoutCollect.setOnClickListener(this);
        layoutCar.setOnClickListener(this);
        txtBuy.setOnClickListener(this);
        txtAddCar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(!TextUtils.isEmpty(SpUtils.getInstance().getString("token"))){
            switch (v.getId()){
                case R.id.layout_collect:
                    Collection();
                    break;
                case R.id.layout_car:
                    openGoodCar();
                    break;
                case R.id.txt_buy:

                    break;
                case R.id.txt_addCar:
                    addCar();
                    break;
            }
        }else{
            Intent intent = new Intent(CarActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }

    /**
     * 添加进购物车
     */
    private void addCar(){
        if(buyNumber <= 0){
            Toast.makeText(this, getString(R.string.tips_buynumber), Toast.LENGTH_SHORT).show();
            return;
        }
        if(goodDetailBean.getData().getProductList().size() > 0){
            int goodsId = this.goodDetailBean.getData().getProductList().get(0).getGoods_id();
            int productid = this.goodDetailBean.getData().getProductList().get(0).getId();
            Map<String,String> map = new HashMap<>();
            map.put("goodsId",String.valueOf(goodsId));
            map.put("number",String.valueOf(buyNumber));
            map.put("productId",String.valueOf(productid));
            presenter.addGoodCar(map);
        }

    }

    /**
     * 打开购物车
     */
    private void openGoodCar(){
        setResult(RECOMMEND_CAR);
        finish();
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

        /**
         * 打开购物车
         */
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
        popupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(layoutShop, Gravity.NO_GRAVITY, 0, ints[1] - 570);
        initPopupView(inflate, popupWindow);
    }

    private void initPopupView(View view, PopupWindow popupWindow) {
        img_ppopup = view.findViewById(R.id.good_detail_img);
        TextView tv_less = view.findViewById(R.id.tv_less);
        TextView tv_number = view.findViewById(R.id.tv_number);
        TextView tv_pius = view.findViewById(R.id.tv_pius);
        ImageView cancel = view.findViewById(R.id.good_detail_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        tv_less.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buyNumber -= 1;
                if (buyNumber == 1) {
                    return;
                }
                tv_number.setText(String.valueOf(buyNumber));
            }
        });
        tv_pius.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buyNumber+=1;
                tv_number.setText(String.valueOf(buyNumber));
            }
        });
    }

    @Override
    public void getGoodDetail(GoodDetailBean goodDetailBean) {
        this.goodDetailBean = goodDetailBean;
        //h5 商品详情
        info = goodDetailBean.getData().getInfo();
        initBanner(goodDetailBean.getData().getGallery());
        initGoodDetail(info);
        initPopupWindow(goodDetailBean.getData());
        initAttribute(goodDetailBean.getData().getAttribute());
        initVebView(goodDetailBean.getData().getInfo().getGoods_desc());
        initGoodRcyProblem(goodDetailBean.getData().getIssue());
        initSeeSee(goodDetailBean.getData().getInfo());

        if(goodDetailBean.getData().getProductList().size() > 0){
            int num = goodDetailBean.getData().getProductList().get(0).getGoods_number();
            txtNumber.setText(String.valueOf(num));
            txtNumber.setVisibility(View.VISIBLE);
        }
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

    //添加购物车返回
    @Override
    public void addGoodCarReturn(AddCarBean addCarBean) {
        //添加成功以后跟新数量显示
        int number = addCarBean.getData().getCartTotal().getGoodsCount();
        txtNumber.setText(String.valueOf(number));
        txtNumber.setVisibility(View.VISIBLE);
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

    private void Collection() {
        Realms.getRealm(CarActivity.this).executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Favorites favorites = realm.createObject(Favorites.class);
                favorites.setName(info.getName());
                favorites.setPic(info.getList_pic_url());
                favorites.setPrice(String.valueOf(info.getRetail_price()));
                favorites.setTitle(info.getGoods_brief());

                Toast.makeText(CarActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
            }
        });
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
        while (matcher.find()) {
            String word = matcher.group();
            int start = word.indexOf("\"") + 1;
            int end = word.indexOf(".jpg");
            if (end > 0) {//如果是jpg格式的就截取jpg
                String url = word.substring(start, end);
                if (url != null) {
                    url = url + ".jpg";
                    list.add(url);
                } else {
                    return;
                }
            } else {
                int end1 = word.indexOf(".png");//如果是png格式的就截取png
                String url = word.substring(start, end1);
                if (url != null) {
                    url = url + ".png";
                    list.add(url);
                } else {
                    return;
                }
            }
        }

    }

}
