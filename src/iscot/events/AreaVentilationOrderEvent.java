package iscot.events;

import iscot.enums.Areas;

import java.io.Serial;

/**
 * Decider order to request ventilation in an area.
 */
public class AreaVentilationOrderEvent extends HighLevelOrderEvent {
    @Serial
    private static final long serialVersionUID = 1L;

    private final boolean turnOn;

    /**
     * Creates an order to turn ventilation on or off for an area.
     *
     * @param area area to which the order applies
     * @param turnOn {@code true} to request ventilation; {@code false} to stop the request
     * @throws NullPointerException if {@code area} is {@code null}
     */
    public AreaVentilationOrderEvent(Areas area, boolean turnOn) {
        super(area);
        this.turnOn = turnOn;
    }

    /**
     * Indicates whether this order requests ventilation to be turned on.
     *
     * @return {@code true} to request ventilation; {@code false} otherwise
     */
    public boolean isTurnOn() {return turnOn;}

    @Override
    public String toString() {
        return "AreaVentilationOrderEvent{" +
                "turnOn=" + turnOn +
                '}';
    }
}