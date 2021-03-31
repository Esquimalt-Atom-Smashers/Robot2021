package frc.robot.events;

import frc.robot.Robot;

abstract class Event {
    
    private final Robot robot;

    Event(Robot robot) {
        this.robot = robot;
    }

}
