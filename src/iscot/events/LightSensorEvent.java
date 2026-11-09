package iscot.events;

import iscot.enums.Areas;
import java.io.Serial;

public class LightSensorEvent extends SensorEvent {
    @Serial
    private static final long serialVersionUID = 1L;
    private final double light;

    public LightSensorEvent(Areas area, double light) {
        super(area);
        this.light = light;
    }

    public double getLight() {
        return light;
    }
}