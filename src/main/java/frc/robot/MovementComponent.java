package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.AnalogInput;


public class MovementComponent extends ComponentBase {

    /** Controls if we use the second joystick to rotate the robot. */
    private static final boolean ALTERNATE_ROTATE = true;

    final int distanceCM = 500; // Max distance ultrasonic is suggested for in CM
    final int maxUltraVal = 4095; // Value of ultrasonic analog read when using 5V (Probably 4095)
    double distConst = maxUltraVal/distanceCM;

    AnalogInput ultrasonic = new AnalogInput(0); // Analog port number

    public MovementComponent(Robot robot) {
        super(robot);
    }

    @Override
    public void teleopPeriodic() {
        Joystick stick = robot.getJoystic();
        double ultraVal = ultrasonic.getValue(); // Gets distance val from ultrasonic between 0-4095 (0V-5V)
        double currentDistance = ultraVal / distConst; // Gives dist in CM

        double moveY = 0;
        System.out.println("CD: " + currentDistance + " UltraVal: " + ultraVal);
        if(currentDistance<=100 && stick.getRawButton(3)){
            moveY = -0.5;
        } else if (currentDistance > 100) {
            moveY = -stick.getY();
        }
        
        robot.move(moveY, ALTERNATE_ROTATE ? stick.getZ() : stick.getY());
    }
    
}
