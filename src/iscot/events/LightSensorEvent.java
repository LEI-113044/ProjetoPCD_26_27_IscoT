package iscot.events;

import iscot.enums.Areas;
import java.io.Serial;

/**
 * Sensor event containing a light-level reading for an area.
 */
public class LightSensorEvent extends SensorEvent {
    @Serial
    private static final long serialVersionUID = 1L;

    private final double light;

    /**
     * Creates a light-level reading event.
     *
     * @param area area where the reading was taken
     * @param light measured light level; the unit or scale is defined by the sensor configuration
     * @throws NullPointerException if {@code area} is {@code null}
     */
    public LightSensorEvent(Areas area, double light) {
        super(area);
        this.light = light;
    }

    /**
     * Returns the reported light level.
     *
     * @return the light-level reading
     */
    public double getLight() {
        return light;
    }
}