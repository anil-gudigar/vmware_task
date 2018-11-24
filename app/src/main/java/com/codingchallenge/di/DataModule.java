package com.codingchallenge.di;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.NonNull;

import com.codingchallenge.app.AppConstants;
import com.codingchallenge.data.local.AppDatabase;
import com.codingchallenge.data.local.AppDbHelper;
import com.codingchallenge.data.local.DbHelper;
import com.codingchallenge.helper.injection.qualifier.ApplicationContext;
import com.codingchallenge.helper.injection.qualifier.DatabaseInfo;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
/**
 * Created by Anil Gudigar on 11/10/18.
 */
@Module
public class DataModule {
    public DataModule() {
    }

    @NonNull
    @Provides
    @Singleton
    AppDatabase provideAppDatabase(@NonNull @DatabaseInfo String dbName, @NonNull @ApplicationContext Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, dbName).fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }
}
