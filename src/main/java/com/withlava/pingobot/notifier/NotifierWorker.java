package com.withlava.pingobot.notifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class NotifierWorker {
    private static final Logger logger = LoggerFactory.getLogger(NotifierWorker.class);

    private final ScheduledExecutorService executor;

    private final NotifierTask notifierTask;

    public NotifierWorker(NotifierTask notifierTask) {
        this.notifierTask = notifierTask;
        executor = Executors.newScheduledThreadPool(1);
    }

    public void start() {
        logger.info("Notifier worker started!");
        executor.scheduleWithFixedDelay(notifierTask, 1, 1, TimeUnit.SECONDS);
    }

    public void stop() {
        logger.info("Notifier worker stopped!");
        executor.shutdown();
    }
}
