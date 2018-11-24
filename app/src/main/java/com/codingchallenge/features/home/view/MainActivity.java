package com.codingchallenge.features.home.view;

import android.os.Bundle;

import com.codingchallenge.R;
import com.codingchallenge.app.base.view.activity.BaseViewModelActivity;
import com.codingchallenge.databinding.ActivityMainBinding;
import com.codingchallenge.features.home.viewmodule.MainViewModel;
/**
 * Created by Anil Gudigar on 11/10/18.
 */
public class MainActivity extends BaseViewModelActivity<ActivityMainBinding, MainViewModel> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.toolbar.setTitle(viewModel.getTitle());
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected boolean canBack() {
        return false;
    }
}
