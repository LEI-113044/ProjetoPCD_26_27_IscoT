package iscot.client;

import iscot.client.gui.SensorWindow;
import iscot.enums.Areas;
import iscot.enums.Type;
import iscot.events.TemperatureSensorEvent;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TemperatureSensor {

    /**
     * @param args IP, porto, área
     */
    public static void main(String[] args) {
        IscotClient cliente = IscotClient.fromArgs(args, Type.SENSOR);
        if (cliente == null) return;
        Areas area = cliente.getArea();

        new SensorWindow("Temperatura", "*", area, " °C", 0, 100, 1, 50, valor -> {
            TemperatureSensorEvent evento = new TemperatureSensorEvent(area, valor);
            String timestamp = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            System.out.println("Evento: TemperatureSensorEvent [" + evento.getArea() + "] Valor: "
                    + evento.getTemperature() + " °C - " + timestamp);
            // TODO fase 5: enviar o evento ao servidor
        });
    }
}