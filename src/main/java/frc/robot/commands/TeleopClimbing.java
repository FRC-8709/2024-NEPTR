package frc.robot.commands;

import frc.robot.subsystems.ClimbingSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class TeleopClimbing extends Command {
  private final ClimbingSubsystem subsystem;
  private final Joystick soloStick;
  public boolean toggle = false;
  private int leftSpeed = 0;
  private int rightSpeed = 0;
  public TeleopClimbing(ClimbingSubsystem subsystem, Joystick soloStick) {
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
    var leftMotorDown = subsystem.climbLeft.getRotorPosition();

    //voltage goes up to 12, not linear
    // 6 volts != 50% speed
    //set button number 1 - 12 on joystick : all labled ex; button 1 is trigger

    if (soloStick.getRawButton(11)) {
      subsystem.setLeftMotor(8);
    } else if (soloStick.getRawButton(10)) {
      subsystem.setRightMotor(4);
    } else if (soloStick.getRawButton(12)){
      subsystem.setRightMotor(-8);
    } else if (soloStick.getRawButton(9)){
      subsystem.setLeftMotor(-4);
    }else if (soloStick.getRawButton(7)){
      subsystem.setLeftMotor(-4);
      subsystem.setRightMotor(4);
    } else if (soloStick.getRawButton(8)){
      subsystem.setLeftMotor(8);
      subsystem.setRightMotor(-8);
    }else {
      subsystem.setRightMotor(0);
      subsystem.setLeftMotor(0);

    }
  }
  

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

