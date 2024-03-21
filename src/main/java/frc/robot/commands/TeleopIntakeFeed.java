// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.IntakeFeed;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class TeleopIntakeFeed extends Command {
  private final IntakeFeed subsystem;
  private final Joystick soloStick;
  private final Joystick rightSoloStick;
  public boolean toggle = false;
  private double speed = 0;

  public TeleopIntakeFeed(IntakeFeed s_IntakeAngle, Joystick soloStick, Joystick rightSoloStick) {
    this.subsystem = s_IntakeAngle;
    //solo stick is the joystick on the far right; all functions not for driving go on this joystick
    this.soloStick = soloStick;
    this.rightSoloStick = rightSoloStick;
    addRequirements(s_IntakeAngle);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //voltage goes up to 12, not linear
    // 6 volts != 50% speed
    //set button number 1 - 12 on joystick : all labled ex; button 1 is trigger
    if (rightSoloStick.getRawButton(7)) {
      speed = -1.85;
      
    } else if (rightSoloStick.getRawButton(8)) {
      speed = -3;
    }else if (soloStick.getRawButton(5) && subsystem.isTriggered == true) {
      speed = -3; 
    } else {
      speed = 0;
    }

    subsystem.setMotors(speed);
  }

  
  

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    subsystem.setMotors(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}