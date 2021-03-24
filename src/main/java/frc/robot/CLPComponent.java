package frc.robot;

public class CLPComponent extends ComponentBase {

    public CLPComponent(Robot robot) {
        super(robot);
    }

    @Override
    public void teleopPeriodic() {
        if (robot.getJoystic().getRawButton(3)) {
            robot.getClpMotor().set(1);
        }
    }
    
}
