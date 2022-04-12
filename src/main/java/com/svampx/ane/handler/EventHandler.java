package com.svampx.ane.handler;

import com.svampx.ane.model.AbstractEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Timer;
import java.util.TimerTask;

@AllArgsConstructor
@Getter
@Slf4j
public abstract class EventHandler {

    private final Timer timer;

    public void start(AbstractEvent event) {
        if (event.getIsOnHold()) {
            //TODO add unique identifier
            log.error("Event is on hold");
        } else {

            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    finish(event);
                    //TODO notifying smth
                }
            };
            launchTimer(event, task);
        }
    }

    protected abstract void launchTimer(AbstractEvent abstractEvent, TimerTask task);

    public abstract void proceed(AbstractEvent event);

    public void hold(AbstractEvent event) {
        event.setIsOnHold(true);
        timer.cancel();
    }

    public void finish(AbstractEvent event) {
        event.setIsFinished(true);
        timer.cancel();
    }
}
