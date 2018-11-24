package com.codingchallenge.data.local;

import com.codingchallenge.data.model.Message;
import com.codingchallenge.sample.SampleMessages;

import java.util.List;

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
        return Observable.fromCallable(() -> mAppDatabase.messageDao().selectAll());
    }

    @Override
    public Observable<List<Message>> getMessagesForMessageId(final Long questionId) {
        return Observable.fromCallable(() -> mAppDatabase.messageDao().loadAllByMessageId(questionId));
    }

    @Override
    public Observable<Boolean> isMessageEmpty() {
        return Observable.fromCallable(() -> mAppDatabase.messageDao().selectAll().isEmpty());
    }

    @Override
    public Observable<Boolean> saveMessageList(final List<Message> messageList) {
        return Observable.fromCallable(() -> {
            mAppDatabase.messageDao().insertAll(messageList);
            return true;
        });
    }

    @Override
    public Observable<Boolean> saveMessage(final Message message) {
        return Observable.fromCallable(() -> {
                    mAppDatabase.messageDao().insert(message);
                    return true;
                }
        );
    }

    @Override
    public Observable<Boolean> updateMessage(Message message) {
        return Observable.fromCallable(() -> {
            mAppDatabase.messageDao().update(message);
            return true;
        });
    }

    @Override
    public Observable<Boolean> deleteMessage(Message message) {
        return Observable.fromCallable(() -> {
            mAppDatabase.messageDao().delete(message);
            return true;
        });
    }

    /**
     * Inserts the dummy data into the database if it is currently empty.
     */
    public Observable<Boolean> populateInitialData() {
        return Observable.fromCallable(() -> { {
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

