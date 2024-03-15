package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeFeed extends SubsystemBase {
    public final TalonFX feedMotor;
    public final DigitalInput feedSensor;
    public boolean isTriggered = false;

    public IntakeFeed(TalonFX feedMotor, DigitalInput limitChannel) {
        this.feedMotor = feedMotor;
        this.feedSensor = limitChannel;
        feedMotor.setNeutralMode(NeutralModeValue.Coast);
    }

    public void setMotors(double speed) {
        // for setting the speed in the command file
        feedMotor.setVoltage(speed);
        if (feedSensor.get()) {
            isTriggered = true;
        } else {
            isTriggered = false;
        }
        
    }
}