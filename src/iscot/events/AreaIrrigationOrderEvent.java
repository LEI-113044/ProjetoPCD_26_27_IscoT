package iscot.events;

import iscot.enums.Areas;

import java.io.Serial;

public class AreaIrrigationOrderEvent extends HighLevelOrderEvent {
    @Serial
    private static final long serialVersionUID = 1L;
    private final boolean turnOn;

    public AreaIrrigationOrderEvent(Areas area, boolean turnOn) {
        super(area);
        this.turnOn = turnOn;
    }

    public boolean isTurnOn() {
        return turnOn;
    }
}