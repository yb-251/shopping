package com.umeng.soexample.text;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.umeng.soexample.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Demo1Activity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView rlv;
    private ListAdapter listAdapter;
    private List<ListData.DataBean> list;
    private Button btn_ok;
    private List<ListData.DataBean> data;
    private ArrayList<Boolean> selected;
    private ArrayList<Boolean> selected2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo1);
        initView();
        initData();

    }

    private void initView() {
        rlv = (RecyclerView) findViewById(R.id.rlv);
        rlv.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        listAdapter = new ListAdapter(this, list);
        rlv.setAdapter(listAdapter);


        selected2 = new ArrayList<>();

            listAdapter.setOnItemClickListener(new ListAdapter.OnItemClickListener() {
                @Override
                public void onClick(int pos, boolean bool,CompoundButton compoundButton) {
                    Toast.makeText(Demo1Activity.this, "111", Toast.LENGTH_SHORT).show();
                    if (!selected.get(pos).equals(bool) ){
                        selected2.add(bool);
                    }else {
                        selected2.remove(bool);
                    }
                }
            });

        btn_ok = (Button) findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(this);
    }

    private void initData() {
        new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(ListApi.sUrl)
                .build()
                .create(ListApi.class)
                .getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ListData>() {



                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(ListData listData) {
                        data = listData.getData();
                        list.clear();
                        list.addAll(data);
                        selected = new ArrayList<>();
                        for (int i = 0; i <data.size() ; i++) {
                            selected.add(data.get(i).isSelect());
                        }
                        listAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ok:
                initLog();
                break;
        }
    }

    private void initLog() {
            Log.d("tag", "initLog: "+selected2.toString());

    }
}
