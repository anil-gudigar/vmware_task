package com.codingchallenge.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.codingchallenge.data.local.dao.MessageDao;
import com.codingchallenge.data.model.Message;

/**
 * Created by Anil Gudigar on 11/10/18.
 */
@Database(entities = {Message.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract MessageDao messageDao();
}
