package iscot.events;

import iscot.enums.Areas;
import iscot.enums.Type;

import java.io.Serial;

/**
 * Event announcing a component's registration in an area.
 */
public class RegistrationEvent extends Event {
    @Serial
    private static final long serialVersionUID = 1L;

    private final String deviceId;

    /**
     * Creates a registration event for a component.
     *
     * @param area area in which the component is registering
     * @param sourceType role of the component being registered
     * @param deviceId identifier of the component being registered
     * @throws NullPointerException if {@code area}, {@code sourceType}, or {@code deviceId} is {@code null}
     */
    public RegistrationEvent(Areas area, Type sourceType, String deviceId) {
        if(deviceId == null) throw new NullPointerException("ID de dispositivo tem de ser especificado.");
        super(area, sourceType);
        this.deviceId = deviceId;
    }

    /**
     * Returns the identifier of the registered component.
     *
     * @return the component identifier
     */
    public String getDeviceId() {
        return deviceId;
    }
}