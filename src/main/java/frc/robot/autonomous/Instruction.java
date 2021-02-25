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

    @Override
    public String toString() {
        return "Instruction@" + hashCode() + "{moveAmount: " + moveAmount + ", rotateAmount" + rotateAmount
            + "";
    }

    public static Instruction fromLine(String line) {
        line = line.trim().toLowerCase();
        double moveAmount = 0;
        double rotateAmount = 0;
        int wait = 0;
        if (line.contains("move")) {
            String portion = line.substring(line.indexOf("move") + 4);
            Double amount = firstNumber(portion);
            if (amount != null) {
                moveAmount = amount;
            }
        }
        if (line.contains("rotate")) {
            String portion = line.substring(line.indexOf("rotate") + 6);
            Double amount = firstNumber(portion);
            if (amount != null) {
                rotateAmount = amount;
            }
        }
        if (line.contains("wait")) {
            String portion = line.substring(line.indexOf("wait") + 4);
            Double amount = firstNumber(portion);
            if (amount != null) {
                wait = amount.intValue();
            }
        }
        return new Instruction(moveAmount, rotateAmount, wait, 0);
    }

    private static Double firstNumber(String line) {
        line = line.trim();
        String[] spaceSplit = line.split(" ");
        if (spaceSplit.length >= 1) {
            try {
                return Double.parseDouble(spaceSplit[0]);
            } catch (Exception ignore) {
                return null;
            }
        }
        return null;
    }

}


