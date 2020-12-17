package com.umeng.soexample.ui.home;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.tabs.TabLayout;
import com.umeng.soexample.R;
import com.umeng.soexample.ui.fragment.ClassifyFragment;
import com.umeng.soexample.ui.fragment.MyFragment;
import com.umeng.soexample.ui.fragment.ShoppingFragment;
import com.umeng.soexample.ui.fragment.SubjectFragment;

public class ShoppingActivity extends AppCompatActivity {

    private FrameLayout frame_layout;
    private TabLayout tab;
    private HomeFragment homeFragment;
    private SubjectFragment subjectFragment;
    private ClassifyFragment classifyFragment;
    private ShoppingFragment shoppingFragment;
    private MyFragment myFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        initView();
    }

    private void initView() {
        frame_layout = (FrameLayout) findViewById(R.id.frame_layout);
        tab = (TabLayout) findViewById(R.id.tab);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        homeFragment = new HomeFragment();
        subjectFragment = new SubjectFragment();
        classifyFragment = new ClassifyFragment();
        shoppingFragment = new ShoppingFragment();
        myFragment = new MyFragment();
        transaction.add(R.id.frame_layout,homeFragment)
                .add(R.id.frame_layout,subjectFragment)
                .add(R.id.frame_layout,classifyFragment)
                .add(R.id.frame_layout,shoppingFragment)
                .add(R.id.frame_layout,myFragment)
                .show(homeFragment)
                .hide(subjectFragment)
                .hide(classifyFragment)
                .hide(shoppingFragment)
                .hide(myFragment)
                .commit();

        tab.addTab(tab.newTab().setText("首页").setIcon(R.drawable.selector_home));
        tab.addTab(tab.newTab().setText("发现").setIcon(R.drawable.selector_subject));
        tab.addTab(tab.newTab().setText("分类").setIcon(R.drawable.selector_classify));
        tab.addTab(tab.newTab().setText("商城").setIcon(R.drawable.selector_shoping));
        tab.addTab(tab.newTab().setText("我的").setIcon(R.drawable.selector_my));

        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
                switch (tab.getPosition()){
                    case 0:
                        transaction1.show(homeFragment)
                                .hide(subjectFragment)
                                .hide(classifyFragment)
                                .hide(shoppingFragment)
                                .hide(myFragment);
                        break;
                    case 1:
                        transaction1.show(subjectFragment)
                                .hide(homeFragment)
                                .hide(classifyFragment)
                                .hide(shoppingFragment)
                                .hide(myFragment);
                        break;
                    case 2:
                        transaction1.show(classifyFragment)
                                .hide(homeFragment)
                                .hide(subjectFragment)
                                .hide(shoppingFragment)
                                .hide(myFragment);
                        break;
                    case 3:
                        transaction1.show(shoppingFragment)
                                .hide(homeFragment)
                                .hide(subjectFragment)
                                .hide(classifyFragment)
                                .hide(myFragment);
                        break;
                    case 4  :
                        transaction1.show(shoppingFragment)
                                .hide(homeFragment)
                                .hide(subjectFragment)
                                .hide(myFragment)
                                .hide(classifyFragment);
                        break;
                    case 5  :
                        transaction1.show(myFragment)
                                .hide(homeFragment)
                                .hide(subjectFragment)
                                .hide(classifyFragment)
                                .hide(shoppingFragment);
                        break;
                }
                transaction1.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}
