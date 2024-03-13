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

    private boolean run = true;
    @Override
    public void initialize() {
      System.out.println("ClimberCommand started!");
    }

    @Override
    public void execute() {
        if (!run) return;
        run = false;

        try {
            Launcher.setMotors(6);
            Indexer.setMotors(8);
            
            Thread.sleep(1000);

        } catch (Exception e){

        }
        }
        
    

    @Override
    public void end(boolean interrupted) {
        Launcher.setMotors(0);
        Indexer.setMotors(0);
        System.out.println("finsh");
    }
    @Override
    public boolean isFinished() {
      return false;
    }
}