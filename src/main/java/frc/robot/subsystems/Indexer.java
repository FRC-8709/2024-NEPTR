package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.LauncherConstants;

public class Indexer extends SubsystemBase {
    private final TalonFX IndexMotor;
    public final DigitalInput IndexerSensor;
    public final DigitalInput frontSensor;
    public boolean isTriggered = false;
    public boolean frontIsTriggered = false;


    public Indexer(TalonFX IndexMotor, int limitChannel, DigitalInput frontSensor) {
        this.IndexMotor = IndexMotor;
        this.frontSensor = frontSensor;
        this.IndexerSensor = new DigitalInput(limitChannel);
        IndexMotor.setNeutralMode(NeutralModeValue.Coast);
    }

    public void setMotors(double speed) {
        // for setting the speed in the command file
        IndexMotor.setControl(LauncherConstants.kLauncherVoltageOut.withOutput(speed));

        if (IndexerSensor.get()) {
            isTriggered = true;
        } else {
            isTriggered = false;
        }

        if (frontSensor.get()) {
            frontIsTriggered = true;
        } else {
            frontIsTriggered = false;
        }
    SmartDashboard.putBoolean("IndexerSensor", IndexerSensor.get());  
        
    }
}