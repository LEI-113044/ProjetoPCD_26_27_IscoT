package iscot.events;

import iscot.enums.Areas;
import java.io.Serial;

/**
 * Sensor event containing a ground moisture reading for an area.
 */
public class GroundMoistureSensorEvent extends SensorEvent {
    @Serial
    private static final long serialVersionUID = 1L;

    private final double groundMoisture;

    /**
     * Creates a ground moisture reading event.
     *
     * @param area area where the reading was taken
     * @param groundMoisture measured ground moisture; the unit or scale is defined by the sensor configuration
     * @throws NullPointerException if {@code area} is {@code null}
     */
    public GroundMoistureSensorEvent(Areas area, double groundMoisture) {
        super(area);
        this.groundMoisture = groundMoisture;
    }

    /**
     * Returns the reported ground moisture value.
     *
     * @return the ground moisture reading
     */
    public double getGroundMoisture() {
        return groundMoisture;
    }

    @Override
    public String toString() {
        return "GroundMoistureSensorEvent{" +
                "groundMoisture=" + groundMoisture +
                '}';
    }
}