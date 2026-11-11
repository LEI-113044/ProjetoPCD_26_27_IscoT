package iscot.events;

import iscot.enums.Areas;
import iscot.enums.Type;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

/**
 * Base class for events exchanged between IscoT components.
 *
 * <p>Each event is associated with an area, the role of its source component,
 * and the instant at which it was created.</p>
 */
public abstract class Event implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final Areas area;
    private final Type sourceType;
    private final Instant timestamp;

    /**
     * Creates an event for the specified area and source type.
     *
     * @param area the area associated with the event
     * @param sourceType the type of component that produced the event
     * @throws NullPointerException if {@code area} or {@code sourceType} is {@code null}
     */
    public Event(Areas area, Type sourceType) {
        if(area == null || sourceType == null) throw new NullPointerException("Area and type have to be specified.");
        this.area = area;
        this.sourceType = sourceType;
        this.timestamp = Instant.now();
    }

    /**
     * Returns the area associated with this event.
     *
     * @return the event area
     */
    public Areas getArea() {
        return area;
    }

    /**
     * Returns the role of the component that produced this event.
     *
     * @return the source component's role
     */
    public Type getSourceType() {
        return sourceType;
    }

    /**
     * Returns the time at which this event was created.
     *
     * @return the event creation time
     */
    public Instant getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Event{" +
                "area=" + area +
                ", sourceType=" + sourceType +
                ", timestamp=" + timestamp +
                '}';
    }
}