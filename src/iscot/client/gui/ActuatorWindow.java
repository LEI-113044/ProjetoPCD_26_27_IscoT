package iscot.client.gui;

import iscot.enums.Areas;

import java.awt.BorderLayout;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;

public class ActuatorWindow {
    private final JTextArea historico = new JTextArea(8, 25);

    public ActuatorWindow(String titulo, Areas area, ComponentState icone) {
        JFrame janela = new JFrame(titulo + " em " + area);
        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        historico.setEditable(false);

        JPanel painel = new JPanel(new BorderLayout());
        painel.add(icone, BorderLayout.CENTER);
        painel.add(new JScrollPane(historico), BorderLayout.SOUTH);

        janela.setContentPane(painel);
        janela.setLocation(600, 400);
        janela.pack();
        janela.setMinimumSize(janela.getSize());
        janela.setVisible(true);
    }

    public void registar(String acao) {
        String linha = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + " - " + acao;
        System.out.println(linha);
        SwingUtilities.invokeLater(() -> historico.append(linha + "\n"));
    }
}