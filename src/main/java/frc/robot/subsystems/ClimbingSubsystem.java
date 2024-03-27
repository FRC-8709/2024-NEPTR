package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimbingConstants;

public class ClimbingSubsystem extends SubsystemBase {
    public final TalonFX climbLeft;
    public final TalonFX climbRight;

    public ClimbingSubsystem(TalonFX climbLeft, TalonFX climbRight) {
        this.climbLeft = climbLeft;
        this.climbRight = climbRight;
        climbLeft.setNeutralMode(NeutralModeValue.Brake);
        climbRight.setNeutralMode(NeutralModeValue.Brake);
    }

    public void setLeftMotor(double speed) {
        // for setting the speed in the command file
        climbLeft.setControl(ClimbingConstants.kClimbingVoltageOut.withOutput(speed));
    }
    public void setRightMotor(double speed) {
        // for setting the speed in the command file
        climbRight.setControl(ClimbingConstants.kClimbingVoltageOut.withOutput(speed));
    }
    @Override
    public void periodic(){
       var temp = climbLeft.getRotorPosition();
       //103 max
       SmartDashboard.putNumber("climbLeftTicks", temp.getValueAsDouble());
    }
}