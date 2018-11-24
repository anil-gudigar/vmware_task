package com.codingchallenge.features.message.viewmodel;

import com.codingchallenge.app.base.viewmodel.ItemModel;
import com.codingchallenge.data.model.Message;

import lombok.Getter;

/**
 * Created by Anil Gudigar on 11/10/18.
 */
@Getter
public class MessageItemViewModel extends ItemModel<Message> {

    private Message message;

    @Override
    public void setItem(Message item) {
        message = item;
    }

    public boolean isRead() {
        return message.read;
    }

    public void setRead(boolean read) {
        message.read = read;
    }
}
