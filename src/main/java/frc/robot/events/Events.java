package frc.robot.events;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

/**
 * This class contains utility methods for creating event handers.
 */
public final class Events {

    /**
     * TODO this comment
     * 
     */
    public static <T extends Event> EventHandler<T> delay(int delay, final EventHandler<T> handler) {
        AtomicInteger times = new AtomicInteger();
        return new EventHandler<T>(){

            @Override
            public void receive(T event) {
                if (delay >= times.get()) {
                    handler.receive(event);
                } else {
                    handler.otherwise();
                    times.set(times.get() + 1);
                }
            }

            @Override
            public void otherwise() {
                handler.otherwise();
            }
            
        };
    }

    public static <T extends Event> EventHandler<T> filter(Predicate<T> tester, final EventHandler<T> handler) {
        return new EventHandler<T>(){

            @Override
            public void receive(T event) {
                if (tester.test(event)) {
                    handler.receive(event);
                } else {
                    handler.otherwise();
                }
            }

            @Override
            public void otherwise() {
                handler.otherwise();
            }
            
        };
    }
    
}