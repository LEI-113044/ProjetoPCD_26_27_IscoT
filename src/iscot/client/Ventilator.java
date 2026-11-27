package iscot.client;

import iscot.client.gui.ActuatorWindow;
import iscot.client.gui.VentilatorIcon;
import iscot.enums.Areas;
import iscot.enums.Type;
import iscot.events.AreaVentilationOrderEvent;

public class Ventilator {

    /**
     * @param args IP, porto, área
     */
    public static void main(String[] args) throws InterruptedException {
        IscotClient cliente = IscotClient.fromArgs(args, Type.ACTUATOR);
        if (cliente == null) return;
        Areas area = cliente.getArea();

        VentilatorIcon ventilador = new VentilatorIcon();
        ActuatorWindow janela = new ActuatorWindow("Ventilador", area, ventilador);
        janela.registar("Ventilador iniciado (" + cliente.getDeviceId() + ")");

        // TODO fase 5: receber e tratar os eventos do servidor
        Thread.sleep(5000);
        aplicarEvento(janela, ventilador, new AreaVentilationOrderEvent(area, true));

        Thread.sleep(5000);
        aplicarEvento(janela, ventilador, new AreaVentilationOrderEvent(area, false));
    }

    private static void aplicarEvento(ActuatorWindow janela, VentilatorIcon ventilador, AreaVentilationOrderEvent evento) {
        ventilador.setLigado(evento.isTurnOn());
        janela.registar("Ventilador " + (evento.isTurnOn() ? "ligado" : "desligado"));
    }
}