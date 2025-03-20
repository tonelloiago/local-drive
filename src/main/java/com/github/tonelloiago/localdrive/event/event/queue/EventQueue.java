package com.github.tonelloiago.localdrive.event.event.queue;

import com.github.tonelloiago.localdrive.domain.Event;
import com.github.tonelloiago.localdrive.manager.annotation.EventManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.SynchronousQueue;

//https://www.baeldung.com/java-synchronous-queue
@Component
public class EventQueue {

    private final SynchronousQueue<Event> eventQueue = new SynchronousQueue<>();
    private final List<EventManager> consumers = new ArrayList<>();

    public void publish(Event event) throws InterruptedException {
        if (event == null) return;
        this.eventQueue.put(event);
    }

    @Scheduled(fixedRate = 10000)
    public Event consume() throws InterruptedException {
        System.out.println("COnsumindo evento");
        return this.eventQueue.peek();
    }

    public void subscribeAll(List<EventManager> managers) {
        consumers.addAll(managers);
    }


    //TODO maybe a retry
}
