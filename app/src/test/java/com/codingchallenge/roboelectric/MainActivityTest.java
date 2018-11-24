package com.codingchallenge.roboelectric;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.codingchallenge.R;
import com.codingchallenge.features.home.view.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Anil Gudigar on 11/12/18.
 */

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    private AppCompatActivity activity;

    @Before
    public void setup() {
        activity = Robolectric.buildActivity(MainActivity.class)
                .create()
                .resume()
                .get();
    }

    @Test
    public void validateToolBarContent() {
        Toolbar toolbar = activity.findViewById(R.id.toolbar);
        assertNotNull("Toolbar is null", toolbar);
        assertTrue("Toolbar's text does not match.", activity.getString(R.string.main_activity_label).equals(toolbar.getTitle().toString()));
    }

    @Test
    public void shouldNotBeNull() {
        assertNotNull( activity );
    }

    @Test
    public void shouldHaveMessageListFragment() {
        assertNotNull( activity.getSupportFragmentManager().findFragmentById( R.id.messagelist ) );
    }
}
