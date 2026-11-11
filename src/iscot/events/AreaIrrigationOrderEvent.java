package iscot.events;

import iscot.enums.Areas;

import java.io.Serial;

/**
 * Decider order to turn the garden irrigation on or off.
 */
public class AreaIrrigationOrderEvent extends HighLevelOrderEvent {
    @Serial
    private static final long serialVersionUID = 1L;

    private final boolean turnOn;

    /**
     * Creates an order to turn the garden irrigation on or off.
     *
     * @param turnOn {@code true} to turn irrigation on; {@code false} to turn it off
     */
    public AreaIrrigationOrderEvent(boolean turnOn) {
        super(Areas.Garden);
        this.turnOn = turnOn;
    }

    /**
     * Indicates whether this order requests irrigation to be turned on.
     *
     * @return {@code true} to turn irrigation on; {@code false} to turn it off
     */
    public boolean isTurnOn() {
        return turnOn;
    }

    @Override
    public String toString() {
        return "AreaIrrigationOrderEvent{" +
                "turnOn=" + turnOn +
                '}';
    }
}