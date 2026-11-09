package iscot.events;

import iscot.enums.Areas;
import iscot.enums.Type;

public class TemperatureSensorEvent extends SensorEvent {
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