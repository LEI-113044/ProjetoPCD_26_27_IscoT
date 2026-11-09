package iscot.events;

import iscot.enums.Areas;
import iscot.enums.Type;

import java.io.Serial;

public class TemperatureSensorEvent extends SensorEvent {
    @Serial
    private static final long serialVersionUID = 1L;
    private final double temperature;

    public TemperatureSensorEvent(Areas area, double temperature) {
        super(area, Type.SENSOR);
        this.temperature = temperature;
    }

    public double getTemperature() {
        return temperature;
    }
}