package iscot.events;

import iscot.enums.Areas;
import iscot.enums.Type;

public class GroundMoistureSensorEvent extends SensorEvent {
    private static final long serialVersionUID = 1L;
    private final double groundMoisture;

    public GroundMoistureSensorEvent(Areas area, double groundMoisture) {
        super(area, Type.SENSOR);
        this.groundMoisture = groundMoisture;
    }

    public double getGroundMoisture() {
        return groundMoisture;
    }
}