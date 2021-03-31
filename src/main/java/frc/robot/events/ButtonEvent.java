package frc.robot.events;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Robot;

public class ButtonEvent extends JoystickEvent {

    private final int button;

    public ButtonEvent(Robot robot, Joystick stick, int pressed) {
        super(robot, stick);
        this.button = pressed;
    }

    public int getButton() {
        return button;
    }
    
}
