package com.umeng.soexample.ui.topic;


import android.content.Intent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseAdapter;
import com.umeng.soexample.base.BaseFragment;
import com.umeng.soexample.interfaces.topic.ITopic;
import com.umeng.soexample.model.topic.TopicBean;
import com.umeng.soexample.model.topic.TopicRelated;
import com.umeng.soexample.model.topic.TopicdeBean;
import com.umeng.soexample.presenter.topic.TopicPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopicFragment extends BaseFragment<TopicPresenter> implements ITopic.View {

    RecyclerView rlvTopicFrag;
    TextView first;
    TextView next;
    NestedScrollView topicNest;
    private ArrayList<TopicBean.DataBeanX.DataBean> tolists;
    private Topic_frag_Adapter topic_frag_adapter;

    public TopicFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getLayout() {
        return R.layout.fragment_subject;
    }

    @Override
    protected TopicPresenter createPrenter() {
        return new TopicPresenter();
    }

    @Override
    protected void initView() {
        rlvTopicFrag = getActivity().findViewById(R.id.rlv_topic_frag);
        first = getActivity().findViewById(R.id.first);
        next = getActivity().findViewById(R.id.next);
        topicNest = getActivity().findViewById(R.id.topic_nest);

        tolists = new ArrayList<>();
        rlvTopicFrag.setLayoutManager(new LinearLayoutManager(getActivity()));
        topic_frag_adapter = new Topic_frag_Adapter(getActivity(), tolists);
        rlvTopicFrag.setAdapter(topic_frag_adapter);

        //下一页
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tolists.clear();
                presenter.getTopic(2);
                //返回顶部
                topicNest.fullScroll(ScrollView.FOCUS_UP);
                topic_frag_adapter.notifyDataSetChanged();
            }
        });
        //上一页
        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tolists.clear();
                presenter.getTopic(1);
                //返回顶部
                topicNest.fullScroll(ScrollView.FOCUS_UP);
                topic_frag_adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void initData() {
        presenter.getTopic(1);
    }

    @Override
    public void getResult(TopicBean topicBean) {
        List<TopicBean.DataBeanX.DataBean> data = topicBean.getData().getData();
        tolists.addAll(data);
        topic_frag_adapter.notifyDataSetChanged();

        topic_frag_adapter.addListClick(new BaseAdapter.IListClick() {
            @Override
            public void itemClick(int pos) {
                Intent intent = new Intent(getActivity(), TopicActivity.class);
                intent.putExtra("id", data.get(pos).getId());
                startActivity(intent);
            }
        });
    }

    @Override
    public void getResult(TopicdeBean topicdeBean) {

    }

    @Override
    public void getResult(TopicRelated topicRelated) {

    }
}