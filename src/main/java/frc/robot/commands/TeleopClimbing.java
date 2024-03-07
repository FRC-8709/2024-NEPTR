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
    //voltage goes up to 12, not linear
    // 6 volts != 50% speed
    //set button number 1 - 12 on joystick : all labled ex; button 1 is trigger

      if (soloStick.getRawButton(1)){
      leftSpeed = -6;
      rightSpeed = -6;
    }else if (soloStick.getRawButton(3)) {
      rightSpeed = 3;
      leftSpeed = 0;
    }else if (soloStick.getRawButton(5)) {
      rightSpeed = 0;
      leftSpeed = 3;
    }
    else if(soloStick.getRawButton(2)){
      rightSpeed = 0;
      leftSpeed = 0;
    }

    subsystem.setLeftMotor(leftSpeed);
    subsystem.setRightMotor(rightSpeed);

  }
  

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    subsystem.setLeftMotor(0);
    subsystem.setRightMotor(0);

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

