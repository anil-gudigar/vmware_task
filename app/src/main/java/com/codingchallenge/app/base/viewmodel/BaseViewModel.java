package com.codingchallenge.app.base.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.util.Log;

import com.codingchallenge.app.CodingChallegeApp;
import com.codingchallenge.helper.rx.SchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;
import lombok.Getter;
/**
 * Created by Anil Gudigar on 11/10/18.
 */
@Getter
public abstract class BaseViewModel extends ViewModel {

    @NonNull
    protected final String TAG;
    private final ObservableBoolean mIsLoading = new ObservableBoolean(false);
    private final SchedulerProvider mSchedulerProvider;
    private boolean isFirstTimeUiCreate = true;
    @NonNull
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public BaseViewModel(SchedulerProvider schedulerProvider) {
        TAG = this.getClass().getSimpleName();
        mSchedulerProvider = schedulerProvider;
    }

    /**
     * called after fragment / activity is created with input bundle arguments
     *
     * @param bundle argument data
     */
    @CallSuper
    public void onCreate(@Nullable Bundle bundle) {
        Log.d(TAG, "onCreate: UI creating...");
        if (isFirstTimeUiCreate) {
            onFirsTimeUiCreate(bundle);
            isFirstTimeUiCreate = false;
        }
    }

    /**
     * Called when UI create for first time only, since activity / fragment might be rotated,
     * we don't need to re-init data, because view model will survive, data aren't destroyed
     *
     * @param bundle  bundle
     */
    protected abstract void onFirsTimeUiCreate(@Nullable Bundle bundle);


    @NonNull
    protected String getString(@StringRes int res) {
        return CodingChallegeApp.getInstance().getString(res);
    }

    public void disposeAllExecutions() {
        mCompositeDisposable.dispose();
        mCompositeDisposable = new CompositeDisposable();
    }

    @CallSuper
    @Override
    protected void onCleared() {
        super.onCleared();
        mCompositeDisposable.dispose();
    }

    @NonNull
    public CompositeDisposable getmCompositeDisposable() {
        return mCompositeDisposable;
    }

    public void setmCompositeDisposable(@NonNull CompositeDisposable mCompositeDisposable) {
        this.mCompositeDisposable = mCompositeDisposable;
    }

    /**
     * It is importance to un-reference activity / fragment instance after they are destroyed
     * For situation of configuration changes, activity / fragment will be destroyed and recreated,
     * but view model will survive, so if we don't un-reference them, memory leaks will occur
     */
    public void onDestroyView() {
    }

    public boolean isFirstTimeUiCreate() {
        return isFirstTimeUiCreate;
    }

    public void setFirstTimeUiCreate(boolean firstTimeUiCreate) {
        isFirstTimeUiCreate = firstTimeUiCreate;
    }

}