package iscot.events;

import iscot.enums.Areas;
import iscot.enums.Type;

public class HighLevelOrderEvent extends Event {
    private final boolean active;

    public HighLevelOrderEvent(Areas area, boolean active) {
        super(area, Type.ACTUATOR);
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }
}
