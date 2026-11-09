package iscot.events;

import iscot.enums.Areas;
import iscot.enums.AirConditioningMode;

import java.io.Serial;
import java.util.Objects;

/**
 * Decider order specifying heating or cooling for an area.
 */
public class AreaTemperatureOrderEvent extends HighLevelOrderEvent {
    @Serial
    private static final long serialVersionUID = 1L;

    private final AirConditioningMode mode;

    /**
     * Creates an order to set the air-conditioning mode for an area.
     *
     * @param area the area to which the order applies
     * @param mode the requested mode: heating or cooling
     * @throws IllegalArgumentException if {@code mode} is neither heating nor cooling
     * @throws NullPointerException if {@code area} or {@code mode} is {@code null}
     */
    public AreaTemperatureOrderEvent(Areas area, AirConditioningMode mode) {
        if(mode == AirConditioningMode.Desligado || mode == AirConditioningMode.Ventilação) throw new IllegalArgumentException("Só pode ordenar o modo de Aquecimento ou Arrefecimento.");
        super(area);
        this.mode = Objects.requireNonNull(mode, "mode");
    }

    /**
     * Returns the air-conditioning mode requested by this order.
     *
     * @return the requested mode
     */
    public AirConditioningMode getMode() {
        return mode;
    }
}