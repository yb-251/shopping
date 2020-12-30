package com.umeng.soexample.ui.shop;

import android.os.Bundle;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseActivity;
import com.umeng.soexample.interfaces.IBasePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

public class FavoritesActivity extends BaseActivity {
    @BindView(R.id.recycler_favorites)
    RecyclerView recyclerFavorites;
    private List<Favorites> list;
    private FavoritesAdapter favoritesAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_favorites;
    }

    @Override
    protected IBasePresenter createPrenter() {
        return null;
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        recyclerFavorites.setLayoutManager(new LinearLayoutManager(this));
        favoritesAdapter = new FavoritesAdapter(this,list);
        recyclerFavorites.setAdapter(favoritesAdapter);
        //recyclerFavorites.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        RealmResults<Favorites> all = Realms.getRealm(FavoritesActivity.this).where(Favorites.class).findAll();
        //list.clear();
        list.addAll(all);
        favoritesAdapter.notifyDataSetChanged();


    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
