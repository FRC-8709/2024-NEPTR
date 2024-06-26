// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.autos.shoot;
import frc.robot.commands.TeleopLauncher;
import frc.robot.ledIndicator.LEDSystem;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Launcher;
import frc.robot.RobotContainer;


public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  public AddressableLED leds = new AddressableLED(0);
  public AddressableLEDBuffer buffer = new AddressableLEDBuffer(81);

  private LEDSystem ledSystem;

  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();
    CameraServer.startAutomaticCapture();
    leds.setLength(buffer.getLength());

    ledSystem = new LEDSystem(leds, buffer);
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
    
    ledSystem.execute();
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void disabledExit() {}

  @Override
  public void autonomousInit() {
     
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {
    // final Launcher s_Launcher = new Launcher(new TalonFX(Constants.LauncherConstants.leftGripper), new TalonFX(Constants.LauncherConstants.rightGripper), 6);
    // final Indexer s_Indexer = new Indexer(new TalonFX(Constants.IndexerConstants.Indexer), 9);

    // shoot(s_Indexer, s_Launcher);

  }

  @Override
  public void autonomousExit() {}

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {}

  @Override
  public void teleopExit() {}

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {}

  @Override
  public void testExit() {}

  @Override
  public void simulationPeriodic() {}
}