package com.codingchallenge.roboelectric;

import android.os.Build;
import android.support.v7.widget.RecyclerView;

import com.codingchallenge.BuildConfig;
import com.codingchallenge.R;
import com.codingchallenge.features.home.view.MainActivity;
import com.codingchallenge.features.message.view.fragments.MessageListFragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Anil Gudigar on 11/12/18.
 */

@RunWith(RobolectricTestRunner.class)
public class MessageListFragmentTest {
    private MainActivity activity = Robolectric.setupActivity(MainActivity.class);
    private MessageListFragment messageListFragment;

    @Before
    public void setUp() {
        this.messageListFragment = (MessageListFragment) activity.getSupportFragmentManager().findFragmentById( R.id.messagelist );
    }

    @Test
    public void shouldNotBeNull() {
        assertNotNull( messageListFragment );
    }

    @Test
    public void shouldHaveDataViewFragment() {
        assertNotNull( messageListFragment.getView().findViewById(R.id.dataView) );
    }

    @Test
    public void shouldHaveEmptyViewFragment() {
        assertNotNull( messageListFragment.getView().findViewById(R.id.emptyView) );
    }

    @Test
    public void shouldHaveProgressViewFragment() {
        assertNotNull( messageListFragment.getView().findViewById(R.id.progressView) );
    }

    @Test
    public void shouldHaveMessageListRecycleViewFragment() {
        assertNotNull( messageListFragment.getView().findViewById(R.id.recyclerView) );
    }

    @Test
    public void shouldHaveMessageListAdapterFragment() {
        assertNotNull(((RecyclerView)messageListFragment.getView().findViewById(R.id.recyclerView)).getAdapter());
    }

}
