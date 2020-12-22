package com.umeng.soexample.ui.fragment;


import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseFragment;
import com.umeng.soexample.interfaces.home.IShot;
import com.umeng.soexample.model.data.ShotItemData;
import com.umeng.soexample.model.data.ShotTabBean;
import com.umeng.soexample.presenter.ShotPresenter;
import com.umeng.soexample.ui.sort.SortAdapter;

import java.util.List;

import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.QTabView;
import q.rorbin.verticaltablayout.widget.TabView;

/**
 * A simple {@link Fragment} subclass.
 */
public class SortFragment extends BaseFragment<ShotPresenter> implements IShot.View {

    VerticalTabLayout vTab;
    ImageView imgItemSort;
    TextView tvImg;
    TextView tvItem;
    RecyclerView rlvShot;

    public SortFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_sort;
    }

    @Override
    protected ShotPresenter createPrenter() {
        return new ShotPresenter();
    }

    @Override
    protected void initView() {
        vTab = getActivity().findViewById(R.id.v_tab);
        imgItemSort = getActivity().findViewById(R.id.img_item_sort);
        tvImg = getActivity().findViewById(R.id.tv_img);
        tvItem = getActivity().findViewById(R.id.tv_item);
        rlvShot = getActivity().findViewById(R.id.rlv_shot);
    }

    @Override
    protected void initData() {
        presenter.getShotTabData();
    }

    @Override
    public void getShotTabReturn(ShotTabBean shotTabBean) {
        List<ShotTabBean.DataBean.CategoryListBean> categoryList = shotTabBean.getData().getCategoryList();
        for (int i = 0; i < categoryList.size(); i++) {
            Log.e("TAG", "getTabReturn: " + i + categoryList.get(i).getName());
        }
        vTab.setTabAdapter(new TabAdapter() {
            @Override
            public int getCount() {
                return categoryList.size();
            }

            @Override
            public ITabView.TabBadge getBadge(int position) {
                return null;
            }

            @Override
            public QTabView.TabIcon getIcon(int position) {
                return null;
            }

            @Override
            public QTabView.TabTitle getTitle(int position) {
                QTabView.TabTitle title = new QTabView.TabTitle.Builder()
                        .setContent(categoryList.get(position).getName())//设置数据   也有设置字体颜色的方法
                        .build();
                return title;
            }

            @Override
            public int getBackground(int position) {
                return 0;
            }
        });
        vTab.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tab, int position) {
                int id = categoryList.get(position).getId();
                if (id > 0) {
                    presenter.getShotItemData(id);
                }
            }

            @Override
            public void onTabReselected(TabView tab, int position) {

            }
        });
    }

    @Override
    public void getShotItemReturn(ShotItemData shotItemData) {
        List<ShotItemData.DataBean.CurrentCategoryBean.SubCategoryListBean> subCategoryList = shotItemData.getData().getCurrentCategory().getSubCategoryList();
        tvImg.setText(shotItemData.getData().getCurrentCategory().getFront_name());
        tvItem.setText(shotItemData.getData().getCurrentCategory().getName() + "分类");
        Glide.with(getActivity()).load(shotItemData.getData().getCurrentCategory().getWap_banner_url()).into(imgItemSort);
        rlvShot.setLayoutManager(new GridLayoutManager(getActivity(),3));
        SortAdapter sortAdapter = new SortAdapter(getActivity(), subCategoryList);
        rlvShot.setAdapter(sortAdapter);
    }
}
