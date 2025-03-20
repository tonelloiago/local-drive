package com.github.tonelloiago.localdrive.watcher.watcher.annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.Executors;

public class WatcherUtils {

    private static final Logger log = LoggerFactory.getLogger(WatcherUtils.class);

    public static void runWatchers(Object... targets) {
        Arrays.stream(targets).forEach(target -> {
            var cls = target.getClass();
            var annotation = cls.getAnnotation(Watcher.class);
            var methodName = annotation.methodToRun();
            try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
                for (var method : cls.getDeclaredMethods()) {
                    if (methodName.equals(method.getName())) {
                        executor.submit(getRunnable(target, method));
                        break;
                    }
                }
            }
        });
    }

    private static Runnable getRunnable(Object target, Method method) {
        return () -> {
            try {
                method.invoke(target);
            } catch (Exception e) {
                log.error("Error in virtual thread (method: {}): ", method.getName(), e); // Log with stack trace
            }
        };
    }
}