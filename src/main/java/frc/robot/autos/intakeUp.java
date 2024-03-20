package frc.robot.autos;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeUppies;
import frc.robot.subsystems.IntakeFeed;

public class intakeUp extends Command{

    private IntakeUppies Uppies;
    private IntakeFeed IntakeFeed;
   
    public intakeUp(IntakeUppies s_Uppies, IntakeFeed s_Feed) {
        this.Uppies = s_Uppies;
        this.IntakeFeed = s_Feed;
    }

    private boolean run = true;

    @Override
    public void execute() {
        if (!run) return;
        run = false;

        try {
            double upperLimit = 0.26;

            IntakeFeed.setMotors(3);

            Thread.sleep(500);

            IntakeFeed.setMotors(0);

            while(Uppies.intakeEncoder.getAbsolutePosition() > upperLimit){
                Uppies.setMotors(-2);
                if(Uppies.intakeEncoder.getAbsolutePosition() <= upperLimit){
                    break;
                }
                System.out.println("WORK PLS");
                
            }
            Uppies.setMotors(0);

            Thread.sleep(1000);

            IntakeFeed.setMotors(3);

            Thread.sleep(1000);
            
            IntakeFeed.setMotors(0);

        } catch (Exception e){

        }
        }
        
    

    @Override
    public void end(boolean interrupted) {

    }

}