package frc.robot.commands;

import com.ctre.phoenix6.mechanisms.swerve.SwerveModule.DriveRequestType;
import com.ctre.phoenix6.mechanisms.swerve.SwerveRequest;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.ClimbingSubsystem;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Launcher;
import frc.robot.subsystems.swerveTurnSubsys;
import frc.robot.CommandSwerveDrivetrain;
import frc.robot.LimelightHelpers;
import frc.robot.generated.TunerConstants;

public class swerveTurn extends Command{
    private final Joystick leftJoystick;
    public final CommandSwerveDrivetrain drivetrain = TunerConstants.DriveTrain;
    private double MaxAngularRate = 1.5 * Math.PI;
    private double MaxSpeed = 6;
    private final SwerveRequest.FieldCentric drive = new SwerveRequest.FieldCentric()
      .withDeadband(MaxSpeed * 0.075).withRotationalDeadband(MaxAngularRate * 0.1) // Add a 10% deadband
      .withDriveRequestType(DriveRequestType.OpenLoopVoltage); // I want field-centric
                                                               // driving in open loop
    private final SwerveRequest.SwerveDriveBrake brake = new SwerveRequest.SwerveDriveBrake();
    



    //https://docs.limelightvision.io/docs/docs-limelight/tutorials/tutorial-swerve-aiming-and-ranging
    //https://github.com/FRC1884/season2024/blob/testing/src/main/java/frc/robot/subsystems/Vision/Vision.java
    

    public swerveTurn(swerveTurnSubsys subsystem,Joystick leftJoystick) {
        
        this.leftJoystick = leftJoystick;
        addRequirements(subsystem);
    }

    @Override
    public void execute(){
    final JoystickButton b = new JoystickButton(leftJoystick, 4);
    LimelightHelpers.setPriorityTagID("", 18);
    boolean tv = LimelightHelpers.getTV("");
    boolean button = false;
    DriverStation.reportError("TV " + tv, false);
    button = b.getAsBoolean();

    if(tv) {
      drivetrain.applyRequest(() -> drive.withRotationalRate(-0.5 * MaxAngularRate));
      DriverStation.reportError("IF STATEMENT", false);
    } else drivetrain.applyRequest(() -> brake);

    
        
        
    
    // while the A-button is pressed, overwrite some of the driving values with the output of our limelight methods
        //m_swerve.drive(xSpeed, ySpeed, rot, fieldRelative, getPeriod());
    }    

    @Override
    public void end(boolean interrupted) {
        
    }

    public void setDefaultCommand(swerveTurn swerveTurn) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setDefaultCommand'");
    }

}

