package iscot.events;

import iscot.enums.Areas;
import iscot.enums.Type;

/**
 * Base class for events containing sensor readings.
 *
 * <p>Sensor events always have {@link Type#SENSOR} as their source type.</p>
 */
public abstract class SensorEvent extends Event {
    /**
     * Creates a sensor event associated with the specified area.
     *
     * @param area area where the reading was taken
     * @throws NullPointerException if {@code area} is {@code null}
     */
    public SensorEvent(Areas area) {
        super(area, Type.SENSOR);
    }
}