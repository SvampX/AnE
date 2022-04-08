package com.svampx.ane.model;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneOffset;
import java.util.Date;
import java.util.TimerTask;

@NoArgsConstructor
@RequiredArgsConstructor
@Slf4j
public class OneTimeEvent extends AbstractEvent {

    @Override
    public void start() {
        if(getIsOnHold()) {
            //TODO add unique identifier
            log.error("Event is on hold");
        }
        else {

            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    finish();
                    //TODO notifying smth
                }
            };
            getTimer().schedule(task, Date.from(getOffset().toInstant(ZoneOffset.UTC)));
        }
    }
}
