package com.umeng.soexample.ui.my;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseFragment;
import com.umeng.soexample.interfaces.IBasePresenter;
import com.umeng.soexample.ui.shop.FavoritesActivity;
import com.umeng.soexample.utils.ImageLoader;
import com.umeng.soexample.utils.SpUtils;
import com.umeng.soexample.utils.TxtUtils;

import java.util.ArrayList;
import java.util.List;

public class MyFragment extends BaseFragment implements View.OnClickListener {

    public static final int LOGIN_ME = 10001; //登录成功的回传值
    public static final int LOGIN_OUT_ME = 10002; //退出登录的回传

    private ImageView login_head;
    private TextView login_name;
    private ImageView jump_login;
    private GridView channel;
    private TextView txt_nickname;
    private TextView txt_mark;
    private ConstraintLayout layout_userinfo;

    public MyFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_my;
    }

    @Override
    protected IBasePresenter createPrenter() {
        return null;
    }

    @Override
    protected void initView() {
        login_head = getActivity().findViewById(R.id.login_head);
        login_name = getActivity().findViewById(R.id.login_name);
        jump_login = getActivity().findViewById(R.id.jump_login);
        txt_mark = getActivity().findViewById(R.id.txt_mark);
        txt_nickname = getActivity().findViewById(R.id.txt_nickname);
        layout_userinfo = getActivity().findViewById(R.id.layout_userinfo);
        channel = getActivity().findViewById(R.id.channel);

        layout_userinfo.setOnClickListener(this);
        jump_login.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        String token = SpUtils.getInstance().getString("token");
        if (!TextUtils.isEmpty(token)) {
            isLogin(true);
        } else {
            isLogin(false);
        }
        inintNineData();
    }

    private void isLogin(boolean bool) {
        if (bool) {
            login_name.setVisibility(View.GONE);
            txt_nickname.setVisibility(View.VISIBLE);
            txt_mark.setVisibility(View.VISIBLE);
            String username = SpUtils.getInstance().getString("username");
            String nickname = SpUtils.getInstance().getString("nickname");
            String avatar = SpUtils.getInstance().getString("avatar");
            String mark = SpUtils.getInstance().getString("mark");
            if (!TextUtils.isEmpty(nickname)) {
                txt_nickname.setText(nickname);
            } else {
                txt_nickname.setText(username);
            }
            ImageLoader.loadImage(avatar, login_head);
            TxtUtils.setTextView(txt_mark, mark);
        } else {
            login_name.setVisibility(View.VISIBLE);
            txt_nickname.setVisibility(View.GONE);
            txt_mark.setVisibility(View.GONE);
        }
    }

    /**
     * 打开登录页面
     */
    private void openLogin() {
        Intent intent = new Intent(mContext, LoginActivity.class);
        getActivity().startActivityForResult(intent, LOGIN_ME);
    }

    private void inintNineData() {
        int[] img = new int[]{
                R.mipmap.dingdan,
                R.mipmap.youhui,
                R.mipmap.lipin,
                R.mipmap.ic_menu_shoping_nor,
                R.mipmap.service,
                R.mipmap.welfare,
                R.mipmap.dingwei,
                R.mipmap.security,
                R.mipmap.service,
                R.mipmap.bangzhu,
                R.mipmap.yijian
        };
        String[] title = new String[]{
                "我的订单",
                "优惠券",
                "礼品卡",
                "我的收藏",
                "我的足迹",
                "会员福利",
                "地址管理",
                "账号安全",
                "联系客服",
                "帮助中心",
                "意见反馈"
        };
        List<MyBean> list = new ArrayList<>();
        for (int i = 0; i < title.length; i++) {
            MyBean myBean = new MyBean(title[i], img[i]);
            list.add(myBean);
        }
        MeAdapter meAdapter = new MeAdapter(getActivity(), list);
        channel.setAdapter(meAdapter);

        meAdapter.setMyList(new MeAdapter.MyList() {
            @Override
            public void setOnClick(int position) {
                //收藏页面
                if (position == 3) {
                    Intent intent = new Intent(getActivity(), FavoritesActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.jump_login:
                startActivity(new Intent(getActivity(),UserInfoDetailActivity.class));
                break;
        }
    }
}
