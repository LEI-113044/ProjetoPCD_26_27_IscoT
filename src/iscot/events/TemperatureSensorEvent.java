package iscot.events;

import iscot.enums.Areas;
import java.io.Serial;

/**
 * Sensor event containing a temperature reading for an area.
 */
public class TemperatureSensorEvent extends SensorEvent {
    @Serial
    private static final long serialVersionUID = 1L;

    private final double temperature;

    /**
     * Creates a temperature reading event.
     *
     * @param area area where the reading was taken
     * @param temperature measured temperature; the unit is defined by the sensor configuration
     * @throws NullPointerException if {@code area} is {@code null}
     */
    public TemperatureSensorEvent(Areas area, double temperature) {
        super(area);
        this.temperature = temperature;
    }

    /**
     * Returns the reported temperature.
     *
     * @return the temperature reading
     */
    public double getTemperature() {
        return temperature;
    }

    @Override
    public String toString() {
        return "TemperatureSensorEvent{" +
                "area=" + super.getArea() +
                "temperature=" + temperature +
                '}';
    }
}