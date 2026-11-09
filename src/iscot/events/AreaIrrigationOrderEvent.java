package iscot.events;

import iscot.enums.Areas;
import iscot.enums.Type;

public class AreaIrrigationOrderEvent extends HighLevelOrderEvent {
    private static final long serialVersionUID = 1L;
    private final boolean turnOn;

    public AreaIrrigationOrderEvent(Areas area, boolean turnOn) {
        super(area, Type.ACTUATOR); // Ou DECIDER dependendo de quem emite, mas tipicamente destinado a atuadores
        this.turnOn = turnOn;
    }

    public boolean isTurnOn() {
        return turnOn;
    }
}