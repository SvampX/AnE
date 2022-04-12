package com.svampx.ane.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.*;
import java.util.TimerTask;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Slf4j
public class RepeatableEvent extends AbstractEvent {

    private LocalTime timeOffset;

    //default 24 hrs
    private final long period = 1000L * 60L * 60L * 24L;
}
