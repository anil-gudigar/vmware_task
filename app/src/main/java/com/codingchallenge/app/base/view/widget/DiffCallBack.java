package com.codingchallenge.app.base.view.widget;

import android.support.v7.util.DiffUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anil Gudigar on 11/12/18.
 */
public abstract class DiffCallBack<T> extends DiffUtil.Callback {
    protected List<T> oldList = new ArrayList<>();
    protected List<T> newList = new ArrayList<>();

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    public abstract void setData(List<T> newData, List<T> data);
}
