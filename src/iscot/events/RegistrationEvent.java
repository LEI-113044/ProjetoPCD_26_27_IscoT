package iscot.events;

import iscot.enums.Areas;
import iscot.enums.Type;

public class RegistrationEvent extends Event {
    private final String deviceId; // Identificador único do dispositivo

    public RegistrationEvent(Areas area, Type type, String deviceId) {
        super(area, type);
        this.deviceId = deviceId;
    }

    public String getDeviceId() {
        return deviceId;
    }
}