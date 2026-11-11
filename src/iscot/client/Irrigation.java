package iscot.client;

import java.awt.BorderLayout;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JPanel;
import iscot.client.gui.IrrigationIcon;
import iscot.enums.Areas;
import iscot.events.AreaIrrigationOrderEvent;

public class Irrigation {

    /**
     * @param args IP, porto, área (a rega só deverá existir no jardim Garden)
     */
    public static void main(String[] args) throws InterruptedException {
        if (args.length != 3) {
            System.err.println("Invalid arguments! Uso: IP PORT Garden");
            return;
        }

        Areas area;
        try {
            area = Areas.valueOf(args[2]);
        } catch (IllegalArgumentException e) {
            System.err.println("Área inválida");
            return;
        }
        if (area != Areas.Garden) {
            System.err.println("A rega só pode ser instalada na área Garden.");
            return;
        }

        // TODO connect to server

        // TODO register with the server

        JFrame janela = new JFrame("Rega em " + area);
        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel painel = new JPanel();
        painel.setLayout(new BorderLayout());
        IrrigationIcon rega = new IrrigationIcon();
        painel.add(rega, BorderLayout.CENTER);
        janela.setContentPane(painel);
        janela.setLocation(600, 400);
        janela.pack();
        janela.setMinimumSize(janela.getSize());
        janela.setVisible(true);

        System.out.println("[GUI] Rega iniciada na área " + area + ". À espera de eventos...");

        // TODO: receive and handle messages from server

        Thread.sleep(5000);
        aplicarEvento(rega, new AreaIrrigationOrderEvent(true));

        Thread.sleep(5000);
        aplicarEvento(rega, new AreaIrrigationOrderEvent(false));
    }

    private static void aplicarEvento(IrrigationIcon rega, AreaIrrigationOrderEvent evento) {
        rega.setAtiva(evento.isTurnOn());

        String estado = evento.isTurnOn() ? "ligada" : "desligada";
        String timestamp = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        System.out.println("Rega " + estado + " - " + timestamp);
    }
}