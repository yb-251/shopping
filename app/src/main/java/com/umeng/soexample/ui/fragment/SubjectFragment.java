package com.umeng.soexample.ui.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.umeng.soexample.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubjectFragment extends Fragment {


    public SubjectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_subject, container, false);
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {

    }

}
