package com.withlava.pingobot.notifier;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class NotifierWorker {

    private final ScheduledExecutorService executor;

    private final NotifierTask notifierTask;

    public NotifierWorker(NotifierTask notifierTask) {
        this.notifierTask = notifierTask;
        executor = Executors.newScheduledThreadPool(1);
    }

    public void start() {
        executor.scheduleWithFixedDelay(notifierTask, 1, 1, TimeUnit.SECONDS);
    }

    public void stop() {
        executor.shutdown();
    }
}
