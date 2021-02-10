package frc.robot;

import frc.robot.ComponentBase;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DrivetrainComponent extends ComponentBase {

    // Variables taken from The ArcadeDriveExample
    private final PWMVictorSPX m_leftMotor = new PWMVictorSPX(0);
    private final PWMVictorSPX m_rightMotor = new PWMVictorSPX(1);
    private final DifferentialDrive m_robotDrive = new DifferentialDrive(m_leftMotor, m_rightMotor);
    private final Joystick m_stick = new Joystick(0);
  

    public DrivetrainComponent(Robot robot) {
        super(robot);
    }

    @Override
    public void update() {
        m_robotDrive.arcadeDrive(m_stick.getY(), m_stick.getX());
    }
    
}
