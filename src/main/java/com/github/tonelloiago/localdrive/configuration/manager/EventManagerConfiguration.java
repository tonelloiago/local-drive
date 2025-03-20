package com.github.tonelloiago.localdrive.configuration.manager;

import com.github.tonelloiago.localdrive.event.event.queue.EventQueue;
import com.github.tonelloiago.localdrive.manager.EventManager;
import com.github.tonelloiago.localdrive.manager.annotation.Manager;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventManagerConfiguration {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private EventQueue eventQueue;

    @PostConstruct
    public void init() {
        var beans = applicationContext.getBeansWithAnnotation(Manager.class);
        var managers = beans.values().stream()
                .filter(EventManager.class::isInstance)
                .map(bean -> (EventManager) bean)
                .toList();
        eventQueue.subscribeAll(managers);
        eventQueue.consume();
    }

}
