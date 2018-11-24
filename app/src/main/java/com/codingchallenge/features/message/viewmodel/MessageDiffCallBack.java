package com.codingchallenge.features.message.viewmodel;

import android.support.annotation.Nullable;

import com.codingchallenge.app.base.view.widget.DiffCallBack;
import com.codingchallenge.data.model.Message;

import java.util.List;

/**
 * Created by Anil Gudigar on 11/12/18.
 */
public class MessageDiffCallBack extends DiffCallBack<Message> {

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).id == newList.get(newItemPosition).id;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        //you can return particular field for changed item.
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }


    @Override
    public void setData(List<Message> newData, List<Message> data) {
        oldList = data;
        newList = newData;
    }

}
