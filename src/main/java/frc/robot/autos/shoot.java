// package frc.robot.autos;

// import edu.wpi.first.wpilibj.DoubleSolenoid;
// import frc.robot.Constants;
// import frc.robot.Constants.IndexerConstants;
// import frc.robot.subsystems.Indexer;
// import frc.robot.subsystems.Launcher;

// public class shoot extends CommandBase {

//     private Indexer Indexer;
//     private Launcher Launcher;
   
//     public shoot(Indexer s_Indexer, Launcher s_Launcher) {
//         this.Indexer = s_Indexer;
//         this.Launcher = s_Launcher;
//     }

//     private boolean run = true;
//     @Override
//     public void execute() {
//         if (!run) return;
//         run = false;
//         try {
//             Constants.Pneumatics.air.enableDigital();
//             elevator.setMotors(-0.75);
//             arm.setMotors(0.41);
//             Thread.sleep(1000L);
//             elevator.setMotors(0);
//             arm.setMotors(0);
//             Thread.sleep(100L);

//             Constants.Pneumatics.clawTilt.set(DoubleSolenoid.Value.kForward);
//             Thread.sleep(100L);

//             arm.setMotors(0.8);
//             Thread.sleep(1750L);
//             arm.setMotors(0);
//             Thread.sleep(100L);

//             elevator.setMotors(-0.75);
//             Thread.sleep(300L);
//             elevator.setMotors(0);
//             Thread.sleep(100L);

//             grippers.setMotors(0.4);
//             Thread.sleep(2000L);
//             Constants.Pneumatics.clawClamp.set(DoubleSolenoid.Value.kReverse);
//             grippers.setMotors(0);
//             Thread.sleep(100L);

//             //elevator.setMotors(0.20);
//             arm.setMotors(-0.75);
//             Thread.sleep(2000L);
//             arm.setMotors(0);
//             //elevator.setMotors(0);
//             Thread.sleep(100L);

//             //Constants.Swerve.driveMotorID.Mod0.setMotors(70);            
//             Thread.sleep(4000L);
            


//             //Constants.Pneumatics.clawTilt.toggle();
//             // Thread.sleep(500L);
//         } catch (Exception e){

//         }
        
//     }

//     @Override
//     public void end(boolean interrupted) {
//     }
// }