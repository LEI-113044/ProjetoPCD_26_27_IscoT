package iscot.events;

import iscot.enums.Areas;
import iscot.enums.Type;

/**
 * Base class for orders produced by a decider.
 *
 * <p>These events always have {@link Type#DECIDER} as their source type.</p>
 */
public abstract class HighLevelOrderEvent extends Event {
    /**
     * Creates an order associated with the specified area.
     *
     * @param area area to which the order applies
     * @throws NullPointerException if {@code area} is {@code null}
     */
    public HighLevelOrderEvent(Areas area) {
        super(area, Type.DECIDER);
    }
}