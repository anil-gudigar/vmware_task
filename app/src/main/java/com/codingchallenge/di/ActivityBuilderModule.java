package com.codingchallenge.di;

import android.support.annotation.NonNull;

import com.codingchallenge.features.home.view.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
/**
 * Created by Anil Gudigar on 11/10/18.
 */
@Module
public abstract class ActivityBuilderModule {
    @NonNull
    @ContributesAndroidInjector(modules = FragmentBuilderModule.class)
    abstract MainActivity mainActivity();
}
