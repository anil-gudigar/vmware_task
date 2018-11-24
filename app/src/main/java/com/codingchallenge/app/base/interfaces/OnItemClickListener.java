package com.codingchallenge.app.base.interfaces;

import android.view.View;

/**
 * Created by Anil Gudigar on 11/10/18.
 */
public interface OnItemClickListener<T> {
    void onItemClick(View v, T item);
}
