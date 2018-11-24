package com.codingchallenge;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.codingchallenge.data.local.AppDatabase;
import com.codingchallenge.data.local.dao.MessageDao;
import com.codingchallenge.data.model.Message;
import com.codingchallenge.utils.ModelTestUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created by Anil Gudigar on 11/12/18.
 */
@RunWith(AndroidJUnit4.class)
public class MessageDaoTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private MessageDao messageDao;
    private AppDatabase mDb;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        mDb = Room.inMemoryDatabaseBuilder(context, AppDatabase.class)
                // Allowing main thread queries, just for testing.
                .allowMainThreadQueries()
                .build();
        messageDao = mDb.messageDao();
    }

    @After
    public void closeDb() {
        mDb.close();
    }

    @Test
    public void insertAndGetMessage() {
        Message message = ModelTestUtils.sampleMessage(1L);
        messageDao.insert(message);
        List<Message> allMessages = messageDao.selectAll();
        assertEquals(allMessages.get(0).getSubject(), message.getSubject());
    }

    @Test
    public void getAllMessage() {
        Message message1 = ModelTestUtils.sampleMessage(1L,"Test1");
        messageDao.insert(message1);
        Message message2 = ModelTestUtils.sampleMessage(2L,"Test2");
        messageDao.insert(message2);
        List<Message> allMessages = messageDao.selectAll();
        assertEquals(allMessages.get(0).getSubject(), message1.getSubject());
        assertEquals(allMessages.get(1).getSubject(), message2.getSubject());
    }

    @Test
    public void deleteAll() {
        Message message1 = ModelTestUtils.sampleMessage(1L,"Test1");
        messageDao.insert(message1);
        Message message2 = ModelTestUtils.sampleMessage(2L,"test2");
        messageDao.insert(message2);
        messageDao.deleteAll();
        List<Message> allMessages = messageDao.selectAll();
        assertTrue(allMessages.isEmpty());
    }

    @Test
    public void loadAllByMessageId() {
        Message message1 = ModelTestUtils.sampleMessage(1L,"Test1");
        messageDao.insert(message1);
        List<Message> allMessages  = messageDao.loadAllByMessageId(1L);
        assertEquals(allMessages.get(0).getId(),message1.getId());
    }

}
