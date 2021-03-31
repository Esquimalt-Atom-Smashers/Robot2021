package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

import java.security.KeyStore.TrustedCertificateEntry;

import javax.swing.plaf.TreeUI;

import edu.wpi.first.wpilibj.AnalogInput;


public class MovementComponent extends ComponentBase {

    /** Controls if we use the second joystick to rotate the robot. */
    private static final boolean ALTERNATE_ROTATE = true;

    public MovementComponent(Robot robot) {
        super(robot);
    }

    @Override
    public void teleopPeriodic() {
        Joystick stick = robot.getJoystick();

        // robot.move(stick, ALTERNATE_ROTATE ? stick.getZ() : stick.getY());
        robot.move(0, 0);

        
    }
    
}
