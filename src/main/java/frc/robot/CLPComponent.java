package frc.robot;

import frc.robot.events.EventHandler;

public class CLPComponent extends ComponentBase {

    public CLPComponent(Robot robot) {
        super(robot);
        
        robot.setOnButtonPressed(3, EventHandler.combine(event ->
            robot.getClpMotor().set(1)
        , () ->
            robot.getClpMotor().set(0)
        ));

        
    }
    
}
