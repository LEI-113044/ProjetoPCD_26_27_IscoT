package iscot.server;

import iscot.enums.Areas;
import iscot.enums.Type;
import iscot.events.Event;
import iscot.events.HighLevelOrderEvent;
import iscot.coordination.ModifiedCountDownLatch;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Server {
    private int port;
    private boolean running = true;
    private Map<String, LinkedList<Event>> topicQueues = new HashMap<>();

    public Server(int port) {
        this.port = port;
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (running) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Novo cliente ligado por: " + clientSocket.getRemoteSocketAddress());
                ClientHandler clientHandler = ClientHandler(clientSocket);
                clientHandler.start();
            }
        }catch(IOException e) {
            System.err.println("Erro no servidor: " + e.getMessage());
        }
    }

    private static class ClientHandler extends Thread {
        private Socket socket;
        private ObjectInputStream in;
        private ObjectOutputStream out;
        private Type clientType;
        private Areas clientArea;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                out = new ObjectOutputStream(socket.getOutputStream());
                in = new ObjectInputStream(socket.getInputStream());
                Object firstMessage = in.readObject();
                if (firstMessage instanceof Event) {
                    // Aqui deve validar o RegistrationMessage, extrair o tipo e a área,
                    // e verificar se são válidos de acordo com os enumerados.
                    System.out.println("Cliente registado com sucesso.");
                }
                while (true) {
                    Object received = in.readObject();
                    if (received instanceof Event) {
                        Event event = (Event) received;
                        handleEvent(event);
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Conexão terminada com o cliente: " + socket.getRemoteSocketAddress());
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    System.err.println("Erro ao finalizar o socket: " + e.getMessage());
                }
            }
        }

        private void handleEvent(Event event) {
            // Lógica para encaminhar o evento para a fila correta do tópico
            // Se for HighLevelOrderEvent, aplicar o ModifiedCountDownLatch conforme necessário.
        }
    }

    static void main(String[] args) {
        int port = 12345; // Porta padrão de teste
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        Server server = new Server(port);
        server.start();
    }
}
