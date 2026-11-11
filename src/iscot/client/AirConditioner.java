package iscot.client;

import java.awt.BorderLayout;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JFrame;
import javax.swing.JPanel;
import iscot.client.gui.ACIcon;
import iscot.enums.AirConditioningMode;
import iscot.enums.Areas;
import iscot.events.AreaTemperatureOrderEvent;

public class AirConditioner {

    /**
     * @param args IP, porto, área
     */
    public static void main(String[] args) throws InterruptedException {
        if (args.length != 3) {
            System.err.println("Invalid arguments!");
            return;
        }

        Areas area;
        try {
            area = Areas.valueOf(args[2]);
        } catch (IllegalArgumentException e) {
            System.err.println("Área inválida");
            return;
        }

        // TODO connect to server

        // TODO register with the server

        JFrame janela = new JFrame("Ar condicionado em " + area);
        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel painel = new JPanel();
        painel.setLayout(new BorderLayout());
        ACIcon ac = new ACIcon();
        painel.add(ac, BorderLayout.CENTER);
        janela.setContentPane(painel);
        janela.setLocation(600, 400);
        janela.pack();
        janela.setMinimumSize(janela.getSize());
        janela.setVisible(true);

        System.out.println("[GUI] Ar condicionado iniciado na área " + area + ". À espera de eventos...");

        // TODO: receive and handle messages from server
        // nao funciona por enquanto
        Thread.sleep(5000);
        aplicarEvento(ac, new AreaTemperatureOrderEvent(area, AirConditioningMode.Frio));

        Thread.sleep(5000);
        aplicarEvento(ac, new AreaTemperatureOrderEvent(area, AirConditioningMode.Aquecimento));

        Thread.sleep(5000);
        desligar(ac);
    }

    private static void aplicarEvento(ACIcon ac, AreaTemperatureOrderEvent evento) {
        ac.setModo(evento.getMode());
        log("Ar condicionado em modo " + evento.getMode());
    }

    private static void desligar(ACIcon ac) {
        ac.setModo(AirConditioningMode.Desligado);
        log("Ar condicionado desligado");
    }

    private static void log(String mensagem) {
        String timestamp = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        System.out.println(mensagem + " - " + timestamp);
    }
}