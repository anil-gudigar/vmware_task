package com.codingchallenge.helper.injection.ui;

import android.support.annotation.NonNull;

import com.codingchallenge.features.home.module.MainActivityModule;
import com.codingchallenge.features.home.view.MainActivity;
import com.codingchallenge.features.message.module.MessageListModule;
import com.codingchallenge.features.message.view.fragments.MessageListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
/**
 * Created by Anil Gudigar on 11/10/18.
 */
@Module
public abstract class BuildersModule {
    @NonNull
    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity mainActivity();

    @NonNull
    @ContributesAndroidInjector(modules = MessageListModule.class)
    abstract MessageListFragment messageListFragment();
}
