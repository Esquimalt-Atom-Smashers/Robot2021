// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.io.FileNotFoundException;

import edu.wpi.first.wpilibj.RobotBase;
import frc.robot.autonomous.Instruction;
import frc.robot.autonomous.PatternAutonomous;

/**
 * Do NOT add any static variitables to this class, or any initialization at
 * all. Unless you know what you are doing, do not modify this file except to
 * change the parameter class to the startRobot call.
 */
public final class Main {

  private Main() {
  }

  /**
   * Main initialization function. Do not perform any initialization here.
   *
   * <p>
   * If you change your main robot class, change the parameter type.
   */
  public static void main(String... args) {

    RobotBase.startRobot(() -> {
      Robot robot = new Robot();
      robot.addComponent(new MovementComponent(robot));
      // robot.addComponent(new UltrasonicComponent(robot));
      try {
        robot.addComponent(PatternAutonomous.createFromFile("simple instructions.txt", robot));
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
      return robot;
    });



  }
}
