package com.umeng.soexample.ui.home.details;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.umeng.soexample.MainActivity;
import com.umeng.soexample.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailsActivity extends AppCompatActivity {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.img_menu)
    ImageView imgMenu;
    @BindView(R.id.img_share)
    ImageView imgShare;

    // 定义标题栏弹窗按钮
    private TitlePopup titlePopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        titlePopup = new TitlePopup(this, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        titlePopup.addAction(new ActionItem(this, "消息", R.drawable.mm_title_btn_compose_normal));
        titlePopup.addAction(new ActionItem(this, "首页", R.drawable.mm_title_btn_receiver_normal));
        titlePopup.addAction(new ActionItem(this, "搜索", R.drawable.mm_title_btn_keyboard_normal));
        titlePopup.addAction(new ActionItem(this, "购物车", R.drawable.mm_title_btn_qrcode_normal));
        titlePopup.setItemOnClickListener(new TitlePopup.OnItemOnClickListener() {
            @Override
            public void onItemClick(ActionItem item, int position) {
                switch (position){
                    case 0:
                        startActivity(new Intent(DetailsActivity.this, MainActivity.class));
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                }
            }
        });
    }

    @OnClick({R.id.img_back, R.id.img_menu, R.id.img_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.img_menu:
                titlePopup.show(view);
                break;
            case R.id.img_share:
                break;
        }
    }


}
