package iscot.client;

import java.awt.BorderLayout;
import java.io.IOException;
import java.net.UnknownHostException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JFrame;
import javax.swing.JPanel;

import iscot.client.gui.VentilatorIcon;
import iscot.enums.Areas;
import iscot.events.AreaVentilationOrderEvent;

public class Ventilator {

	/**
	 * 
	 * @param server address and port
	 * @param roomName: string
	 * @throws IOException 
	 * @throws UnknownHostException 
	 * @throws NumberFormatException 
	 * @throws InterruptedException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws NumberFormatException, UnknownHostException, IOException, InterruptedException, ClassNotFoundException {
		if(args.length!=3) {
			System.err.println("Invalid arguments!");
		}
		else {
			// TODO connect to server
			
			// TODO register with the server
			Areas area=Areas.valueOf(args[2]);
			
			JFrame janela = new JFrame("Ventilador em "+area);
			janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			
			// Criar os componentes gráficos
			JPanel painel=new JPanel();
			painel.setLayout(new BorderLayout());
			VentilatorIcon ventilator = new VentilatorIcon();
			painel.add(ventilator,BorderLayout.CENTER);
			janela.setContentPane(painel);
			janela.setLocation(600, 400);
			janela.setMinimumSize(janela.getSize());
			janela.pack();
			janela.setVisible(true);

			System.out.println("[GUI] Ventilador iniciado na área " + area + ". À espera de eventos...");

			// TODO: receive and handle messages from server
			
			// TODO: for first phase: create an appropriate event hardcoded locally and change the GUI accordingly

			Thread.sleep(5000);
			aplicarEvento(ventilator, new AreaVentilationOrderEvent(area, true));

			Thread.sleep(5000);
			aplicarEvento(ventilator, new AreaVentilationOrderEvent(area, false));
		}
	}

	private static void aplicarEvento(VentilatorIcon ventilator, AreaVentilationOrderEvent evento) {
		ventilator.setAtivo(evento.isTurnOn());

		String estado = evento.isTurnOn() ? "ligado" : "desligado";
		String timestamp = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
		System.out.println("Ventilador " + estado + " - " + timestamp);
	}
}
