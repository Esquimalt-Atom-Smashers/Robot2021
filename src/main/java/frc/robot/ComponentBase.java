package frc.robot;

/**
 * This class is used to create a component which interacts with the Robot specified in the contsrtucor.
 * To implement this class create a class which extends this and implement all the methods.
 * These methods will be called when their corresponding methods are called on the Robot class.
 */
public abstract class ComponentBase {

    protected final Robot robot;

    public ComponentBase(Robot robot) {
        this.robot = robot;
    }

    public Robot getRobot() {
        return robot;
    }

    /**
     * This method will get called by the Robot.java class whenever it's teleopPeriodic methods is called.
     */
    public void teleopPeriodic() {}
    
}
