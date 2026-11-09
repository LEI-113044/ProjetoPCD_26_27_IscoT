package iscot.events;

import iscot.enums.Areas;

public class AreaVentilationOrderEvent extends HighLevelOrderEvent {
    public AreaVentilationOrderEvent(Areas area, boolean active) {
        super(area, active);
    }
}
