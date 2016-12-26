package com.adoptame.andrearodriguez.adoptame.libs.base;

/**
 * Created by andrearodriguez on 7/24/16.
 */
public interface EventBus {
    void register(Object subscriber);
    void unregister(Object subscriber);
    void post(Object event);
}
