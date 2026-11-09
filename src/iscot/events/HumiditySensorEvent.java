package iscot.events;

import iscot.enums.Areas;
import java.io.Serial;

public class HumiditySensorEvent extends SensorEvent {
    @Serial
    private static final long serialVersionUID = 1L;
    private final double humidity;

    public HumiditySensorEvent(Areas area, double humidity) {
        super(area);
        this.humidity = humidity;
    }

    public double getHumidity() {
        return humidity;
    }
}