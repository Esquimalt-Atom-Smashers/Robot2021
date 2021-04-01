package frc.robot.events;

import java.util.function.Consumer;
import java.util.function.Predicate;

public interface EventHandler<T extends Event> {

    void receive(T event);

    default void otherwise() {

    }

    default EventHandler<T> delay(int delay) {
        return Events.delay(delay, this);
    }
    default EventHandler<T> filter(Predicate<T> tester) {
        return Events.filter(tester, this);
    }

    static <T extends Event> EventHandler<T> combineAndCreate(Consumer<T> receiver, Runnable otherwise) {
        return new EventHandler<>(){

            @Override
            public void receive(T event) {
                receiver.accept(event);
            }

            @Override
            public void otherwise() {
                otherwise.run();
            }
            
        };
    }
    
}
