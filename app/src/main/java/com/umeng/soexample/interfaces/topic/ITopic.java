package com.umeng.soexample.interfaces.topic;

import com.umeng.soexample.interfaces.CallBack;
import com.umeng.soexample.interfaces.IBasePresenter;
import com.umeng.soexample.interfaces.IBaseView;
import com.umeng.soexample.interfaces.IModel;
import com.umeng.soexample.model.topic.TopicBean;
import com.umeng.soexample.model.topic.TopicRelated;
import com.umeng.soexample.model.topic.TopicdeBean;

public interface ITopic {
    interface View extends IBaseView {
        void getResult(TopicBean topicBean);
        void getResult(TopicdeBean topicdeBean);
        void getResult(TopicRelated topicRelated);
    }
    interface Presenter extends IBasePresenter<View> {
        void getTopic(int page);
        void getTopicde(int id);
        void getTopicRela(int id);
    }
    interface Model extends IModel {
        void getTopic(int page, CallBack callback);
        void getTopicde(int id, CallBack callback);
        void getTopicRela(int id, CallBack callback);
    }
}
