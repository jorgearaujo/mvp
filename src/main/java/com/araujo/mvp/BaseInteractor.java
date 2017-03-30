package com.araujo.mvp;

import de.greenrobot.event.EventBus;

/**
 * Created by jorge.araujo on 1/2/2016.
 */
public class BaseInteractor {

    public BaseInteractor() {
    }

    private EventBus bus = EventBus.getDefault();

    public EventBus getEventBus() {
        return bus;
    }
}
