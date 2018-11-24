package com.codingchallenge.app.base.view.widget;

import android.databinding.BindingAdapter;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.widget.TextView;

/**
 * Created by Anil Gudigar on 11/10/18.
 */
public class TextViewBindingAdapter {
    @BindingAdapter("isRead")
    public static void setBold(@NonNull TextView view, boolean isRead) {
        if (isRead) {
            view.setTypeface(null, Typeface.NORMAL);
        } else {
            view.setTypeface(null, Typeface.BOLD);
        }
    }
}
