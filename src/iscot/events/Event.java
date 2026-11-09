package iscot.events;

import iscot.enums.Areas;
import iscot.enums.Type;

import java.io.Serializable;
import java.time.LocalTime;

public abstract class Event implements Serializable {
    private static final long serialVersionUID = 1L;

    private final Areas area;
    private final Type type;
    private final LocalTime timestamp;

    public Event(Areas area, Type type) {
        this.area = area;
        this.type = type;
        this.timestamp = LocalTime.now();
    }

    public Areas getArea() {
        return area;
    }

    public Type getType() {
        return type;
    }

    public LocalTime getTimestamp() {
        return timestamp;
    }
}