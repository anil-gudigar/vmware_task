package com.codingchallenge.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.provider.BaseColumns;
import android.support.annotation.Nullable;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Anil Gudigar on 11/10/18.
 */
@Getter
@Setter
@Entity(tableName = Message.TABLE_NAME)
public class Message {
    /**
     * The name of the Cheese table.
     */
    public static final String TABLE_NAME = "message";

    /**
     * The name of the ID column.
     */
    public static final String COLUMN_ID = BaseColumns._ID;

    /**
     * The name of the name column.
     */
    public static final String COLUMN_NAME_SUBJECT = "subject";
    /**
     * The name of the name column.
     */
    public static final String COLUMN_NAME_READ = "read";

    /**
     * The unique ID of the cheese.
     */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true, name = COLUMN_ID)
    public long id;

    /**
     * The name of the cheese.
     */
    @ColumnInfo(name = COLUMN_NAME_SUBJECT)
    public String subject;

    /**
     * The name of the cheese.
     */
    @Nullable
    @ColumnInfo(name = COLUMN_NAME_READ)
    public Boolean read = false;

    public Message(String subject) {
        this.subject = subject;
    }
}
