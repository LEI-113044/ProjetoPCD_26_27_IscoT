package iscot.client;

import iscot.client.gui.SensorWindow;
import iscot.enums.Areas;
import iscot.enums.Type;
import iscot.events.HumiditySensorEvent;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class HumiditySensor {

	/**
	 * @param args IP, porto, área
	 */
	public static void main(String[] args) {
		IscotClient cliente = IscotClient.fromArgs(args, Type.SENSOR);
		if (cliente == null) return;
		Areas area = cliente.getArea();

		new SensorWindow("Humidade", "💧", area, "%", 0, 100, 1, 50, valor -> {
			HumiditySensorEvent evento = new HumiditySensorEvent(area, valor);
			String timestamp = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
			System.out.println("Evento: HumiditySensorEvent [" + evento.getArea() + "] Valor: "
					+ evento.getHumidity() + "% - " + timestamp);
			// TODO fase 5: enviar o evento ao servidor
		});
	}
}