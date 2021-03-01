package frc.robot;

import edu.wpi.first.wpilibj.AnalogInput;

/*
    I realize we might be using multiple of these so maybe better make a creator for them
*/

public class UltrasonicComponent {
    
    final int distanceCM = 500; // Max distance ultrasonic is suggested for in CM
    final int maxUltraVal = 4095; // Value of ultrasonic analog read when using 5V (Probably 4095)
    double distConst = maxUltraVal/distanceCM;

    AnalogInput ultrasonic = new AnalogInput(0); // Analog port number
    
    double ultraVal = ultrasonic.getValue(); // Gets distance val from ultrasonic between 0-4095 (0V-5V)
    double currentDistance = ultraVal / distConst; // Gives dist in CM

}
