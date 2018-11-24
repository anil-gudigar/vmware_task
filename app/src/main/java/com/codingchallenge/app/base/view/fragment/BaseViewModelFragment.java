package com.codingchallenge.app.base.view.fragment;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.view.View;

import com.codingchallenge.app.base.view.activity.BaseActivity;
import com.codingchallenge.app.base.viewmodel.BaseViewModel;
import com.codingchallenge.helper.injection.Injectable;

import javax.inject.Inject;
/**
 * Created by Anil Gudigar on 11/10/18.
 */
public abstract class BaseViewModelFragment<B extends ViewDataBinding, VM extends BaseViewModel> extends BaseFragment<B>
        implements Injectable {

    @Inject
    protected ViewModelProvider.Factory viewModelFactory;

    @Inject
    protected VM viewModel;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (!(getActivity() instanceof BaseActivity)) {
            throw new IllegalStateException("All fragment's container must extend BaseActivity");
        }
    }

    @Override
    @CallSuper
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModelClass());
        viewModel.onCreate(getFragmentArguments());
    }


    public void setLoading(boolean loading) {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        viewModel.onDestroyView();
    }

    protected abstract Class<VM> getViewModelClass();
}
