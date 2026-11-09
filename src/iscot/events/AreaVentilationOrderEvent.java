package iscot.events;

import iscot.enums.Areas;
import iscot.enums.Type;

import java.io.Serial;

public class AreaVentilationOrderEvent extends HighLevelOrderEvent {
    @Serial
    private static final long serialVersionUID = 1L;
    private final boolean turnOn;

    public AreaVentilationOrderEvent(Areas area, boolean turnOn) {
        super(area, Type.ACTUATOR); // Ou DECIDER dependendo de quem emite, mas tipicamente destinado a atuadores
        this.turnOn = turnOn;
    }

    public boolean isTurnOn() {
        return turnOn;
    }
}