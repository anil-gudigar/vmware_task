package com.codingchallenge.data.local;

import com.codingchallenge.data.model.Message;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Anil Gudigar on 11/10/18.
 */
public interface DbHelper {
    Observable<List<Message>> getAllMessage();

    Observable<List<Message>> getMessagesForMessageId(Long questionId);

    Observable<Boolean> isMessageEmpty();

    Observable<Boolean> saveMessage(Message message);

    Observable<Boolean> updateMessage(Message message);

    Observable<Boolean> deleteMessage(Message message);

    Observable<Boolean> saveMessageList(List<Message> messageList);

    Observable<Boolean> populateInitialData();

}
