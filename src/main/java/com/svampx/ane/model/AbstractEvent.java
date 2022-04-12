package com.svampx.ane.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractEvent {

    private String message;
    private String group;
    private Boolean isOnHold = true;
    private Boolean isFinished = false;

}
