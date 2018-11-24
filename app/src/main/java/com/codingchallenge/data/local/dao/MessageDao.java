package com.codingchallenge.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.database.Cursor;

import com.codingchallenge.data.model.Message;

import java.util.List;

/**
 * Created by Anil Gudigar on 11/10/18.
 */
@Dao
public interface MessageDao {

    /**
     * Inserts a message into the table.
     *
     * @param message A new message.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Message message);

    /**
     * Inserts multiple message into the database
     *
     * @param messages An array of new message.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Message> messages);

    /**
     * Select all Message.
     *
     * @return A {@link Cursor} of all the Message in the table.
     */
    @Query("SELECT * FROM " + Message.TABLE_NAME)
    List<Message> selectAll();

    @Query("SELECT * FROM " + Message.TABLE_NAME + " WHERE _id = :questionId")
    List<Message> loadAllByMessageId(Long questionId);

    /**
     * Counts the number of Message in the table.
     *
     * @return The number of Message.
     */
    @Query("SELECT COUNT(*) FROM " + Message.TABLE_NAME)
    int count();

    /**
     * Update the Message. The Message is identified by the row ID.
     *
     * @param message The Message to update.
     * @return A number of Message updated. This should always be {@code 1}.
     */
    @Update
    int update(Message message);

    /**
     * Delete the Message. The Message is identified by the row ID.
     *
     * @param message The Message to update.
     * @return A number of Message updated. This should always be {@code 1}.
     */
    @Delete
    int delete(Message message);

    /**
     * Delete All the Message.
     */
    @Query("DELETE FROM " + Message.TABLE_NAME)
    void deleteAll();
}
