package com.codingchallenge.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.codingchallenge.di.AppComponent;
import com.codingchallenge.helper.injection.AppInjector;
import com.facebook.stetho.Stetho;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by Anil Gudigar on 11/10/18.
 */
public class CodingChallegeApp extends Application implements HasActivityInjector {
    protected static CodingChallegeApp sInstance;

    protected static AppComponent sAppComponent;

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    public static AppComponent getAppComponent() {
        return sAppComponent;
    }

    public static CodingChallegeApp getInstance() {
        return sInstance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        Stetho.initializeWithDefaults(this);
        initializeComponent();
    }

    private void initializeComponent() {
        sAppComponent = AppInjector.init(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
