package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.LauncherConstants;

public class IntakeUppies extends SubsystemBase {
    private final TalonFX IndexMotor;
    public DutyCycleEncoder IntakeEncoder;


    public IntakeUppies(TalonFX IndexMotor, int channelA) {
        this.IndexMotor = IndexMotor;
        this.IntakeEncoder = new DutyCycleEncoder(channelA);

        IndexMotor.setNeutralMode(NeutralModeValue.Brake);
    }

    public void setMotors(double speed) {
        // for setting the speed in the command file
        IndexMotor.setControl(LauncherConstants.kLauncherVoltageOut.withOutput(speed));
        SmartDashboard.putNumber("intakeEncoder", IntakeEncoder.getAbsolutePosition());  

        
    }
}