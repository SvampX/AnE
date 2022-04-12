package com.svampx.ane.handler;

import com.svampx.ane.model.AbstractEvent;
import com.svampx.ane.model.OneTimeEvent;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

@Slf4j
public class OneTimeEventHandler extends EventHandler {

    public OneTimeEventHandler(Timer timer) {
        super(timer);
    }

    @Override
    protected void launchTimer(AbstractEvent abstractEvent, TimerTask task) {
        getTimer().schedule(task, Date.from(castEvent(abstractEvent).getOffset().toInstant(ZoneOffset.UTC)));
    }

    @Override
    public void proceed(AbstractEvent abstractEvent) {
        OneTimeEvent event = castEvent(abstractEvent);
        if(event.getIsOnHold() && event.getOffset().toInstant(ZoneOffset.UTC).isAfter(Instant.now())) {
            finish(event);
        }
        event.setIsOnHold(false);
        start(event);
    }

    private OneTimeEvent castEvent(AbstractEvent abstractEvent) {
        if (abstractEvent instanceof OneTimeEvent) {
            return (OneTimeEvent) abstractEvent;
        }
        else {
            throw new IllegalArgumentException("Inappropriate event type for OneTimeEventHandler");
        }
    }

}
