package iscot.client;

import iscot.client.gui.ACIcon;
import iscot.client.gui.ActuatorWindow;
import iscot.enums.AirConditioningMode;
import iscot.enums.Areas;
import iscot.enums.Type;
import iscot.events.AreaTemperatureOrderEvent;

public class AirConditioner {

    /**
     * @param args IP, porto, área
     */
    public static void main(String[] args) throws InterruptedException {
        IscotClient cliente = IscotClient.fromArgs(args, Type.ACTUATOR);
        if (cliente == null) return;
        Areas area = cliente.getArea();

        ACIcon ac = new ACIcon();
        ActuatorWindow janela = new ActuatorWindow("Ar condicionado", area, ac);
        janela.registar("Ar condicionado iniciado (" + cliente.getDeviceId() + ")");

        // TODO fase 5: receber e tratar os eventos do servidor
        Thread.sleep(5000);
        aplicarEvento(janela, ac, new AreaTemperatureOrderEvent(area, AirConditioningMode.Frio));

        Thread.sleep(5000);
        aplicarEvento(janela, ac, new AreaTemperatureOrderEvent(area, AirConditioningMode.Aquecimento));

        // desligar não é uma ordem do decisor (o evento não aceita Desligado): é feito localmente
        Thread.sleep(5000);
        desligar(janela, ac);
    }

    private static void aplicarEvento(ActuatorWindow janela, ACIcon ac, AreaTemperatureOrderEvent evento) {
        ac.setModo(evento.getMode());
        janela.registar("Ar condicionado em modo " + evento.getMode());
    }

    private static void desligar(ActuatorWindow janela, ACIcon ac) {
        ac.setModo(AirConditioningMode.Desligado);
        janela.registar("Ar condicionado desligado");
    }
}