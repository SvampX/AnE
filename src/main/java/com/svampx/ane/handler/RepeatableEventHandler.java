package com.svampx.ane.handler;

import com.svampx.ane.model.AbstractEvent;
import com.svampx.ane.model.OneTimeEvent;
import com.svampx.ane.model.RepeatableEvent;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

@Slf4j
public class RepeatableEventHandler extends EventHandler {

    public RepeatableEventHandler(Timer timer) {
        super(timer);
    }

    @Override
    protected void launchTimer(AbstractEvent abstractEvent, TimerTask task) {
        RepeatableEvent event = castEvent(abstractEvent);
        long delay = getDelay(event);
        getTimer().scheduleAtFixedRate(task, delay, event.getPeriod());
    }

    private long getDelay(RepeatableEvent event) {
        long offsetMills = event.getTimeOffset().get(ChronoField.MILLI_OF_DAY);
        long currentMills = ZonedDateTime.now().get(ChronoField.MILLI_OF_DAY);
        return Math.max(offsetMills - currentMills, currentMills - offsetMills);
    }

    @Override
    public void proceed(AbstractEvent abstractEvent) {
        RepeatableEvent event = castEvent(abstractEvent);
        event.setIsOnHold(false);
        start(event);
    }

    private RepeatableEvent castEvent(AbstractEvent abstractEvent) {
        if (abstractEvent instanceof OneTimeEvent) {
            return (RepeatableEvent) abstractEvent;
        }
        else {
            throw new IllegalArgumentException("Inappropriate event type for OneTimeEventHandler");
        }
    }

}
