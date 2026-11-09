package iscot.events;

import iscot.enums.Areas;
import iscot.enums.Type;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

public abstract class Event implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final Areas area;
    private final Type sourceType;
    private final Instant timestamp;

    public Event(Areas area, Type sourceType) {
        if(area == null || sourceType == null) throw new IllegalArgumentException("Área e tipo têm de ser especificados.");
        this.area = area;
        this.sourceType = sourceType;
        this.timestamp = Instant.now();
    }

    public Areas getArea() {
        return area;
    }

    public Type getSourceType() {
        return sourceType;
    }

    public Instant getTimestamp() {
        return timestamp;
    }
}