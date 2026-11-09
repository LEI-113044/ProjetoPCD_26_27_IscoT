package iscot.events;

import iscot.enums.Areas;
import iscot.enums.Type;

public class HumiditySensorEvent extends SensorEvent {
    private static final long serialVersionUID = 1L;
    private final double humidity;

    public HumiditySensorEvent(Areas area, double humidity) {
        super(area, Type.SENSOR);
        this.humidity = humidity;
    }

    public double getHumidity() {
        return humidity;
    }
}