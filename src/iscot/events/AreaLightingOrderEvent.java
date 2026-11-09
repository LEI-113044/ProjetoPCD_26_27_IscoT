package iscot.events;

import iscot.enums.Areas;

import java.io.Serial;

/**
 * Decider order to turn the lights in an area on or off.
 */
public class AreaLightingOrderEvent extends HighLevelOrderEvent {
    @Serial
    private static final long serialVersionUID = 1L;

    private final boolean turnOn;

    /**
     * Creates an order to turn the lights in an area on or off.
     *
     * @param area the area to which the order applies
     * @param turnOn {@code true} to turn the lights on; {@code false} to turn them off
     */
    public AreaLightingOrderEvent(Areas area, boolean turnOn) {
        super(area);
        this.turnOn = turnOn;
    }

    /**
     * Indicates whether this order requests the lights to be turned on.
     *
     * @return {@code true} to turn the lights on; {@code false} to turn them off
     */
    public boolean isTurnOn() {
        return turnOn;
    }
}