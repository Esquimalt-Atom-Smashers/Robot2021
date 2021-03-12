package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

import java.security.KeyStore.TrustedCertificateEntry;

import javax.swing.plaf.TreeUI;

import edu.wpi.first.wpilibj.AnalogInput;


public class MovementComponent extends ComponentBase {

    /** Controls if we use the second joystick to rotate the robot. */
    private static final boolean ALTERNATE_ROTATE = true;

    private final int distanceCM = 500; // Max distance ultrasonic is suggested for in CM
    private final int maxUltraVal = 4095; // Value of ultrasonic analog read when using 5V (Probably 4095)
    private double distConst = maxUltraVal/distanceCM;

    private int timerCount = 0;
    private boolean backwards = false;

    private boolean autoDrive = false;

    private AnalogInput ultrasonic = new AnalogInput(0); // Analog port number

    public MovementComponent(Robot robot) {
        super(robot);
    }

    @Override
    public void teleopPeriodic() {
        Joystick stick = robot.getJoystic();
        robot.move(backwards ? -stick.getY() : stick.getY(), ALTERNATE_ROTATE ? stick.getZ() : stick.getY());
        if (stick.getRawButton(4) && timerCount <= 0) {
            timerCount = 100;
        } else {
            timerCount--;
        }
        //double ultraVal = ultrasonic.getValue(); // Gets distance val from ultrasonic between 0-4095 (0V-5V)
        //double currentDistance = ultraVal / distConst; // Gives dist in CM

        
        //if (stick.getRawButton(4)) { //Autonomous mode switcher, Button 4 is Y
        //    autoDrive = !autoDrive;
        //}

        //double moveY = 0; // Fwd/bkwd movement
        //double moveZ = 0; // rotate movement
        //System.out.println("CD: " + currentDistance + " UltraVal: " + ultraVal + " Stick Y:" + stick.getY());
        // if(currentDistance<=100 && stick.getRawButton(3)){
        //     moveY = -0.5; // Backup half speed
        // } else if (currentDistance > 100) {
        //     moveY = -stick.getY();
        // }
        
        //Regular Driving Mode
        // if (autoDrive == false) {
        //     if (currentDistance >= 200) {
        //         moveY = -stick.getY();
        //     } else if (currentDistance < 50) {
        //         moveY = 0;
        //         if (stick.getRawButton(3)) {
        //             moveY = -0.5;
        //         }
        //     } else if (currentDistance < 200) {
        //         moveY = -stick.getY() / (currentDistance / 50);
        //     }
        //     robot.move(moveY, ALTERNATE_ROTATE ? stick.getZ() : stick.getY());
        // }

        // Autonomous mode with fail safes
        // if (autoDrive == true) {
        //     if (stick.getRawButton(3)) {
        //         moveY = 0;
        //     } else if (stick.getZ() != 0) {
        //         moveZ = stick.getZ();
        //     } else if (currentDistance >= 75) {
        //         moveY = 0.5;
        //     } else if (currentDistance < 75) {
        //         moveZ = 0.5;
        //     }
        //     robot.move(moveY, ALTERNATE_ROTATE ? moveZ : stick.getY());
        // }
        
        // robot.move(moveY, ALTERNATE_ROTATE ? stick.getZ() : stick.getY());
        // robot.move(moveY, ALTERNATE_ROTATE ? moveZ : stick.getY());
    }
    
}
