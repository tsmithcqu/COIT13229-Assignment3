package mdhs;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Server class to handle client connections.
 * This server listens on a specified port and handles incoming connections
 * on separate threads to allow concurrent handling of clients.
 */
public class Server {
    private int port = 6969;
    private ServerSocket serverSocket;

    /**
     * Constructor initialises the server on a specified port.
     */
    public Server() throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("MDHS Server is running on port " + port);
    }

    /**
     * Listens for incoming client connections and handles them concurrently.
     */
    public void listen() {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                new ClientHandler(clientSocket).start(); // Handle each client in a new thread
            } catch (IOException e) {
                System.out.println("Error accepting client connection: " + e.getMessage());
            }
        }
    }

    //To do: start the server here.
}


/**
 * Thread to handle individual client connections.
 */
class ClientHandler extends Thread {
    private Socket clientSocket;

    /**
     * Constructor assigns the client socket for this handler.
     */
    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }
}

// To do: Data input and output streams to talk to client. 
// To do: add comments to each line of code. 
