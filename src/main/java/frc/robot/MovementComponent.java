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

        double multiplier = 1;
        double rightspeed=robot.getRightMotor().get();
        double leftspeed=robot.getLeftMotor().get();
        double speed;
        speed=(rightspeed+leftspeed)*5;
        if (currentDistance <= 125 / Math.max(1,-(10+speed))) {
            multiplier = currentDistance / (500 / -speed);
        }

        System.out.println("Dist: " + currentDistance + " Multiplier: " + multiplier + " Spd: " + speed);
        double moveY = -stick.getY();
        if (moveY > 0) {
            moveY *= multiplier;
        }

        

        if (moveY > 0 && currentDistance <= 50/-speed) {
            moveY = -moveY;
        }
        
        robot.move(moveY, ALTERNATE_ROTATE ? stick.getZ() : stick.getY());
    }
    
}
