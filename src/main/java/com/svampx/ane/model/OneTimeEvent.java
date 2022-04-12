package com.svampx.ane.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
public class OneTimeEvent extends AbstractEvent {

    private LocalDateTime offset;

}
