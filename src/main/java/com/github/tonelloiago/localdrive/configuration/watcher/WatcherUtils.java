package com.github.tonelloiago.localdrive.configuration.watcher;



import java.lang.reflect.Method;

public class WatcherUtils {

    public static void runWatcher(Object target) {
        var cls = target.getClass();
        var annotation = cls.getAnnotation(Watcher.class);
        for(Method method : cls.getDeclaredMethods()) {
            if(annotation.methodToRun().equals(method.getName())) {
                var task = getRunnable(target, method);
                Thread.startVirtualThread(task);
            }
        }
    }

    private static Runnable getRunnable( Object target, Method method) {
        return () -> {
            try {
                method.invoke(target);
            } catch (Exception e) {
                System.err.println("Error in virtual thread: " + e.getMessage());
                throw new RuntimeException(e);
            }
        };
    }

}
