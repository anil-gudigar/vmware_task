package com.codingchallenge.app.base.viewmodel;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.codingchallenge.app.base.adapter.BaseRecyclerViewModelAdapter;
import com.codingchallenge.app.base.interfaces.OnItemClickListener;
import com.codingchallenge.app.base.interfaces.Refreshable;
import com.codingchallenge.data.local.DbHelper;
import com.codingchallenge.data.model.Message;
import com.codingchallenge.features.message.viewmodel.MessageDiffCallBack;
import com.codingchallenge.helper.rx.SchedulerProvider;

import java.util.List;

/**
 * Created by Anil Gudigar on 11/10/18.
 */
public abstract class ListViewModel<T, A extends BaseRecyclerViewModelAdapter> extends BaseViewModel implements Refreshable, OnItemClickListener<T> {
    final DbHelper mDbHelper;
    final MessageDiffCallBack messageDiffCallBack;
    @Nullable
    private List<T> data;
    @Nullable
    private A adapter;

    public ListViewModel(DbHelper dbHelper, SchedulerProvider schedulerProvider) {
        super(schedulerProvider);
        mDbHelper = dbHelper;
        messageDiffCallBack = new MessageDiffCallBack();
    }

    @CallSuper
    public void initAdapter(@NonNull A adapter) {
        this.adapter = adapter;
        adapter.setData(data);
        adapter.setItemClickListener(this);
    }

    @CallSuper
    @Override
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    /**
     * Call this to fill data to {@link #adapter}
     *
     * @param newData new data
     * @param refresh true if data come from refresh action (call remote api)
     */
    protected void setData(@NonNull List<T> newData, boolean refresh) {
        if (data == null) {
            this.data = newData;
        } else {
            if (refresh) {
                messageDiffCallBack.setData((List<Message>) newData, (List<Message>) data);
                adapter.updateList(messageDiffCallBack);
                data.clear();
            }
            data.addAll(newData);
            messageDiffCallBack.setData((List<Message>) newData, (List<Message>) data);
            adapter.updateList(messageDiffCallBack);
        }
        if (adapter != null) {
            adapter.setData(data);
        }
    }

    @Nullable
    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Nullable
    public A getAdapter() {
        return adapter;
    }

    public void setAdapter(@Nullable A adapter) {
        this.adapter = adapter;
    }

    @Override
    public void refresh() {
        onLoadData();
    }

    public void refresh(int delay) {
        new Handler(Looper.myLooper()).postDelayed(this::refresh, delay);
    }

    @Override
    public final boolean onLoadData() {
        LoadData((isRefresh, response) -> {
            setData(response, isRefresh);
        });
        return true;
    }

    protected abstract void LoadData(OnLoadDataDone<T> onLoadDataDone);

    /**
     * IMPORTANCE to clear adapter reference since adapter instance is related to activity / fragment
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (adapter != null) {
            this.data = adapter.getData();
        }
        this.adapter = null;
    }

    public interface OnLoadDataDone<T> {

        /**
         * Called after success response come
         *
         * @param isRefresh true if refreshed
         * @param response  response data
         */
        void onDone(boolean isRefresh, List<T> response);
    }
}
