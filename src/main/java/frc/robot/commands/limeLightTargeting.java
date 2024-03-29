package frc.robot.commands;

import com.ctre.phoenix6.mechanisms.swerve.SwerveRequest;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.units.Units;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.LimelightHelpers;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.IntakeFeed;
import frc.robot.subsystems.Launcher;
import frc.robot.CommandSwerveDrivetrain;
import frc.robot.generated.TunerConstants;
import frc.robot.Constants.AutoConstants;

public class limeLightTargeting extends Command{
    private Indexer Indexer;
    private Launcher Launcher;
    private final Joystick leftJoystick;
    private final Joystick rightJoystick;
    private final SlewRateLimiter m_xspeedLimiter = new SlewRateLimiter(3);
    private final SlewRateLimiter m_yspeedLimiter = new SlewRateLimiter(3);
    private final SlewRateLimiter m_rotLimiter = new SlewRateLimiter(3);



    //https://docs.limelightvision.io/docs/docs-limelight/tutorials/tutorial-swerve-aiming-and-ranging
    //https://github.com/FRC1884/season2024/blob/testing/src/main/java/frc/robot/subsystems/Vision/Vision.java
    

   
    public limeLightTargeting(Indexer s_Indexer, Launcher s_Launcher, Joystick leftJoystick, Joystick righJoystick) {
        
        this.Indexer = s_Indexer;
        this.Launcher = s_Launcher;
        this.leftJoystick = leftJoystick;
        this.rightJoystick = righJoystick;
    }
    double limelight_aim_proportional() {    
        final SwerveRequest.FieldCentric drive = new SwerveRequest.FieldCentric();
        double kP = 1;

        // tx ranges from (-hfov/2) to (hfov/2) in degrees. If your target is on the rightmost edge of 
        // your limelight 3 feed, tx should return roughly 31 degrees.
        double targetingAngularVelocity = LimelightHelpers.getTX("limelight") * kP;

        // convert to radians per second for our drive method
        
        targetingAngularVelocity *= AutoConstants.kMaxAccelerationMetersPerSecondSquared;
        //targetingAngularVelocity *= Subsystem..kMaxAngularSpeed;

        //invert since tx is positive when the target is to the right of the crosshair
        targetingAngularVelocity *= -1.0;


        return targetingAngularVelocity;
  }
  double limelight_range_proportional()
  {    
    double kP = .1;
    double targetingForwardSpeed = LimelightHelpers.getTY("limelight") * kP;
    targetingForwardSpeed *= AutoConstants.kMaxAccelerationMetersPerSecondSquared;
    targetingForwardSpeed *= -1.0;
    return targetingForwardSpeed;
  }

  public void drive(boolean FieldRelative){
    
    var rot =
        -m_rotLimiter.calculate(MathUtil.applyDeadband(rightJoystick.getX(), 0.02))
            * AutoConstants.kMaxAccelerationMetersPerSecondSquared;
    // Get the x speed. We are inverting this because Xbox controllers return
    // negative values when we push forward.
    // var xSpeed =
    //     -m_xspeedLimiter.calculate(MathUtil.applyDeadband(leftJoystick.getY(), 0.02))
    //         * Drivetrain.kMaxSpeed;

    // Get the y speed or sideways/strafe speed. We are inverting this because
    // we want a positive value when we pull to the left. Xbox controllers
    // return positive values when you pull to the right by default.
    // var ySpeed =
    //     -m_yspeedLimiter.calculate(MathUtil.applyDeadband(leftJoystick.getX(), 0.02))
    //         * Drivetrain.kMaxSpeed;

    // Get the rate of angular rotation. We are inverting this because we want a
    // positive value when we pull to the left (remember, CCW is positive in
    // mathematics). Xbox controllers return positive values when you pull to
    // the right by default.
    // var rot =
    //     -m_rotLimiter.calculate(MathUtil.applyDeadband(m_controller.getRightX(), 0.02))
    //         * Drivetrain.kMaxAngularSpeed;
    if(leftJoystick.getRawButton(10)){
            final var rot_limelight = limelight_aim_proportional();
            rot = rot_limelight;
    
        //while using Limelight, turn off field-relative driving.
        //fieldRelative = false;
    }
    
    // while the A-button is pressed, overwrite some of the driving values with the output of our limelight methods
        //m_swerve.drive(xSpeed, ySpeed, rot, fieldRelative, getPeriod());
    }    

    @Override
    public void end(boolean interrupted) {

    }

}
