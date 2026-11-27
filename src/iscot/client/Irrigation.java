package iscot.client;

import iscot.client.gui.ActuatorWindow;
import iscot.client.gui.IrrigationIcon;
import iscot.enums.Areas;
import iscot.enums.Type;
import iscot.events.AreaIrrigationOrderEvent;

public class Irrigation {

    /**
     * @param args IP, porto, área (a rega só existe no jardim: Garden)
     */
    public static void main(String[] args) throws InterruptedException {
        // verificar a área antes de se registar no servidor
        if (args.length == 3 && !args[2].equals(Areas.Garden.name())) {
            System.err.println("A rega só pode ser instalada na área Garden.");
            return;
        }

        IscotClient cliente = IscotClient.fromArgs(args, Type.ACTUATOR);
        if (cliente == null) return;
        Areas area = cliente.getArea();

        IrrigationIcon rega = new IrrigationIcon();
        ActuatorWindow janela = new ActuatorWindow("Rega", area, rega);
        janela.registar("Rega iniciada (" + cliente.getDeviceId() + ")");

        // TODO fase 5: receber e tratar os eventos do servidor
        Thread.sleep(5000);
        aplicarEvento(janela, rega, new AreaIrrigationOrderEvent(true));

        Thread.sleep(5000);
        aplicarEvento(janela, rega, new AreaIrrigationOrderEvent(false));
    }

    private static void aplicarEvento(ActuatorWindow janela, IrrigationIcon rega, AreaIrrigationOrderEvent evento) {
        rega.setAtiva(evento.isTurnOn());
        janela.registar("Rega " + (evento.isTurnOn() ? "ligada" : "desligada"));
    }
}