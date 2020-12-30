package com.umeng.soexample.ui.topic;

import android.content.Intent;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseActivity;
import com.umeng.soexample.interfaces.topic.ITopic;
import com.umeng.soexample.model.topic.TopicBean;
import com.umeng.soexample.model.topic.TopicRelated;
import com.umeng.soexample.model.topic.TopicdeBean;
import com.umeng.soexample.presenter.topic.TopicPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;

public class TopicActivity extends BaseActivity<TopicPresenter> implements ITopic.View {
    @BindView(R.id.rlv_de_to)
    RecyclerView rlvDeTo;
    @BindView(R.id.rlv_topic_rela)
    RecyclerView rlvTopicRela;

    @Override
    protected int getLayout() {
        return R.layout.activity_topicde;
    }

    @Override
    protected TopicPresenter createPrenter() {
        return new TopicPresenter();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent.hasExtra("id")) {
            int id = intent.getIntExtra("id", 0);
            presenter.getTopicde(id);
            presenter.getTopicRela(id);
        }
    }

    @Override
    public void getResult(TopicBean topicBean) {

    }

    @Override
    public void getResult(TopicdeBean topicdeBean) {
        TopicdeBean.DataBean data = topicdeBean.getData();
        initgetImage(data.getContent());
    }

    private void initgetImage(String content) {
        String img = "<img[\\s\\S]*?>";
        Pattern pattern = Pattern.compile(img);
        Matcher matcher = pattern.matcher(content);
        List<String> list = new ArrayList<>();
        while (matcher.find()) {
            String word = matcher.group();
            int start = word.indexOf("\"") + 1;
            int end = word.indexOf(".jpg");
            //判断图片的格式
            if (end > 0) {
                String url = word.substring(start, end);
                if (url != null) {
                    url = "http:" + url + ".jpg";
                    list.add(url);
                } else {
                    return;
                }
            } else {
                int end1 = word.indexOf(".png");
                String url = word.substring(start, end1);
                if (url != null) {
                    url = url + ".png";
                    list.add(url);
                } else {
                    return;
                }
            }
        }
        Log.e("111", "initgetImage: " + list);
        rlvDeTo.setLayoutManager(new LinearLayoutManager(this));
        RlvDeToAdapter rlvDeToAdapter = new RlvDeToAdapter(this, list);
        rlvDeTo.setAdapter(rlvDeToAdapter);
        rlvDeToAdapter.notifyDataSetChanged();
    }

    @Override
    public void getResult(TopicRelated topicRelated) {
        List<TopicRelated.DataBean> data = topicRelated.getData();
        rlvTopicRela.setLayoutManager(new LinearLayoutManager(this));
        TopicRelatedAdapter topicRelatedAdapter = new TopicRelatedAdapter(this, data);
        rlvTopicRela.setAdapter(topicRelatedAdapter);
        topicRelatedAdapter.notifyDataSetChanged();
    }

}
