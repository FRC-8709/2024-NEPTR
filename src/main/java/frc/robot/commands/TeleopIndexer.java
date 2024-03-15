// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Indexer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class TeleopIndexer extends Command {
  private final Indexer subsystem;
  private final Joystick soloStick;
  private final Joystick codyRightStick;

  public TeleopIndexer(Indexer subsystem, Joystick soloStick, Joystick codyRightStick) {
    this.subsystem = subsystem;
    //solo stick is the joystick on the far right; all functions not for driving go on this joystick
    this.soloStick = soloStick;
    this.codyRightStick = codyRightStick;
    addRequirements(subsystem);
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
    if (soloStick.getRawButton(3) && subsystem.isTriggered == true) {
      // in
      subsystem.setMotors(-12); 
    } if (soloStick.getRawButton(11)) {
      // in
      subsystem.setMotors(-12); 
    } else if (soloStick.getRawButton(9)) {
      subsystem.setMotors(12); 
    } else if (codyRightStick.getRawButton(5) && subsystem.frontIsTriggered == true) {
      subsystem.setMotors(12); 
    }  else {
      subsystem.setMotors(0);
    }
    
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