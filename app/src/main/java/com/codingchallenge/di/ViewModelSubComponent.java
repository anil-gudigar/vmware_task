package com.codingchallenge.di;

import android.support.annotation.NonNull;

import dagger.Subcomponent;
/**
 * Created by Anil Gudigar on 11/10/18.
 */
@Subcomponent
public interface ViewModelSubComponent {
    @Subcomponent.Builder
    interface Builder {
        @NonNull
        ViewModelSubComponent build();
    }
}
