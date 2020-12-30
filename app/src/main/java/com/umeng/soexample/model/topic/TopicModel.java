package com.umeng.soexample.model.topic;
import com.umeng.soexample.base.BaseModel;
import com.umeng.soexample.interfaces.CallBack;
import com.umeng.soexample.interfaces.topic.ITopic;
import com.umeng.soexample.net.CommonSubscriber;
import com.umeng.soexample.net.HttpManager;
import com.umeng.soexample.utils.RxUtils;

public class TopicModel extends BaseModel implements ITopic.Model {
    @Override
    public void getTopic(int page, CallBack callback) {
        addDisposable(HttpManager.getInstance().getShopApi().getTopic(page)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<TopicBean>(callback) {
                    @Override
                    public void onNext(TopicBean topicBean) {
                        callback.success(topicBean);
                    }
                }));
    }

    @Override
    public void getTopicde(int id, CallBack callback) {
        addDisposable(HttpManager.getInstance().getShopApi().getTopicde(id)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<TopicdeBean>(callback) {
                    @Override
                    public void onNext(TopicdeBean topicdeBean) {
                        callback.success(topicdeBean);
                    }
                }));
    }

    @Override
    public void getTopicRela(int id, CallBack callback) {
        addDisposable(HttpManager.getInstance().getShopApi().getTopicRela(id)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<TopicRelated>(callback) {
                    @Override
                    public void onNext(TopicRelated topicRelated) {
                        callback.success(topicRelated);
                    }
                }));
    }
}
