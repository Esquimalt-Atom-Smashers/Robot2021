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
    // Min val approx 30° ♫ ♫

    @Override
    public void teleopPeriodic() {


        // if (robot.getActuatorServos().getAngle() < 30) {
        //     robot.getActuatorServos().setAngle(30);
        // }
        // if (!retracting && robot.getJoystic().getRawButton(2) && ranTimes % 8 == 0 && robot.getActuatorServos().getAngle() < 145) {
        //     robot.getActuatorServos().setAngle(robot.getActuatorServos().getAngle() + 1);
        //     System.out.println("Angle: " + robot.getActuatorServos().getAngle());
        // } else if (retracting || robot.getActuatorServos().getAngle() >= MAX_ANGLE) {
        //     retracting = true;
        //     robot.getActuatorServos().setAngle(robot.getActuatorServos().getAngle() - 0.1);
        //     if (robot.getActuatorServos().getAngle() <= MIN_ANGLE) {
        //         retracting = false;
        //     }
        // }
        // if (ranTimes % 100 == 0) {
        //     if (robot.getActuatorServos().get() >= 0.7) {
        //         retracting = true;
        //         robot.getActuatorServos().set(0);
        //     } else if (!retracting) {
        //         robot.getActuatorServos().set(robot.getActuatorServos().get() + 0.05);
        //     }
        // } 
        // if (robot.getActuatorServos().get() == 0) {
        //     retracting = false;
        // }



        // ranTimes++;
        // if (robot.getActuatorServos().getAngle() == 145 && ranTimes % 10 == 0) {

        // }
    }
    
}
