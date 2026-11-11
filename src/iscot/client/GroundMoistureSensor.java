package iscot.client;

import java.awt.BorderLayout;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JFrame;
import javax.swing.JPanel;
import iscot.client.gui.SensorControl;
import iscot.enums.Areas;
import iscot.events.GroundMoistureSensorEvent;

public class GroundMoistureSensor {

    /**
     * @param args IP, porto, área
     */
    public static void main(String[] args) {
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

        // TODO connect to the server

        // TODO register with the server

        JFrame janela = new JFrame("Humidade do solo em " + area);
        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel painel = new JPanel();
        painel.setLayout(new BorderLayout());
        SensorControl sensorControl = new SensorControl("*", "Humidade do solo - " + area, "%",
                0, 100, 1, 50,
                valor -> {
                    GroundMoistureSensorEvent evento = new GroundMoistureSensorEvent(area, valor);
                    String timestamp = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
                    System.out.println("Evento: GroundMoistureSensorEvent [" + evento.getArea() + "] Valor: "
                            + evento.getGroundMoisture() + "% - " + timestamp);
                });
        painel.add(sensorControl, BorderLayout.CENTER);
        janela.setContentPane(painel);
        janela.setLocation(600, 400);
        janela.pack();
        janela.setMinimumSize(janela.getSize());
        janela.setVisible(true);
    }
}