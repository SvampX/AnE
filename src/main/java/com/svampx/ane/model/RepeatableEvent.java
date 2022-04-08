package com.svampx.ane.model;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.*;
import java.util.TimerTask;

@NoArgsConstructor
@RequiredArgsConstructor
@Slf4j
public class RepeatableEvent extends AbstractEvent {

    //default 24 hrs
    private final long period = 1000L * 60L * 60L * 24L;

    private long geTimeFromNowToOffset() {
        Instant todayMidnightUTC = LocalDateTime.now().toInstant(ZoneOffset.UTC);
        return Duration.between(todayMidnightUTC, getOffset()).toMillis();
    }

    @Override
    public void start() {
        if(getIsOnHold()) {
            //TODO add unique identifier
            log.warn("Event was on hold");
            proceed();
        }
        else {

            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    //TODO notifying smth
                }
            };
            getTimer().scheduleAtFixedRate(task, geTimeFromNowToOffset(), period);
        }
    }

    public void cancelEvent() {
        finish();
        //TODO add unique identifier
        log.info("Repeatable Event was cancelled");
    }
}
