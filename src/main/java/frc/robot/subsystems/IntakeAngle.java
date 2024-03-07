package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeAngle extends SubsystemBase {
    public final TalonFX IndexMotor;

    public IntakeAngle(TalonFX IndexMotor) {
        this.IndexMotor = IndexMotor;
        IndexMotor.setNeutralMode(NeutralModeValue.Brake);
    }

    public void setMotors(double speed) {
        // for setting the speed in the command file
        IndexMotor.setVoltage(speed);
        
    }
}