package frc.robot.events;

import frc.robot.Robot;

public abstract class Event {
    
    private final Robot robot;

    Event(Robot robot) {
        this.robot = robot;
    }

    public Robot getRobot() {
        return robot;
    }

}
