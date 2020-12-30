package com.umeng.soexample.presenter.topic;

import com.umeng.soexample.base.BasePresenter;
import com.umeng.soexample.interfaces.CallBack;
import com.umeng.soexample.interfaces.topic.ITopic;
import com.umeng.soexample.model.topic.TopicBean;
import com.umeng.soexample.model.topic.TopicModel;
import com.umeng.soexample.model.topic.TopicRelated;
import com.umeng.soexample.model.topic.TopicdeBean;

public class TopicPresenter extends BasePresenter<ITopic.View> implements ITopic.Presenter {

    ITopic.Model model;
    public TopicPresenter() {
        model = new TopicModel();
    }

    @Override
    public void getTopic(int page) {
        model.getTopic(page, new CallBack() {
            @Override
            public void fail(String msg) {

            }

            @Override
            public void success(Object o) {
                mView.getResult((TopicBean) o);
            }
        });
    }

    @Override
    public void getTopicde(int id) {
        model.getTopicde(id, new CallBack() {
            @Override
            public void fail(String msg) {

            }

            @Override
            public void success(Object o) {
                mView.getResult((TopicdeBean) o);
            }
        });
    }

    @Override
    public void getTopicRela(int id) {
        model.getTopicRela(id, new CallBack() {
            @Override
            public void fail(String msg) {

            }

            @Override
            public void success(Object o) {
                mView.getResult((TopicRelated) o);
            }
        });
    }
}
