package com.codingchallenge.helper.rx;

import io.reactivex.Scheduler;

/**
 * Created by Anil Gudigar on 11/10/18.
 */

public interface SchedulerProvider {

    Scheduler computation();

    Scheduler io();

    Scheduler ui();
}
