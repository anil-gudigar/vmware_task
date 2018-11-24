package com.codingchallenge.helper.injection.ui;

import android.app.Activity;
import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.codingchallenge.helper.injection.qualifier.ActivityContext;
import com.codingchallenge.helper.injection.qualifier.ActivityFragmentManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Anil Gudigar on 11/10/18.
 */

@Module
public abstract class BaseActivityModule<T extends AppCompatActivity> {

    @Provides
    @ActivityContext
    public Context provideContext(T activity) {
        return activity;
    }

    @Provides
    public Activity provideActivity(T activity) {
        return activity;
    }

    @Provides
    @ActivityFragmentManager
    public FragmentManager provideFragmentManager(@NonNull T activity) {
        return activity.getSupportFragmentManager();
    }

    @Provides
    public LifecycleOwner provideLifeCycleOwner(T activity) {
        return activity;
    }
}
