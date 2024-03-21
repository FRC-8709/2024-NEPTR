package frc.robot.autos;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeUppies;
import frc.robot.subsystems.IntakeFeed;

public class intakeDown extends Command{

    private IntakeUppies Uppies;
    private IntakeFeed IntakeFeed;
   
    public intakeDown(IntakeUppies s_Uppies, IntakeFeed s_Feed) {
        this.Uppies = s_Uppies;
        this.IntakeFeed = s_Feed;
    }

    private boolean run = true;

    @Override
    public void execute() {
        if (!run) return;
        run = false;

        try {
            double lowerLimit = 0.51;

            while(Uppies.intakeEncoder.getAbsolutePosition() < lowerLimit){
                Uppies.setMotors(2);
                if(Uppies.intakeEncoder.getAbsolutePosition() >= lowerLimit){
                    break;
                }
                System.out.println("WORK PLS");
            }
            Uppies.setMotors(0);
        
        } catch (Exception e){

        }
        }
        
    

    @Override
    public void end(boolean interrupted) {

    }

}