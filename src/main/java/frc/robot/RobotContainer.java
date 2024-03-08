// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix6.Utils;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.mechanisms.swerve.SwerveRequest;
import com.ctre.phoenix6.mechanisms.swerve.SwerveModule.DriveRequestType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;


import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.commands.TeleopLauncherAngle;
//import frc.robot.commands.TeleopAuto;
import frc.robot.commands.TeleopIndexer;
import frc.robot.commands.TeleopIntakeAngle;
import frc.robot.commands.TeleopIntakeUppies;
import frc.robot.commands.TeleopLauncher;
import frc.robot.generated.TunerConstants;
import frc.robot.subsystems.AngleSubsystem;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.IntakeAngle;
import frc.robot.subsystems.IntakeUppies;
import frc.robot.subsystems.Launcher;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine.Direction;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class RobotContainer {
  private double MaxSpeed = 6; // 6 meters per second desired top speed
  private double MaxAngularRate = 1.5 * Math.PI; // 3/4 of a rotation per second max angular velocity

  /* Setting up bindings for necessary control of the swerve drive platform */
  private final Joystick leftJoystick = new Joystick(0); // My joystick
  private final Joystick rightJoystick = new Joystick(1);
  private final Joystick soloJoystick = new Joystick(2);
  private final Joystick soloJoystick2ElectricBoogaloo = new Joystick(3);
  

  private final JoystickButton a = new JoystickButton(leftJoystick, 3);
  private final JoystickButton b = new JoystickButton(leftJoystick, 4);
  private final JoystickButton trigger = new JoystickButton(leftJoystick, 1);
  private final JoystickButton resetButton = new JoystickButton(leftJoystick, 5);

  
  private final CommandSwerveDrivetrain drivetrain = TunerConstants.DriveTrain; // My drivetrain
  
  private final SwerveRequest.FieldCentric drive = new SwerveRequest.FieldCentric()
      .withDeadband(MaxSpeed * 0.075).withRotationalDeadband(MaxAngularRate * 0.1) // Add a 10% deadband
      .withDriveRequestType(DriveRequestType.OpenLoopVoltage); // I want field-centric
                                                               // driving in open loop
  private final SwerveRequest.SwerveDriveBrake brake = new SwerveRequest.SwerveDriveBrake();
  private final SwerveRequest.PointWheelsAt point = new SwerveRequest.PointWheelsAt();

  private final Telemetry logger = new Telemetry(MaxSpeed);
  
  private final Launcher s_Launcher = new Launcher(new TalonFX(Constants.LauncherConstants.leftGripper), new TalonFX(Constants.LauncherConstants.rightGripper));
  private final AngleSubsystem s_Angle = new AngleSubsystem(new TalonFX(Constants.AngleConstants.LeftAngle), new TalonFX(Constants.AngleConstants.RightAngle),8);
  private final Indexer s_Indexer = new Indexer(new TalonFX(Constants.IndexerConstants.Indexer), 9);
  private final IntakeAngle s_IntakeAngle = new IntakeAngle(new TalonFX(Constants.IntakeAngleConstants.IntakeAngle));
  private final IntakeUppies s_IntakeUppies = new IntakeUppies(new TalonFX(Constants.IntakeUppiesConstants.IntakeUppies), 7);
  private Command runAuto = drivetrain.getAutoPath("Test");

  public RobotContainer() {
    configureBindings();

    s_Launcher.setDefaultCommand(
      new TeleopLauncher(s_Launcher, soloJoystick)
    );
    
    s_IntakeUppies.setDefaultCommand(
      new TeleopIntakeUppies(s_IntakeUppies, rightJoystick)
    );

    s_Angle.setDefaultCommand(
      new TeleopLauncherAngle(s_Angle, soloJoystick, leftJoystick)
    );

    s_Indexer.setDefaultCommand(
      new TeleopIndexer(s_Indexer, soloJoystick)
    );

    s_IntakeAngle.setDefaultCommand(
      new TeleopIntakeAngle(s_IntakeAngle, leftJoystick)
    );

  }

  private void configureBindings() {
    drivetrain.setDefaultCommand( // Drivetrain will execute this command periodically
        drivetrain.applyRequest(() -> drive.withVelocityX(-leftJoystick.getY() * MaxSpeed) // Drive forward with
                                                                                           // negative Y (forward)
            .withVelocityY(-leftJoystick.getX() * MaxSpeed) // Drive left with negative X (left)
            .withRotationalRate(-rightJoystick.getX() * MaxAngularRate) // Drive counterclockwise with negative X (left)
        ));

    a.whileTrue(drivetrain.applyRequest(() -> brake));
    b.whileTrue(drivetrain
        .applyRequest(() -> point.withModuleDirection(new Rotation2d(-leftJoystick.getY(), -leftJoystick.getX()))));

    // reset the field-centric heading on left bumper press
    trigger.onTrue(drivetrain.runOnce(() -> drivetrain.seedFieldRelative()));
    resetButton.onTrue(drivetrain.runOnce(() -> drivetrain.seedFieldRelative()));

    if (Utils.isSimulation()) {
      drivetrain.seedFieldRelative(new Pose2d(new Translation2d(), Rotation2d.fromDegrees(90)));
    }
    drivetrain.registerTelemetry(logger::telemeterize);
  }

  

  public Command getAutonomousCommand() {
    /* First put the drivetrain into auto run mode, then run the auto */
    return runAuto;
  }
}
