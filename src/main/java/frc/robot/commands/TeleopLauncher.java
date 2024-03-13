// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Launcher;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class TeleopLauncher extends Command {
  private final Launcher subsystem;
  private final Joystick soloStick;
  public boolean toggle = false;
  private double speed = 0;
  private int speedwSensor = 0;
  public TeleopLauncher(Launcher subsystem, Joystick soloStick) {
    this.subsystem = subsystem;
    //solo stick is the joystick on the far right; all functions not for driving go on this joystick
    this.soloStick = soloStick;
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

    //if (soloStick.getRawButton(1) && toggle == false) {
      if (soloStick.getRawButton(3)){
      //toggle is so its only a button press, not a button hold
      speed = -1.5;
      
      //toggle = true;
    }else if (soloStick.getRawButton(1) ) {
      speed = 6;
      
      //toggle = true;
    }else if (soloStick.getRawButton(5)) {
      speed = 3;
    }
    else if (soloStick.getRawButton(7)) {
      speed = 5;
    }
    else if(soloStick.getRawButton(2)){
      speed = 0;
    }
    subsystem.setMotors(speed);

    /*
    if(speed != 0){
      // - NEGATIVE VALUES ONLY
        subsystem.setMotors(speed);
    }
    else {
      subsystem.setMotors(0);
    }
    */
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