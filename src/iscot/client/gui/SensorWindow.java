package iscot.client.gui;

import iscot.enums.Areas;

import java.awt.BorderLayout;
import java.util.function.DoubleConsumer;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Janela partilhada pelos sensores: um slider ({@link SensorControl}) que simula a leitura.
 */
public class SensorWindow {

    /**
     * Cria e mostra a janela do sensor.
     *
     * @param titulo      nome do sensor (ex.: "Temperatura")
     * @param icone       símbolo mostrado no título do painel
     * @param area        área onde o sensor está instalado
     * @param unidade     unidade da leitura (ex.: " °C")
     * @param minimo      valor mínimo do slider
     * @param maximo      valor máximo do slider
     * @param passo       incremento do slider
     * @param inicial     valor inicial do slider
     * @param aoMudar     ação executada quando o utilizador larga o slider
     */
    public SensorWindow(String titulo, String icone, Areas area, String unidade,
                        double minimo, double maximo, double passo, double inicial,
                        DoubleConsumer aoMudar) {
        JFrame janela = new JFrame(titulo + " em " + area);
        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel painel = new JPanel(new BorderLayout());
        painel.add(new SensorControl(icone, titulo + " - " + area, unidade,
                minimo, maximo, passo, inicial, aoMudar), BorderLayout.CENTER);

        janela.setContentPane(painel);
        janela.setLocation(600, 400);
        janela.pack();
        janela.setMinimumSize(janela.getSize());
        janela.setVisible(true);
    }
}