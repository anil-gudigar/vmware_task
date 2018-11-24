package com.codingchallenge.app.base.viewmodel;

import android.arch.lifecycle.ViewModel;

/**
 * Created by Anil Gudigar on 11/10/18.
 */
public abstract class ItemModel<ITEM_T> extends ViewModel {
    public ItemModel() {
    }

    public abstract void setItem(ITEM_T item);
}
