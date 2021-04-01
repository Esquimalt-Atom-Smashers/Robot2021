package frc.robot;

import frc.robot.events.EventHandler;

public class CLPComponent extends ComponentBase {

    public CLPComponent(Robot robot) {
        super(robot);
        
        robot.setOnButtonPressed(3, EventHandler.combineAndCreate(event ->
            robot.getClpMotors().set(-1)
        , () ->
            robot.getClpMotors().set(0)
        ));
        
    }
    
}
