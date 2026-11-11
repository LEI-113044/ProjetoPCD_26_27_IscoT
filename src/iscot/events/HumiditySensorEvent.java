package iscot.events;

import iscot.enums.Areas;
import java.io.Serial;

/**
 * Sensor event containing an air-humidity reading for an area.
 */
public class HumiditySensorEvent extends SensorEvent {
    @Serial
    private static final long serialVersionUID = 1L;

    private final double humidity;

    /**
     * Creates an air-humidity reading event.
     *
     * @param area area where the reading was taken
     * @param humidity measured air humidity; the unit or scale is defined by the sensor configuration
     * @throws NullPointerException if {@code area} is {@code null}
     */
    public HumiditySensorEvent(Areas area, double humidity) {
        super(area);
        this.humidity = humidity;
    }

    /**
     * Returns the reported air-humidity value.
     *
     * @return the humidity reading
     */
    public double getHumidity() {
        return humidity;
    }

    @Override
    public String toString() {
        return "HumiditySensorEvent{" +
                "humidity=" + humidity +
                '}';
    }
}