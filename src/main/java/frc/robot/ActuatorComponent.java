package frc.robot;

public class ActuatorComponent extends ComponentBase {

    private int ranTimes = 0;
    private boolean retracting = false;
    private final static double MAX_ANGLE = 145;
    private final static double MIN_ANGLE = 30;

    public ActuatorComponent(Robot robot) {
        super(robot);
    }

    // Max val approx. 145°
    // Min val approx 30° ♫

    @Override
    public void teleopPeriodic() {
        if (robot.getActuatorServo().getAngle() < 30) {
            robot.getActuatorServo().setAngle(30);
        }
        if (!retracting && robot.getJoystic().getRawButton(2) && ranTimes % 8 == 0 && robot.getActuatorServo().getAngle() < 145) {
            robot.getActuatorServo().setAngle(robot.getActuatorServo().getAngle() + 1);
            System.out.println("Angle: " + robot.getActuatorServo().getAngle());
        } else if (retracting || robot.getActuatorServo().getAngle() >= MAX_ANGLE) {
            retracting = true;
            robot.getActuatorServo().setAngle(robot.getActuatorServo().getAngle() - 0.1);
            if (robot.getActuatorServo().getAngle() <= MIN_ANGLE) {
                retracting = false;
            }
        }
            // if (ranTimes % 100 == 0) {
            //     if (robot.getActuatorServo().get() >= 0.7) {
            //         retracting = true;
            //         robot.getActuatorServo().set(0);
            //     } else if (!retracting) {
            //         robot.getActuatorServo().set(robot.getActuatorServo().get() + 0.05);
            //     }
            // } ♫
            // if (robot.getActuatorServo().get() == 0) {
            //     retracting = false;
            // }
        ranTimes++;
        if (robot.getActuatorServo().getAngle() == 145 && ranTimes % 10 == 0) {

        }
    }
    
}
