package frc.robot.events;

import java.util.function.Consumer;

public interface EventHandler<T extends Event> {

    void receive(T event);

    default void otherwise() {

    }

    public static <T extends Event> EventHandler<T> combine(Consumer<T> receiver, Runnable otherwise) {
        return new EventHandler<T>(){

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
