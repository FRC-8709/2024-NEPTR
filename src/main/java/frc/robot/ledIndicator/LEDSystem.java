package frc.robot.ledIndicator;

import java.util.HashSet;
import java.util.Set;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;

public class LEDSystem {
    
    private AddressableLED led;
    private AddressableLEDBuffer ledBuffer;
    private IndicatorLevels indicator = IndicatorLevels.PARTY;

    public LEDSystem(AddressableLED led, AddressableLEDBuffer buffer) {
        this.led = led;
        this.ledBuffer = buffer;
    }

    public void setIndicator(IndicatorLevels level) {
        this.indicator = level;
    }

    /**
     * This function needs to be called in the robot periodic system, so it runs every 20ms.
     */
    public void execute() {

        switch (indicator) {
            case PARTY:
                this.party();
                break;
            case INTAKE_MODE:
                setColor(0, 0, 250);
                break;
            case NOTE_STORED:
                setColor(0, 250, 0);
                break;
            case AUTONOMOUS:
                setColor(250, 229, 0);
            case DISABLED:
            default:
                setColor(150, 0, 0);
                break;
        }

    }

    private void setColor(int r, int g, int b) {
        for (int i = 0; i < this.ledBuffer.getLength(); i++) {
            this.ledBuffer.setRGB(i, r, g, b);
        }
      
        this.led.setData(this.ledBuffer);
        this.led.start();
    }

    private void party() {
        int[] letters = new int[]{49,2,53,57,4,51,59,6,46,8,9,10,47,46,62,63,64,14,16,18,41,39,37,69,71,20,21,22,34,74,75,76,24,25,31,29,78,80};
        for (int n : letters) {
            this.ledBuffer.setRGB(n-1, 255, 165, 0);
        }

        int[] emptySpace = new int[]{1,54,5,50,55,56,3,52,58,7,48,60,61,11,12,13,45,44,43,42,65,66,67,68,15,40,70,17,38,72,73,19,36,35,23,32,33,77,26,27,30,28,79,81};
        for (int n : emptySpace) {
            this.ledBuffer.setRGB(n-1, 0, 0, 20);
        }
        this.led.setData(this.ledBuffer);
        this.led.start();
    }

}
