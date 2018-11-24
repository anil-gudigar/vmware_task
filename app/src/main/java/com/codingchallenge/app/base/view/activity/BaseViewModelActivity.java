package com.codingchallenge.app.base.view.activity;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;

import com.codingchallenge.app.base.viewmodel.BaseViewModel;

import java.lang.reflect.ParameterizedType;

import javax.inject.Inject;

/**
 * Created by Anil Gudigar on 11/10/18.
 */
public abstract class BaseViewModelActivity<B extends ViewDataBinding, VM extends BaseViewModel> extends BaseActivity {
    public String TAG;
    protected VM viewModel;
    @VisibleForTesting
    protected B binding;
    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TAG = getClass().getSimpleName();

        //init data binding
        binding = DataBindingUtil.setContentView(this, getLayout());
        initViews();

        // int view model
        // noinspection unchecked
        Class<VM> viewModelClass = (Class<VM>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[1]; // 1 is BaseViewModel

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(viewModelClass);

        viewModel.onCreate(getIntent().getExtras());
    }

    public VM getViewModel() {
        return viewModel;
    }

    @Override
    protected boolean shouldUseDataBinding() {
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}