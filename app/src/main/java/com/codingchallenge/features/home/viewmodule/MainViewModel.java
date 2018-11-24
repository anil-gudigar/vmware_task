package com.codingchallenge.features.home.viewmodule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.codingchallenge.R;
import com.codingchallenge.app.base.viewmodel.BaseViewModel;
import com.codingchallenge.data.local.DbHelper;
import com.codingchallenge.helper.rx.SchedulerProvider;

import javax.inject.Inject;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Anil Gudigar on 11/10/18.
 */
@Setter
@Getter
public class MainViewModel extends BaseViewModel {
    private final DbHelper mDbHelper;
    private String Title;

    @Inject
    public MainViewModel(DbHelper dbHelper, SchedulerProvider schedulerProvider) {
        super(schedulerProvider);
        setTitle(getString(R.string.main_activity_label));
        mDbHelper = dbHelper;
    }

    @Override
    protected void onFirsTimeUiCreate(@Nullable Bundle bundle) {
        loadinitialData();
    }

    public void loadinitialData() {
        getmCompositeDisposable().add(mDbHelper
                .populateInitialData()
                .subscribeOn(getMSchedulerProvider().io())
                .observeOn(getMSchedulerProvider().ui())
                .subscribe(success -> {
                    if (success) {
                        Log.i("anil", "data dump done");
                    }
                }, throwable -> {

                }));
    }
}
