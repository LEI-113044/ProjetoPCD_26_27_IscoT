package iscot.client;

import java.awt.BorderLayout;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JFrame;
import javax.swing.JPanel;
import iscot.client.gui.LightIcon;
import iscot.enums.Areas;
import iscot.events.AreaLightingOrderEvent;

public class Light {

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

        JFrame janela = new JFrame("Luz em " + area);
        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel painel = new JPanel();
        painel.setLayout(new BorderLayout());
        LightIcon luz = new LightIcon();
        painel.add(luz, BorderLayout.CENTER);
        janela.setContentPane(painel);
        janela.setLocation(600, 400);
        janela.pack();
        janela.setMinimumSize(janela.getSize());
        janela.setVisible(true);

        System.out.println("[GUI] Luz iniciada na área " + area + ". À espera de eventos...");

        // TODO: receive and handle messages from server
        Thread.sleep(5000);
        aplicarEvento(luz, new AreaLightingOrderEvent(area, true));

        Thread.sleep(5000);
        aplicarEvento(luz, new AreaLightingOrderEvent(area, false));
    }

    private static void aplicarEvento(LightIcon luz, AreaLightingOrderEvent evento) {
        luz.setLigada(evento.isTurnOn());

        String estado = evento.isTurnOn() ? "ligada" : "desligada";
        String timestamp = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        System.out.println("Lâmpada " + estado + " - " + timestamp);
    }
}