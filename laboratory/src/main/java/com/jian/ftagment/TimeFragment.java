package com.jian.ftagment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;

/**
 * Created by jian on 2016/9/24.
 */

public class TimeFragment extends Fragment {
    public TimeFragment() {
    }
    private View v;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.frg_timechoose, null, false);
        initView();
        return v;
    }
    private void initView() {


    }
}
