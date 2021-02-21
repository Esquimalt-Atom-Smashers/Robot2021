package frc.robot.autonomous;

/**
 * This class is used to create an instruction which can be loaded into the PatternAutonomous class which will execute it.
 */
public class Instruction {

    /** How long the robot will wait in 1/2 ms before moving and rotating the specified amounts. */
    private int waitBefore;
    /** How far the robot will move. 2ms per 1 unit of movement. */
    private double moveAmount;
    /** How much the robot will rotate. 2ms per 1 unit of rotation. */
    private double rotateAmount;
    /** How long the robot will wait in 1/2 ms after moving and rotating. */
    private int waitAfter;

    public Instruction(double moveAmount, double rotateAmount, int waitBefore, int waitAfter) {
        this.moveAmount = moveAmount;
        this.rotateAmount = rotateAmount;
        this.waitBefore = waitBefore;
        this.waitAfter = waitAfter;
    }

    public Instruction(double moveAmount, double rotateAmount) {
        this(moveAmount, rotateAmount, 0, 0);
    }

    public double getMoveAmount() {
        return moveAmount;
    }
    public double getRotateAmount() {
        return rotateAmount;
    }
    public int getWaitBefore() {
        return waitBefore;
    }
    public int getWaitAfter() {
        return waitAfter;
    }

    public void setMoveAmount(double moveAmount) {
        this.moveAmount = moveAmount;
    }
    public void setRotateAmount(double rotateAmount) {
        this.rotateAmount = rotateAmount;
    }
    public void setWaitBefore(int waitBefore) {
        this.waitBefore = waitBefore;
    }
    public void setWaitAfter(int waitAfter) {
        this.waitAfter = waitAfter;
    }
}


