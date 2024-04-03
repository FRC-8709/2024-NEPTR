package frc.robot.ledIndicator;

public enum IndicatorLevels {

    NOTE_STORED(false),
    INTAKE_MODE(true),
    AUTONOMOUS(false),
    PARTY(false),
    DISABLED(false);

    public boolean blink;

    private IndicatorLevels(boolean blink) {
        this.blink = blink;
    }
}