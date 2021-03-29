package frc.robot;

//import jdk.internal.jshell.tool.resources.l10n; this was giving an error Zev, had to comment it out - Kyle

import edu.wpi.first.wpilibj.XboxController; //need to figure out how to use D-Pad for manually controling the actuators - Kyle

public class ActuatorComponent extends ComponentBase {
    private XboxController xboxcontroller;
    private int ranTimes = 0;
    private boolean retracting = false;
    private final static double MAX_ANGLE = 145;
    private final static double MIN_ANGLE = 30;
    private final static double POSITION_1 = 1; //placeholder
    private final static double POSITION_2 = 2; //placeholder
    private final static double POSITION_3 = 3; //placeholder

    public ActuatorComponent(Robot robot) {
        super(robot);
    }

    // Max val approx. 145°
    // Min val approx 30° ♫

    @Override
    public void teleopPeriodic() {

        //Rightmost actuator
        if (robot.getActuatorServoRight().getAngle() < 30) {
            robot.getActuatorServoRight().setAngle(30);
        }
        if (!retracting && robot.getJoystic().getRawButton(2) && ranTimes % 8 == 0 && robot.getActuatorServoRight().getAngle() < 145) {
            robot.getActuatorServoRight().setAngle(robot.getActuatorServoRight().getAngle() + 1);
            System.out.println("Angle: " + robot.getActuatorServoRight().getAngle());
        } else if (retracting || robot.getActuatorServoRight().getAngle() >= MAX_ANGLE) {
            retracting = true;
            robot.getActuatorServoRight().setAngle(robot.getActuatorServoRight().getAngle() - 0.1);
            if (robot.getActuatorServoRight().getAngle() <= MIN_ANGLE) {
                retracting = false;
            }
        }
            // if (ranTimes % 100 == 0) {
            //     if (robot.getActuatorServoRight().get() >= 0.7) {
            //         retracting = true;
            //         robot.getActuatorServoRight().set(0);
            //     } else if (!retracting) {
            //         robot.getActuatorServoRight().set(robot.getActuatorServoRight().get() + 0.05);
            //     }
            // } ♫
            // if (robot.getActuatorServoRight().get() == 0) {
            //     retracting = false;
            // }
        ranTimes++;
        if (robot.getActuatorServoRight().getAngle() == 145 && ranTimes % 10 == 0) {

        }
        
        //Center actuator
        if (robot.getActuatorServoCenter().getAngle() < 30) {
            robot.getActuatorServoCenter().setAngle(30);
        }
        if (!retracting && robot.getJoystic().getRawButton(2) && ranTimes % 8 == 0 && robot.getActuatorServoCenter().getAngle() < 145) {
            robot.getActuatorServoCenter().setAngle(robot.getActuatorServoCenter().getAngle() + 1);
            System.out.println("Angle: " + robot.getActuatorServoCenter().getAngle());
        } else if (retracting || robot.getActuatorServoCenter().getAngle() >= MAX_ANGLE) {
            retracting = true;
            robot.getActuatorServoCenter().setAngle(robot.getActuatorServoCenter().getAngle() - 0.1);
            if (robot.getActuatorServoCenter().getAngle() <= MIN_ANGLE) {
                retracting = false;
            }
        }
            // if (ranTimes % 100 == 0) {
            //     if (robot.getActuatorServoCenter().get() >= 0.7) {
            //         retracting = true;
            //         robot.getActuatorServoCenter().set(0);
            //     } else if (!retracting) {
            //         robot.getActuatorServoCenter().set(robot.getActuatorServoCenter().get() + 0.05);
            //     }
            // } ♫
            // if (robot.getActuatorServoCenter().get() == 0) {
            //     retracting = false;
            // }
        ranTimes++;
        if (robot.getActuatorServoCenter().getAngle() == 145 && ranTimes % 10 == 0) {

        }

        //Leftmost actuator
        
        if (robot.getActuatorServoLeft().getAngle() < 30) {
            robot.getActuatorServoLeft().setAngle(30);
        }
        if (!retracting && robot.getJoystic().getRawButton(2) && ranTimes % 8 == 0 && robot.getActuatorServoLeft().getAngle() < 145) {
            robot.getActuatorServoLeft().setAngle(robot.getActuatorServoLeft().getAngle() + 1);
            System.out.println("Angle: " + robot.getActuatorServoLeft().getAngle());
        } else if (retracting || robot.getActuatorServoLeft().getAngle() >= MAX_ANGLE) {
            retracting = true;
            robot.getActuatorServoLeft().setAngle(robot.getActuatorServoLeft().getAngle() - 0.1);
            if (robot.getActuatorServoLeft().getAngle() <= MIN_ANGLE) {
                retracting = false;
            }
        }
            // if (ranTimes % 100 == 0) {
            //     if (robot.getActuatorServoLeft().get() >= 0.7) {
            //         retracting = true;
            //         robot.getActuatorServoLeft().set(0);
            //     } else if (!retracting) {
            //         robot.getActuatorServoLeft().set(robot.getActuatorServoLeft().get() + 0.05);
            //     }
            // } ♫
            // if (robot.getActuatorServoLeft().get() == 0) {
            //     retracting = false;
            // }
        ranTimes++;
        if (robot.getActuatorServoLeft().getAngle() == 145 && ranTimes % 10 == 0) {

        }
        //A BUTTON 
        if (xboxcontroller.getAButtonPressed() == true) { //might be getAButton(), getAButtonPressed() or getAButtonReleased()
            robot.getActuatorServoRight().setAngle(POSITION_1);
            robot.getActuatorServoCenter().setAngle(POSITION_1);
            robot.getActuatorServoLeft().setAngle(POSITION_1);
            //or we could also do it like this, might be smoother?
            //robot.getActuatorServo().setAngle(robot.getActuatorServo().getAngle() + (POSITION_1 - robot.getActuatorServo().getAngle()));
            //robot.getActuatorServoCenter().setAngle(robot.getActuatorServoCenter().getAngle() + (POSITION_1 - robot.getActuatorServoCenter().getAngle()));
            //robot.getActuatorServoLeft().setAngle(robot.getActuatorServoLeft().getAngle() + (POSITION_1 - robot.getActuatorServoLeft().getAngle()));
            /*
            */
    }
        //B BUTTON
        if (xboxcontroller.getBButtonPressed() == true) { //might be getAButton(), getAButtonPressed() or getAButtonReleased()
            robot.getActuatorServoRight().setAngle(POSITION_2);
            robot.getActuatorServoCenter().setAngle(POSITION_2);
            robot.getActuatorServoLeft().setAngle(POSITION_2);
    }
        //Y BUTTON
        if (xboxcontroller.getYButtonPressed() == true) { //might be getAButton(), getAButtonPressed() or getAButtonReleased()
            robot.getActuatorServoRight().setAngle(POSITION_3);
            robot.getActuatorServoCenter().setAngle(POSITION_3);
            robot.getActuatorServoLeft().setAngle(POSITION_3);
    }   

    
}

}
