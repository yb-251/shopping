package com.umeng.soexample.ui.home;

import android.content.Context;
import android.widget.TextView;

import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseAdapter;
import com.umeng.soexample.model.home.GoodDetailBean;

import java.util.List;

public class GoodRcyProblemAdapter extends BaseAdapter<GoodDetailBean.DataBeanX.IssueBean> {
    Context context;
    public GoodRcyProblemAdapter(Context context, List<GoodDetailBean.DataBeanX.IssueBean> data) {
        super(context, data);
        this.context = context;
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.layout_problem;
    }

    @Override
    protected void bindData(GoodDetailBean.DataBeanX.IssueBean data, VH vh) {
        TextView tv_problem = (TextView) vh.getViewById(R.id.tv_problem);
        TextView tv_problem2 = (TextView) vh.getViewById(R.id.tv_problem2);
        String question = data.getQuestion();
        String answer = data.getAnswer();
        tv_problem.setText(question);
        tv_problem2.setText(answer);
    }
}
