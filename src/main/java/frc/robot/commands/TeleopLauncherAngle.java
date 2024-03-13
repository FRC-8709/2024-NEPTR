// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.AngleSubsystem;

/** An example command that uses an example subsystem. */
public class TeleopLauncherAngle extends Command {
  private final AngleSubsystem subsystem;
  private final Joystick soloStick;
  private final Joystick soloSoloStick;

  public TeleopLauncherAngle(AngleSubsystem subsystem, Joystick soloStick, Joystick soloSoloStick) {
    this.subsystem = subsystem;
    //solo stick is the joystick on the far right; all functions not for driving go on this joystick
    this.soloStick = soloStick;
    this.soloSoloStick = soloSoloStick;
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

    

    //DriverStation.reportError("Position " + subsystem.masterMotor.getRotorPosition().getValueAsDouble(), false);
 

    if (soloStick.getRawButton(4) ) {
      //down
      subsystem.setMotors(3); 
    } else if (soloStick.getRawButton(6)  ) {//&& subsystem.masterMotor.getRotorPosition().getValueAsDouble() >= lowLimit
      //up
      subsystem.setMotors(-3); 
      // line speaker setpoint
    } else if (soloStick.getRawButton(10) && subsystem.Encoder.getAbsolutePosition() >= 0.2605) {
        subsystem.setMotors(-4);
        //against speaker setpoint
    } else if (soloStick.getRawButton(12) && subsystem.Encoder.getAbsolutePosition() >= 0.2283) {
        subsystem.setMotors(-4);
    } else if (soloSoloStick.getRawButton(5) && subsystem.Encoder.getAbsolutePosition() >= 0.1279) {
      // upper limit
        subsystem.setMotors(-4);
    } else if (soloSoloStick.getRawButton(3) && subsystem.Encoder.getAbsolutePosition() <= 0.3804) {
      // lower limit
        subsystem.setMotors(4);
    } else { // && subsystem.Encoder.getAbsolutePosition() <= 0.80
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