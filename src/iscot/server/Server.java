package iscot.server;

import iscot.enums.Areas;
import iscot.enums.Type;
import iscot.events.Event;
import iscot.events.RegistrationEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Central server for the IscoT message broker.
 *
 * <p>Accepts remote client connections (sensors, deciders, actuators)
 * and assigns a dedicated thread to manage communication with each client.</p>
 */
public class Server {

    public static final int PORTO = 8080;

    private int idCounter = 1;

    /**
     * Generates a unique device identifier for a registering client.
     *
     * @param type the role of the client component
     * @param area the house area where the component operates
     * @return a unique device identifier string
     */
    private synchronized String generateDeviceId(Type type, Areas area) {
        return type + "-" + area + "-" + (idCounter++);
    }

    /**
     * Dedicated worker thread managing interactions with a single connected client.
     */
    private class HandleClient extends Thread {

        private final Socket socket;
        private ObjectOutputStream out;
        private ObjectInputStream in;
        private String deviceId;
        private Areas area;
        private Type clientType;

        /**
         * Creates a client handler for the specified socket.
         *
         * @param socket the client communication socket
         */
        public HandleClient(Socket socket) {
            this.socket = socket;
        }

        /**
         * Initializes object streams for bidirectional communication.
         *
         * @throws IOException if an I/O error occurs while opening the streams
         */
        private void doConnections() throws IOException {
            out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();
            in = new ObjectInputStream(socket.getInputStream());
        }

        @Override
        public void run() {
            try {
                doConnections();

                Object received = in.readObject();
                if (!(received instanceof RegistrationEvent regEvent)) {
                    System.err.println("Rejected: first message must be a RegistrationEvent.");
                    return;
                }

                if (regEvent.getSourceType() == null || regEvent.getArea() == null) {
                    System.err.println("Rejected: invalid Type or Area.");
                    return;
                }

                this.clientType = regEvent.getSourceType();
                this.area = regEvent.getArea();
                this.deviceId = generateDeviceId(clientType, area);
                setName("ClientThread-" + deviceId);
                System.out.println("Client registered: " + deviceId + " [" + clientType + " in " + area + "]");

                RegistrationEvent ack = new RegistrationEvent(this.area, this.clientType, this.deviceId);
                out.writeObject(ack);
                out.flush();

                while (true) {
                    Event event = (Event) in.readObject();
                    System.out.println("Received from " + deviceId + ": " + event);
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Client disconnected: " + (deviceId != null ? deviceId : socket.getRemoteSocketAddress()));
            } finally {
                try {
                    socket.close();
                } catch (IOException ignored) {}
            }
        }
    }

    /**
     * Starts the server loop to listen for and accept incoming connections.
     *
     * @throws IOException if an error occurs when opening or accepting on the socket
     */
    public void startServing() throws IOException {
        try (ServerSocket ss = new ServerSocket(PORTO)) {
            System.out.println("IscoT Server listening on port " + PORTO + "...");
            while (true) {
                Socket socket = ss.accept();
                new HandleClient(socket).start();
            }
        }
    }

    public static void main(String[] args) {
        try {
            new Server().startServing();
        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
        }
    }
}