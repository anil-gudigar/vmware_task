package com.codingchallenge.helper.injection.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.codingchallenge.features.home.viewmodule.MainViewModel;
import com.codingchallenge.features.message.viewmodel.MessageListViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by Anil Gudigar on 11/10/18.
 */
@Module
public abstract class ViewModelModule {

    @NonNull
    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(CodingChallengeViewModelFactory factory);

    @NonNull
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    abstract ViewModel bindmainViewModel(MainViewModel viewModel);

    @NonNull
    @Binds
    @IntoMap
    @ViewModelKey(MessageListViewModel.class)
    abstract ViewModel messageListViewModel(MessageListViewModel viewModel);

}

