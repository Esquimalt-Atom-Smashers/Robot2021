package frc.robot.autonomous;

import java.util.ArrayList;
import java.util.Arrays;

import frc.robot.ComponentBase;
import frc.robot.Robot;

/**
 * This class takes a list of {@link frc.robot.autonomous.Instructions} in its constructor then works its way through them until there are none left.
 */
public class PatternAutonomous extends ComponentBase {

    private final ArrayList<Instruction> instructions = new ArrayList<>();

    private Instruction currentInstruction;
    private int waitBefore = 0;
    private double moveAmount = 0;
    private double rotateAmount = 0;
    private int waitAfter = 0;

    public PatternAutonomous(Robot bot, Instruction... defaultInstructions) {
        super(bot);
        instructions.addAll(Arrays.asList(defaultInstructions));
    }

    @Override
    public void teleopPeriodic() {
        if (currentInstruction == null && instructions.size() > 0) {
            currentInstruction = instructions.get(0);
            instructions.remove(0);
            if (currentInstruction != null) {
                // Gather values for variables from new currentInstruciton.
                waitBefore = currentInstruction.getWaitBefore();
                moveAmount = currentInstruction.getMoveAmount();
                rotateAmount = currentInstruction.getRotateAmount();
                waitAfter = currentInstruction.getWaitAfter();
            } else {
                // If the currentInstruction in slot 0 is null then we need to re-run this to until either we get one which isnt null or the list is emptied.
                teleopPeriodic();
                return;
            }
        } else if (currentInstruction == null) {
            // If we have ran out of instructions then we shouldnt run anymore code.
            return;
        }
        if (currentInstruction != null && waitBefore == 0 && moveAmount == 0 && rotateAmount == 0 && waitAfter == 0) {
            currentInstruction = null;
        } else if (currentInstruction != null) {
            // Our first objective is to drain the waitBefore variable, this is done by removing 1 from it until it reaches 0.
            if (waitBefore > 0) {
                waitBefore--;
            // This next code chunk will move the robot by the currnet instruction's moveAmount and rotateAmounts, if these variables are above 1 then only 1 will be used until it reaches below 1.
            } else if (moveAmount > 0 || rotateAmount > 0) {
                double moveNow = 0;
                double rotateNow = 0;
                if (moveAmount > 0) {
                    if (moveAmount >= 1) {
                        moveAmount--;
                        moveNow = 1;
                    } else {
                        moveNow = moveAmount;
                        moveAmount = 0;
                    }
                }
                if (rotateAmount > 0) {
                    if (rotateAmount >= 1) {
                        rotateAmount--;
                        rotateNow = 1;
                    } else {
                        rotateNow = rotateAmount;
                        rotateAmount = 0;
                    }
                }
                robot.move(moveNow, rotateNow);
            // Similar to the waitBefore code chunk but this one is only used if moveAmount, rotateAmount, and waitBefore are all equal to 0.
            } else if (waitAfter > 0) {
                waitAfter--;
            }
        }
    }

    public ArrayList<Instruction> getInstructions() {
        return instructions;
    }
    
}
