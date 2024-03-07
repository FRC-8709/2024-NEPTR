// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.IntakeAngle;
import frc.robot.subsystems.IntakeUppies;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
//0.388 up position
//0.785 down position 
/** An example command that uses an example subsystem. */
public class TeleopIntakeUppies extends Command {
  private final IntakeUppies subsystem;
  private final Joystick soloStick;

  public TeleopIntakeUppies(IntakeUppies s_IntakeUppies, Joystick soloStick) {
    this.subsystem = s_IntakeUppies;
    //solo stick is the joystick on the far right; all functions not for driving go on this joystick
    this.soloStick = soloStick;
    addRequirements(s_IntakeUppies);
  }


// Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //voltage goes up to 12, not linear
    // 6 volts != 50% speed
    //set button number 1 - 12 on joystick : all labled ex; button 1 is trigger//&& subsystem.IntakeEncoder.getAbsolutePosition() >= 0.388
    if (soloStick.getRawButton(1)  ) {
      subsystem.setMotors(2);
    } else if (soloStick.getRawButton(2)) {//&& subsystem.IntakeEncoder.getAbsolutePosition() <= 0.785
      subsystem.setMotors(-2);
    } else {
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