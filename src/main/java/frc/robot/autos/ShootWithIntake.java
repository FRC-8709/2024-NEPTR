package frc.robot.autos;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.IntakeFeed;
import frc.robot.subsystems.Launcher;

public class ShootWithIntake extends Command{

    private Indexer Indexer;
    private Launcher Launcher;
    private IntakeFeed IntakeFeed;
   
    public ShootWithIntake(Indexer s_Indexer, Launcher s_Launcher, IntakeFeed s_Feed) {
        this.Indexer = s_Indexer;
        this.Launcher = s_Launcher;
        this.IntakeFeed = s_Feed;
    }

    private boolean run = true;
    //0.273

    @Override
    public void execute() {
        if (!run) return;
        run = false;

        try {
            Launcher.setMotors(10);
            Thread.sleep(600);

            Launcher.setMotors(10);
            IntakeFeed.setMotors(-2);
            Indexer.setMotors(12);
            
            Thread.sleep(600);
            Launcher.setMotors(10);
            Indexer.setMotors(12);
            IntakeFeed.setMotors(-2);
            Thread.sleep(1000);
            Launcher.setMotors(0);
            Indexer.setMotors(0);
            IntakeFeed.setMotors(0);

        } catch (Exception e){

        }
        }
        
    

    @Override
    public void end(boolean interrupted) {

    }

}