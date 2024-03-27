package frc.robot.commands;

import com.ctre.phoenix6.mechanisms.swerve.SwerveRequest;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.LimelightHelpers;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.IntakeFeed;
import frc.robot.subsystems.Launcher;
import frc.robot.CommandSwerveDrivetrain;
import frc.robot.generated.TunerConstants;

public class limeLightTargeting extends Command{
    private Indexer Indexer;
    private Launcher Launcher;
    //https://docs.limelightvision.io/docs/docs-limelight/tutorials/tutorial-swerve-aiming-and-ranging
    //https://github.com/FRC1884/season2024/blob/testing/src/main/java/frc/robot/subsystems/Vision/Vision.java
    

   
    public limeLightTargeting(Indexer s_Indexer, Launcher s_Launcher) {
        
        this.Indexer = s_Indexer;
        this.Launcher = s_Launcher;
    }
    double limelight_aim_proportional() {    
        final SwerveRequest.FieldCentric drive = new SwerveRequest.FieldCentric();
        double kP = .035;

        // tx ranges from (-hfov/2) to (hfov/2) in degrees. If your target is on the rightmost edge of 
        // your limelight 3 feed, tx should return roughly 31 degrees.
        double targetingAngularVelocity = LimelightHelpers.getTX("limelight") * kP;

        // convert to radians per second for our drive method
        targetingAngularVelocity *= Drivetrain.kMaxAngularSpeed;

        //invert since tx is positive when the target is to the right of the crosshair
        targetingAngularVelocity *= -1.0;


        return targetingAngularVelocity;


        
        //invert since tx is positive when the target is to the right of the crosshair
        //

        return targetingAngularVelocity;
  }
  

  private void drive(boolean FieldRelative){
    var rot =
        -m_rotLimiter.calculate(MathUtil.applyDeadband(m_controller.getRightX(), 0.02))
            * Drivetrain.kMaxAngularSpeed;
    // while the A-button is pressed, overwrite some of the driving values with the output of our limelight methods
    if(m_controller.getAButton()){
        final var rot_limelight = limelight_aim_proportional();
        rot = rot_limelight;

        //while using Limelight, turn off field-relative driving.
        //fieldRelative = false;
    }
        m_swerve.drive(xSpeed, ySpeed, rot, fieldRelative, getPeriod());
    }


    private boolean run = true;

    @Override
    public void execute() {
        if (!run) return;
        run = false;
            //
        
        }
        
    

    @Override
    public void end(boolean interrupted) {

    }

}