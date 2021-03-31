package frc.robot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class CLPMotors {

    private final List<WPI_VictorSPX> motors = new ArrayList<>();

    public CLPMotors(int... ports) {
        List<WPI_VictorSPX> motors = new ArrayList<>();
        for (int port : ports) {
            motors.add(new WPI_VictorSPX(port));
        }
        this.motors.addAll(motors);
    }
    public CLPMotors(WPI_VictorSPX... motors) {
        this(Arrays.asList(motors));
    }
    public CLPMotors(Collection<WPI_VictorSPX> motors) {
        this.motors.addAll(motors);
    }

    public void set(double value) {
        this.motors.forEach(motor -> motor.set(value));
    }
    
}
