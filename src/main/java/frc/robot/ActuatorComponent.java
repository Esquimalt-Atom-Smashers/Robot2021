package frc.robot;

public class ActuatorComponent extends ComponentBase {

    private int ranTimes = 0;

    private static final double MAX_ANGLE = 145;
    private static final double MIN_ANGLE = 30;
    private static final double MOVE_AMOUNT = 0.1;
    private static final int    DELAY = 30 * 6;

    public ActuatorComponent(Robot robot) {
        super(robot);

        robot.getActuatorServos().setAngle(30);

        robot.setOnDpadMoved(event -> {
            if (ranTimes >= DELAY) {
                double eventVal = event.getValue();
                double angle = robot.getActuatorServos().getAngle();
                if (eventVal == 0) {
                    if (angle < MAX_ANGLE) {
                        robot.getActuatorServos().setAngle(robot.getActuatorServos().getAngle() + MOVE_AMOUNT);
                    }
                } else if (eventVal == 90 && angle > MIN_ANGLE) {
                    robot.getActuatorServos().setAngle(robot.getActuatorServos().getAngle() - MOVE_AMOUNT);
                }
            }
            ranTimes++;
        });

    }

    
}
