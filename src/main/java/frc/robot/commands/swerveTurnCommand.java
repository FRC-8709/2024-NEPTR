package frc.robot.commands;

import com.ctre.phoenix6.mechanisms.swerve.SwerveRequest;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.units.Time;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.CommandSwerveDrivetrain;
import frc.robot.commands.targeting.TurnDirection;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.AngleSubsystem;
import frc.robot.subsystems.Launcher;

public class swerveTurnCommand extends Command {
    
    public final CommandSwerveDrivetrain drivetrain;
    private final SwerveRequest.FieldCentric drive;
    private final SwerveRequest.SwerveDriveBrake brake;
    private double MaxAngularRate = 1.5 * Math.PI;
    private int TimeCheck = 999;
    private double LastX;
    private Indexer Indexer;
    private Launcher Launcher;
    private AngleSubsystem LauncherAngle;
    // private double err;
    // private double deltaErr;

    public swerveTurnCommand(CommandSwerveDrivetrain drivetrain, SwerveRequest.FieldCentric drive, 
    SwerveRequest.SwerveDriveBrake brake, Indexer s_Indexer, Launcher s_Launcher, AngleSubsystem s_LauncherAngle) {
        this.drivetrain = drivetrain;
        this.drive = drive;
        this.brake = brake;
        this.Indexer = s_Indexer;
        this.LauncherAngle = s_LauncherAngle;
        this.Launcher = s_Launcher;
        
        addRequirements(drivetrain);

    }

    @Override
    public void execute(){
    
        // Let's define which apriltag we are targeting. We want the middle of the Speaker on whatever alliance we are on. 
        // On Red, that is #4 with a height of 57.13
        // On Blue, that is #7 with a height of 57.13 - these heights being the smae helps - dist to center of tag.

        //int targeted

        double v = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0); // tv is if any target is in sight
        double x = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0); // tx is the side to side value for how far we are away from the target.
        double y = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0); // ty is the height value- used for determining height
        
        // Start by checking if a target is in view. If it is NOT in view start rotating to find the defined target.
        TimeCheck++;
          if (v != 1.0 && TimeCheck > 3)
          {
            drivetrain.setControl(drive.withRotationalRate(-0.15 * MaxAngularRate)); // Modify the -0.4 for speed, We want this negative to turn to the right.
            return;
          }
          else if(v == 1.0)
          {
            TimeCheck = 0;
          }
          
        
        // Now that the robot sees the target, using the X position to align ourselves to the center of the target.
        // Sorry Victoria but i'm doing this my non-PID way haha. Feel free to change it afterwards
        // Looking at the Limelight interface I think we should be within 8 units of true center and that should work for us- testing required
        // TODO: Test below value

        double tolerance = 1; // 8 total, divide by 2

        if (!withinTolerance(x, 0.0, tolerance)) {
          // We know we are not lined up, so let's align properly
          // The bot to turn right is a negative modifier on the swerve drive
          // We need to turn right if the Limelight provided x value is positive.
          //TurnDirection turnDirection = x < 0.0 ? TurnDirection.LEFT : TurnDirection.RIGHT;
          
          // Modifier for Turning Precisely
          if(v != 1.00) x = LastX;
          double turnSpeed = 0.3*(-x/11) * MaxAngularRate;
          //double modifier = turnDirection == TurnDirection.LEFT ? 1.0 : -1.0;

          double finalSpeed = turnSpeed;//modifier * turnSpeed;

          // Finally, turn the bot. Process should automatically exit once within the tolerance.
          drivetrain.setControl(drive.withRotationalRate(finalSpeed));
        } // else drivetrain.setControl(brake);

        // Drive is aligned. Start implementing subsystems.
        // Calculate distance first.
        final double apriltagInches = 57.13; // height from carpet to center of april tag. taken from field drawing
        final double limelightHeight = 7.25; // inches
        final double limelightDegrees = 5; // degrees

        double angleToGoalDegree = limelightDegrees + y;
        double angleToRadians = angleToGoalDegree * (Math.PI * 180.0);

        double distanceToSpeaker = (apriltagInches - limelightHeight) / Math.tan(angleToRadians);
        double actualDistance = Math.abs(distanceToSpeaker);

        // Use actualDistance as a reference for distance variables. Should be somewhat accurate, but repetable.
        // Set these values to the needed.
        double launchVoltage = 12;
        double indexerVoltage = 12;
        //subsystem.setMotors(-12); 
        //subsystem.Encoder.getAbsolutePosition() >= 0.2605
        double launchAngle = 0.2605; // I'm sure this is way off lol, lets set this to a median number at some point
        double launchAngleVoltage = 12;

        if(distanceToSpeaker <= 125) {
          launchVoltage = 6;
          indexerVoltage = 6;
          // FIX ARM
          if(LauncherAngle.Encoder.getAbsolutePosition() >= 0.26){
            launchAngleVoltage = -4;
          } else if (LauncherAngle.Encoder.getAbsolutePosition() <= 0.26){
            launchAngleVoltage = 4;
          }
        }

        DriverStation.reportError("Distance" + distanceToSpeaker, false);

        Indexer.setMotors(indexerVoltage); 
        Launcher.setMotors(launchVoltage); 
        LauncherAngle.setMotors(launchVoltage); 



        // Set these values to what is needed;
        // Then call the subsystems to take those values. Subsystems should auto adjust to these setpoints.

        LastX = x;
        
    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.setControl(brake);
    }


    // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  public boolean withinTolerance(double actual, double nominal, double tolerance) {
    return Math.abs(nominal - actual) <= tolerance;
}

    
}