package iscot.client;

import iscot.enums.Areas;
import iscot.enums.Type;
import iscot.events.RegistrationEvent;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;

/**
 * Ligação de um cliente (sensor, decisor ou atuador) ao servidor IscoT.
 *
 * <p>Trata da validação dos argumentos, da ligação e do registo,
 * para que cada cliente se concentre apenas na sua GUI.</p>
 */
public class IscotClient {

    private final Areas area;
    private final Type type;
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private String deviceId;

    public IscotClient(Areas area, Type type) {
        this.area = area;
        this.type = type;
    }

    /**
     * Valida os argumentos (IP, porto, área), cria o cliente e liga-o ao servidor.
     *
     * @return o cliente já registado, ou {@code null} se algo falhar
     */
    public static IscotClient fromArgs(String[] args, Type type) {
        if (args.length != 3) {
            System.err.println("Invalid arguments! Uso: IP PORTO Area");
            return null;
        }

        Areas area;
        try {
            area = Areas.valueOf(args[2]);
        } catch (IllegalArgumentException e) {
            System.err.println("Área inválida: " + args[2] + ". Válidas: " + Arrays.toString(Areas.values()));
            return null;
        }

        int port;
        try {
            port = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.err.println("Porto inválido: " + args[1] + ". Tem de ser um número inteiro.");
            return null;
        }

        IscotClient cliente = new IscotClient(area, type);
        try {
            cliente.connect(args[0], port);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Não foi possível ligar ao servidor: " + e.getMessage());
            return null;
        }

        return cliente;
    }

    /**
     * Abre a ligação, cria os canais e regista o cliente no servidor.
     */
    public void connect(String ip, int port) throws IOException, ClassNotFoundException {
        socket = new Socket(ip, port);

        // ordem inversa à do servidor (que cria o out primeiro)
        in = new ObjectInputStream(socket.getInputStream());
        out = new ObjectOutputStream(socket.getOutputStream());

        out.writeObject(new RegistrationEvent(area, type));
        out.flush();

        RegistrationEvent resposta = (RegistrationEvent) in.readObject();
        deviceId = resposta.getDeviceId();
        System.out.println("Registado como " + deviceId);
    }

    public Areas getArea() {
        return area;
    }

    public Type getType() {
        return type;
    }

    public String getDeviceId() {
        return deviceId;
    }
}