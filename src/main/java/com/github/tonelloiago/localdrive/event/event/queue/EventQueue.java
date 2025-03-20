package com.github.tonelloiago.localdrive.event.event.queue;

import com.github.tonelloiago.localdrive.domain.Event;
import com.github.tonelloiago.localdrive.manager.EventManager;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.SynchronousQueue;

@Component
public class EventQueue {

    private final SynchronousQueue<Event> eventQueue = new SynchronousQueue<>();
    private final List<EventManager> consumers = new ArrayList<>();

    public void publish(Event event) throws InterruptedException {
        if (event == null) return;
        System.out.println("📨 Publicando evento: " + event);
        this.eventQueue.put(event);
    }

    public void consume() {
        Thread.startVirtualThread(() -> {
            try {
                while (true) {
                    var event = this.eventQueue.take();
                    consumers.stream()
                        .filter(consumer -> !event.publisher().equals(consumer.getClass()))
                        .forEach(consumer ->
                                {
                                    System.out.println("📢 Evento entregue a: " + consumer.getClass().getSimpleName());
                                    event.eventKind().getAction().accept(consumer);
                                });
                }
            }catch (InterruptedException ex) {
                System.out.println();
            }
        });
    }

    public void subscribeAll(List<EventManager> managers) {
        consumers.addAll(managers);
    }

    //TODO maybe a retry
}
