package iscot.client;

import iscot.client.gui.SensorWindow;
import iscot.enums.Areas;
import iscot.enums.Type;
import iscot.events.LightSensorEvent;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LightSensor {

    /**
     * @param args IP, porto, área
     */
    public static void main(String[] args) {
        IscotClient cliente = IscotClient.fromArgs(args, Type.SENSOR);
        if (cliente == null) return;
        Areas area = cliente.getArea();

        new SensorWindow("Luminosidade", "*", area, " lx", 0, 100, 1, 50, valor -> {
            LightSensorEvent evento = new LightSensorEvent(area, valor);
            String timestamp = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            System.out.println("Evento: LightSensorEvent [" + evento.getArea() + "] Valor: "
                    + evento.getLight() + " lx - " + timestamp);
            // TODO fase 5: enviar o evento ao servidor
        });
    }
}