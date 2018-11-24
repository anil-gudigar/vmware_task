package com.codingchallenge.features.message.viewmodel;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.codingchallenge.app.base.viewmodel.ListViewModel;
import com.codingchallenge.data.local.DbHelper;
import com.codingchallenge.data.model.Message;
import com.codingchallenge.features.message.view.adapters.MessageRecycleViewAdapter;
import com.codingchallenge.helper.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import lombok.Getter;
import lombok.Setter;
/**
 * Created by Anil Gudigar on 11/10/18.
 */
@Setter
@Getter
public class MessageListViewModel extends ListViewModel<Message, MessageRecycleViewAdapter> {

    private final MutableLiveData<List<Message>> mMessageLiveData;
    private DbHelper mDbHelper;
    private LifecycleOwner mLifecycleOwner;

    @Inject
    public MessageListViewModel(DbHelper dbHelper, SchedulerProvider schedulerProvider) {
        super(dbHelper, schedulerProvider);
        mDbHelper = dbHelper;
        mMessageLiveData = new MutableLiveData<>();
    }

    @Override
    protected void onFirsTimeUiCreate(@Nullable Bundle bundle) {
        mDbHelper.getAllMessage();
    }

    @Override
    public void initAdapter(@NonNull MessageRecycleViewAdapter adapter) {
        super.initAdapter(adapter);
        setData(new ArrayList<>(), true);
        refresh(100);
    }

    @Override
    protected void LoadData(OnLoadDataDone<Message> onLoadDataDone) {
        loadMessages(onLoadDataDone);
        mMessageLiveData.observe(mLifecycleOwner, messageList -> {
            setData(messageList, true);
        });
    }


    @Override
    public void onItemClick(View v, Message item) {
        item.setRead(true);
        updateMessages(item);
    }

    private void updateMessages(Message item) {
        getmCompositeDisposable().add(mDbHelper
                .updateMessage(item)
                .subscribeOn(getMSchedulerProvider().io())
                .observeOn(getMSchedulerProvider().ui())
                .subscribe(success -> {
                    refresh();
                }, throwable -> {

                }));
    }

    private void loadMessages(OnLoadDataDone<Message> onLoadDataDone) {
        getmCompositeDisposable().add(mDbHelper
                .getAllMessage()
                .subscribeOn(getMSchedulerProvider().io())
                .observeOn(getMSchedulerProvider().ui())
                .subscribe(messageList -> {
                    if (messageList != null && messageList.size() != 0) {
                        mMessageLiveData.setValue(messageList);
                        onLoadDataDone.onDone(true, messageList);
                    }
                }, throwable -> {

                }));
    }
}