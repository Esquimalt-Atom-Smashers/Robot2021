package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.ComponentBase;

public class DrivetrainComponent extends ComponentBase {


    public DrivetrainComponent(Robot robot) {
        super(robot);
    }

    @Override
    public void teleopPeriodic() {
        Joystick stick = robot.getJoystic();
        robot.getDifferentialDrive().arcadeDrive(stick.getY(), stick.getX());
    }
    
}
