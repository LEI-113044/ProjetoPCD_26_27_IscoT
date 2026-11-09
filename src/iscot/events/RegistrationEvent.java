package iscot.events;

import iscot.enums.Areas;
import iscot.enums.Type;

import java.io.Serial;

public class RegistrationEvent extends Event {
    @Serial
    private static final long serialVersionUID = 1L;
    private final String deviceId;

    public RegistrationEvent(Areas area, Type sourceType, String deviceId) {
        if(deviceId == null) throw new IllegalArgumentException("ID de dispositivo tem de ser especificado.");
        super(area, sourceType);
        this.deviceId = deviceId;
    }

    public String getDeviceId() {
        return deviceId;
    }
}