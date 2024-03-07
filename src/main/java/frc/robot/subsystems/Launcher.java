package frc.robot.subsystems;

import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.LauncherConstants;

public class Launcher extends SubsystemBase {
    public final TalonFX masterMotor;
    public final TalonFX followerMotor;

    public Launcher(TalonFX masterMotor, TalonFX followerMotor) {
        this.masterMotor = masterMotor;
        this.followerMotor = followerMotor;
        masterMotor.setNeutralMode(NeutralModeValue.Brake);
        followerMotor.setNeutralMode(NeutralModeValue.Brake);
    }

    public void setMotors(double speed) {

        // for setting the speed in the command file
        // motors going opposite directions, change to false if they need to go the same direction for any reason
        masterMotor.setControl(LauncherConstants.kLauncherVoltageOut.withOutput(speed));
        followerMotor.setControl(LauncherConstants.kLauncherVoltageOut.withOutput(-speed));


    }
}