package com.svampx.ane.model;

import lombok.Getter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Timer;

@Getter
public abstract class AbstractEvent {

//    private OffsetDateTime created;
//    private OffsetDateTime deleted;
//    private OffsetDateTime updated;
    private Timer timer;
    private LocalDateTime offset;
    private String message;
    //mb enum but in this case couldn't be created by a user
    private String group;
    private Boolean isOnHold = true;
    private Boolean isFinished = false;

    //mb trigger,
    public abstract void start();
    public void hold() {
        isOnHold = true;
        getTimer().cancel();
    }
    public void proceed() {
        if(isOnHold && offset.toInstant(ZoneOffset.UTC).isAfter(Instant.now())) {
            finish();
        }
        isOnHold = false;
        start();
    }
    public void finish() {
        isFinished = true;
        getTimer().cancel();
    }

    public Boolean getFinished() {
        return isFinished;
    }
}
