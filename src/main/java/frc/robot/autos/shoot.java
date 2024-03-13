package frc.robot.autos;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Launcher;

public class shoot extends Command{

    private Indexer Indexer;
    private Launcher Launcher;
   
    public shoot(Indexer s_Indexer, Launcher s_Launcher) {
        this.Indexer = s_Indexer;
        this.Launcher = s_Launcher;
    }

    @Override
    public void execute() {
        

        try {

            Indexer.setMotors(8);
            Launcher.setMotors(6);
            Thread.sleep(1000);

        } catch (Exception e){

        }
        }
        
    

    @Override
    public void end(boolean interrupted) {
    }
}