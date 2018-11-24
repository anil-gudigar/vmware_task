package com.codingchallenge.helper.injection.ui;

import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.codingchallenge.helper.injection.qualifier.ActivityContext;
import com.codingchallenge.helper.injection.qualifier.ActivityFragmentManager;
import com.codingchallenge.helper.injection.qualifier.ChildFragmentManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Anil Gudigar on 11/10/18.
 */

@Module
public abstract class BaseFragmentModule<T extends Fragment> {

    @Provides
    @ActivityContext
    public Context provideContext(@NonNull T fragment) {
        return fragment.getContext();
    }

    @NonNull
    @Provides
    @ChildFragmentManager
    public FragmentManager provideChildFragmentManager(@NonNull T fragment) {
        return fragment.getChildFragmentManager();
    }

    @Provides
    @ActivityFragmentManager
    public FragmentManager provideActivityFragmentManager(@NonNull FragmentActivity activity) {
        return activity.getSupportFragmentManager();
    }

    @Nullable
    @Provides
    public FragmentActivity provideActivity(@NonNull T fragment) {
        return fragment.getActivity();
    }

    @Provides
    public LifecycleOwner provideLifeCycleOwner(T fragment) {
        return fragment;
    }

}