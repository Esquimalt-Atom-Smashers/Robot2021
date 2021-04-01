// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.clp.CLPMotors;
import frc.robot.events.ButtonEvent;
import frc.robot.events.DpadEvent;
import frc.robot.events.EventHandler;
import frc.robot.events.SingleStickEvent;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.servos.Servos;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

    // Some important variables
    private static final String kDefaultAuto = "Default";
    private static final String kCustomAuto = "My Auto";

    /**
     * This variable controls whether or not the robot will ovveride the
     * JOYSTICK_SLOT and instead looks through slots 0-10 looking for a valid
     * joystick.
     */
    private static final boolean FIND_JOYSTICK_SLOT = false;
    /**
     * This variable controls the usb slot we look for the joystick in, if
     * FIND_JOYSTICK_SLOT is true then this will only be used as a backup.
     */
    private static final int DEFAULT_JOYSTICK_SLOT = 3;

    private final PWMVictorSPX m_leftMotor = new PWMVictorSPX(1);
    private final PWMVictorSPX m_rightMotor = new PWMVictorSPX(3);
    private final DifferentialDrive m_robotDrive = new DifferentialDrive(m_leftMotor, m_rightMotor);

    private Joystick m_stick;
    private XboxController xboxController = new XboxController(DEFAULT_JOYSTICK_SLOT);

    private final CLPMotors clpMotors = new CLPMotors(6);
    private final Servos actuatorServos = new Servos(7, 8, 9, 0);

    /***************************
     * Event Variables
     ***************************/
    private final HashMap<Integer, EventHandler<ButtonEvent>> eventMap = new HashMap<>();
    private EventHandler<SingleStickEvent> leftStickHandler;
    private EventHandler<SingleStickEvent> rightStickHandler;
    private EventHandler<DpadEvent> dpadHandler;

    private String m_autoSelected;
    private final SendableChooser<String> m_chooser = new SendableChooser<>();

    private final ArrayList<ComponentBase> components = new ArrayList<>();

    private boolean disabled = true;

    /**
     * This function is run when the robot is first started up and should be used
     * for any initialization code.
     */
    @Override
    public void robotInit() {
        m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
        m_chooser.addOption("My Auto", kCustomAuto);
        SmartDashboard.putData("Auto choices", m_chooser);
        if (FIND_JOYSTICK_SLOT) {
            // This loop looks for a joystick in slots 0-10 breaking if it finds one and
            // using the slot specified in DEFAULT_JOYSTICK_SLOT if none is found.
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
     * This function is called every robot packet, no matter the mode. Use this for
     * items like diagnostics that you want ran during disabled, autonomous,
     * teleoperated and test.
     *
     * <p>
     * This runs after the mode specific periodic functions, but before LiveWindow
     * and SmartDashboard integrated updating.
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
     * This autonomous (along with the chooser code above) shows how to select
     * between different autonomous modes using the dashboard. The sendable chooser
     * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
     * remove all of the chooser code and uncomment the getString line to get the
     * auto name from the text box below the Gyro
     *
     * <p>
     * You can add additional auto modes by adding additional comparisons to the
     * switch structure below with additional strings. If using the SendableChooser
     * make sure to add them to the chooser code above as well.
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
    public void teleopInit() {
    }

    /** This function is called periodically during operator control. */
    @Override
    public void teleopPeriodic() {

        // This loop is used to run the teleopPeriodic method in the ComponentBases
        // stored in the components.
        components.forEach(ComponentBase::teleopPeriodic);

        // This loop is used to properly activate the event handlers in the button map.
        for (int key : eventMap.keySet()) {
            if (m_stick.getRawButton(key)) {
                eventMap.get(key).receive(new ButtonEvent(this, m_stick, key));
            } else {
                eventMap.get(key).otherwise();
            }
        }

        if (leftStickHandler != null) {
            if (m_stick.getY() != 0 || m_stick.getX() != 0) {
                leftStickHandler.receive(new SingleStickEvent(this, m_stick, m_stick.getY(), m_stick.getX()));
            } else {
                leftStickHandler.otherwise();
            }
        }

        if (rightStickHandler != null) {
            if (m_stick.getY(Hand.kLeft) != 0 || m_stick.getX(Hand.kLeft) != 0) {
                rightStickHandler.receive(
                        new SingleStickEvent(this, m_stick, m_stick.getY(Hand.kLeft), m_stick.getX(Hand.kLeft)));
            } else {
                rightStickHandler.otherwise();
            }
        }

        if (dpadHandler != null) {
            if (m_stick.getPOV() != -1) {
                dpadHandler.receive(new DpadEvent(this, m_stick, m_stick.getPOV()));
            } else {
                dpadHandler.otherwise();
            }
        }

    }

    /** This function is called once when the robot is disabled. */
    @Override
    public void disabledInit() {
        disabled = true;
    }

    /** This function is called periodically when disabled. */
    @Override
    public void disabledPeriodic() {
    }

    /** This function is called once when test mode is enabled. */
    @Override
    public void testInit() {
    }

    /** This function is called periodically during test mode. */
    @Override
    public void testPeriodic() {
    }

    /**
     * This method is used to add a component to the Robot.
     * 
     * @param component - The component you are adding to the robot.
     */
    public void addComponent(ComponentBase component) {
        components.add(component);
    }

    /**
     * This method moves the robot using the DifferentialDrive by the given x and y
     * 
     * @param moveAmount the amount the robot will move
     * @param rotation   the amount the robot will rotate
     */
    public void move(double moveAmount, double rotation) {
        m_robotDrive.arcadeDrive(moveAmount, rotation);
    }

    /**
     * 
     * @return The joystick which is created from the slot: JOYSTICK_SLOT
     */
    public Joystick getJoystick() {
        return m_stick;
    }

    public XboxController getXboxController() {
        return xboxController;
    }

    /**
     * 
     * @return The Differential Drive containing the drive base's left and right
     *         motors.
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

    public CLPMotors getClpMotors() {
        return clpMotors;
    }

    public Servos getActuatorServos() {
        return actuatorServos;
    }

    public void setOnButtonPressed(int button, EventHandler<ButtonEvent> handler) {
        eventMap.put(button, handler);
    }

    public Optional<EventHandler<ButtonEvent>> getOnButtonPressed(int button) {
        if (eventMap.containsKey(button)) {
            return Optional.of(eventMap.get(button));
        } else {
            return Optional.empty();
        }
    }

    public void setOnLeftStickMoved(EventHandler<SingleStickEvent> handler) {
        this.leftStickHandler = handler;
    }

    public void setOnRightStickMoved(EventHandler<SingleStickEvent> handler) {
        this.rightStickHandler = handler;
    }

    public void setOnDpadMoved(EventHandler<DpadEvent> dpadEventHandler) {
        this.dpadHandler = dpadEventHandler;
    }

}
