// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.RobotBase;
import frc.robot.servos.ActuatorComponent;

import java.util.function.Supplier;

/**
 * WPILib doesn't want you to add stuff to here, that's because they do many things behind-the-scenes before starting the program.
 * If you want to do code in here do so under Main -> Robot#startRobot(Supplier)
 */
public final class Main {

    private Main() {
    }

    /**
     * Main initialization function.
     */
    public static void main(String... args) {

        RobotBase.startRobot(() -> {
            Robot robot = new Robot();
            robot.addComponent(new MovementComponent(robot));
            robot.addComponent(new ActuatorComponent(robot));
            // robot.addComponent(new CLPComponent(robot));
            return robot;
        });

    }
}
