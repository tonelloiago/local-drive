package com.github.tonelloiago.localdrive.configuration.event.queue;

import com.github.tonelloiago.localdrive.domain.Event;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.SynchronousQueue;

//https://www.baeldung.com/java-synchronous-queue
@Component
public class EventQueue {

    private final SynchronousQueue<Event> eventQueue = new SynchronousQueue<>();

    public void publish(Event event) throws InterruptedException {
        if (event == null) return;
        this.eventQueue.put(event);
    }

    public Event consume() throws InterruptedException {
        return this.eventQueue.take();
    }
}
