package com.codingchallenge.data.local;

import android.support.annotation.NonNull;

import com.codingchallenge.data.model.Message;
import com.codingchallenge.sample.SampleMessages;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Anil Gudigar on 11/10/18.
 */
public class AppDbHelper implements DbHelper {

    private final AppDatabase mAppDatabase;

    @Inject
    public AppDbHelper(AppDatabase appDatabase) {
        this.mAppDatabase = appDatabase;
    }

    @Override
    public Observable<List<Message>> getAllMessage() {
        return Observable.fromCallable(new Callable<List<Message>>() {
            @Override
            public List<Message> call() {
                return mAppDatabase.messageDao().selectAll();
            }
        });
    }

    @Override
    public Observable<List<Message>> getMessagesForMessageId(final Long questionId) {
        return Observable.fromCallable(new Callable<List<Message>>() {
            @Override
            public List<Message> call() {
                return mAppDatabase.messageDao().loadAllByMessageId(questionId);
            }
        });
    }

    @Override
    public Observable<Boolean> isMessageEmpty() {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() {
                return mAppDatabase.messageDao().selectAll().isEmpty();
            }
        });
    }

    @Override
    public Observable<Boolean> saveMessageList(final List<Message> messageList) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @NonNull
            @Override
            public Boolean call() {
                mAppDatabase.messageDao().insertAll(messageList);
                return true;
            }
        });
    }

    @Override
    public Observable<Boolean> saveMessage(final Message message) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @NonNull
            @Override
            public Boolean call() {
                mAppDatabase.messageDao().insert(message);
                return true;
            }
        });
    }

    @Override
    public Observable<Boolean> updateMessage(Message message) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @NonNull
            @Override
            public Boolean call() {
                mAppDatabase.messageDao().update(message);
                return true;
            }
        });
    }

    @Override
    public Observable<Boolean> deleteMessage(Message message) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @NonNull
            @Override
            public Boolean call() {
                mAppDatabase.messageDao().delete(message);
                return true;
            }
        });
    }

    /**
     * Inserts the dummy data into the database if it is currently empty.
     */
    public Observable<Boolean> populateInitialData() {
        return Observable.fromCallable(new Callable<Boolean>() {
            @NonNull
            @Override
            public Boolean call() {
                if (mAppDatabase.messageDao().count() == 0) {
                    for (Message message : SampleMessages.getPREPOPULATE_DATA()) {
                        mAppDatabase.messageDao().insert(message);
                    }
                }
                return true;
            }
        });
    }
}

