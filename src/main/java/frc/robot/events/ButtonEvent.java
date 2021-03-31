package frc.robot.events;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Robot;

public class ButtonEvent extends JoystickEvent {

    public ButtonEvent(Robot robot, Joystick stick, int pressed) {
        super(robot, stick);
    }
    
}
