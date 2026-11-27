package iscot.client;

import iscot.client.gui.SensorWindow;
import iscot.enums.Areas;
import iscot.enums.Type;
import iscot.events.GroundMoistureSensorEvent;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class GroundMoistureSensor {

    /**
     * @param args IP, porto, área
     */
    public static void main(String[] args) {
        IscotClient cliente = IscotClient.fromArgs(args, Type.SENSOR);
        if (cliente == null) return;
        Areas area = cliente.getArea();

        new SensorWindow("Humidade do solo", "*", area, "%", 0, 100, 1, 50, valor -> {
            GroundMoistureSensorEvent evento = new GroundMoistureSensorEvent(area, valor);
            String timestamp = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            System.out.println("Evento: GroundMoistureSensorEvent [" + evento.getArea() + "] Valor: "
                    + evento.getGroundMoisture() + "% - " + timestamp);
            // TODO fase 5: enviar o evento ao servidor
        });
    }
}