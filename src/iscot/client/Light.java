package iscot.client;

import iscot.client.gui.ActuatorWindow;
import iscot.client.gui.LightIcon;
import iscot.enums.Areas;
import iscot.enums.Type;
import iscot.events.AreaLightingOrderEvent;

public class Light {

    /**
     * @param args IP, porto, área
     */
    public static void main(String[] args) throws InterruptedException {
        IscotClient cliente = IscotClient.fromArgs(args, Type.ACTUATOR);
        if (cliente == null) return;
        Areas area = cliente.getArea();

        LightIcon luz = new LightIcon();
        ActuatorWindow janela = new ActuatorWindow("Luz", area, luz);
        janela.registar("Luz iniciada (" + cliente.getDeviceId() + ")");

        // TODO fase 5: receber e tratar os eventos do servidor
        Thread.sleep(5000);
        aplicarEvento(janela, luz, new AreaLightingOrderEvent(area, true));

        Thread.sleep(5000);
        aplicarEvento(janela, luz, new AreaLightingOrderEvent(area, false));
    }

    private static void aplicarEvento(ActuatorWindow janela, LightIcon luz, AreaLightingOrderEvent evento) {
        luz.setLigada(evento.isTurnOn());
        janela.registar("Lampada " + (evento.isTurnOn() ? "ligada" : "desligada"));
    }
}