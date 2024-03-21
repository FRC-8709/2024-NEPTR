package frc.robot.autos;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeUppies;
import frc.robot.subsystems.IntakeFeed;

public class intakeFeeder extends Command{

    private IntakeFeed IntakeFeed;
   
    public intakeFeeder(IntakeFeed s_Feed) {
        this.IntakeFeed = s_Feed;
    }

    private boolean run = true;

    @Override
    public void execute() {
        if (!run) return;
        run = false;

        try {

            IntakeFeed.setMotors(-3);

            Thread.sleep(550);
            
            IntakeFeed.setMotors(0);

        } catch (Exception e){

        }
        }
        
    

    @Override
    public void end(boolean interrupted) {

    }

}