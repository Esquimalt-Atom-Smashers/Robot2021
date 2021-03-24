// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.ArrayList;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  
  // Some important variables
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";

  /** This variable controls whether or not the robot will ovveride the JOYSTICK_SLOT and instead looks through slots 0-10 looking for a valid joystick. */
  private static final boolean FIND_JOYSTICK_SLOT = false;
  /** This variable controls the usb slot we look for the joystick in, if FIND_JOYSTICK_SLOT is true then this will only be used as a backup. */
  private static final int DEFAULT_JOYSTICK_SLOT = 3;

  private final PWMVictorSPX m_leftMotor = new PWMVictorSPX(1);
  private final PWMVictorSPX m_rightMotor = new PWMVictorSPX(3);
  private final DifferentialDrive m_robotDrive = new DifferentialDrive(m_leftMotor, m_rightMotor);
  private Joystick m_stick;

  private final WPI_VictorSPX clpMotor = new WPI_VictorSPX(2);
  private final Servo actuatorServo = new Servo(0);

  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  private final ArrayList<ComponentBase> components = new ArrayList<>();

  private boolean disabled = true;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    if (FIND_JOYSTICK_SLOT) {
      // This loop looks for a joystick in slots 0-10 breaking if it finds one and using the slot specified in DEFAULT_JOYSTICK_SLOT if none is found.
      for (int i = 0; i < 10; i++) {
        m_stick = new Joystick(i);
        if (m_stick != null) {
          break;
        } else if (i == 9) {
          m_stick = new Joystick(DEFAULT_JOYSTICK_SLOT);
        }
      }
    } else {
      m_stick = new Joystick(DEFAULT_JOYSTICK_SLOT);
    }
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    if (disabled) {
      disabled = false;
      for (ComponentBase component : components) {
        component.enabled();
      }
    }
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {}

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    
    // This loop is used to run the teleopPeriodic method in the ComponentBases stored in the components.
    for (ComponentBase base : components) {
      base.teleopPeriodic();
    }

  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {
    disabled = true;
  }

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /**
   * This method is used to add a component to the Robot.
   * @param component - The component you are adding to the robot.
   */
  public void addComponent(ComponentBase component) {
    components.add(component);
  }

  /**
   * This method moves the robot using the DifferentialDrive by the given x and y
   * @param moveAmount the amount the robot will move
   * @param rotation the amount the robot will rotate
   */
  public void move(double moveAmount, double rotation) {
    m_robotDrive.arcadeDrive(moveAmount, rotation);
  }

  /**
   * 
   * @return The joystick which is created from the slot: JOYSTICK_SLOT
   */
  public Joystick getJoystic() {
    return m_stick;
  }

  /**
   * 
   * @return The Differential Drive containing the drive base's left and right motors.
   */
  public DifferentialDrive getDifferentialDrive() {
    return m_robotDrive;
  }

  public PWMVictorSPX getLeftMotor() {
    return m_leftMotor;
  }
  public PWMVictorSPX getRightMotor() {
    return m_rightMotor;
  }

  public WPI_VictorSPX getClpMotor() {
    return clpMotor;
  }

  public Servo getActuatorServo() {
    return actuatorServo;
  }

}
