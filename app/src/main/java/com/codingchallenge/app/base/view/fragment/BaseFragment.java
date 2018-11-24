package com.codingchallenge.app.base.view.fragment;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codingchallenge.helper.AutoClearedValue;

/**
 * Created by Anil Gudigar on 11/10/18.
 */

public abstract class BaseFragment<B extends ViewDataBinding> extends Fragment {

    protected String TAG;

    protected B binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TAG = this.getClass().getSimpleName();
        B dataBinding = DataBindingUtil.inflate(inflater, getLayout(), container, false);
        this.binding = new AutoClearedValue<B>(this, dataBinding).get();
        return binding.getRoot();
    }

    protected abstract int getLayout();

    @Nullable
    protected Bundle getFragmentArguments() {
        return getArguments();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}