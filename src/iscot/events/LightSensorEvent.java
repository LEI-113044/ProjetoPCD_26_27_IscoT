package iscot.events;

import iscot.enums.Areas;
import iscot.enums.Type;

public class LightSensorEvent extends SensorEvent {
    private static final long serialVersionUID = 1L;
    private final double light;

    public LightSensorEvent(Areas area, double light) {
        super(area, Type.SENSOR);
        this.light = light;
    }

    public double getLight() {
        return light;
    }
}