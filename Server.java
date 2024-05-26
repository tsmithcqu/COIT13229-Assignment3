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
    private int port = 6969; // Port number where the server will listen for incoming connections from the Client. 
    private ServerSocket serverSocket; // ServerSocket variable for managing client connections.

    /**
     * Constructor initialises the server on a specified port.
     */
    public Server() throws IOException {
        serverSocket = new ServerSocket(port); // Initialises the ServerSocket on the specified port.
        System.out.println("MDHS Server is running on port " + port); // Console output indicating server is running. 
    }

    /**
     * Listens for incoming client connections and handles them concurrently.
     */
    public void listen() {
        while (true) { // Infinite loop to continuously listen for client connections. 
            try {
                Socket clientSocket = serverSocket.accept(); // Accept an incoming client connection. 
                new ClientHandler(clientSocket).start(); // Handle each client in a new thread
            } catch (IOException e) {
                System.out.println("Error accepting client connection: " + e.getMessage()); // Displays error message to the console if connection fails
            }
        }
    }

    /**
     * Main method to start the server.
     */
    public static void main(String[] args) {
        try {
            Server server = new Server(); // Create a new Server object. 
            server.listen(); // Call listen method to start listening for clients. 
        } catch (IOException e) {
            System.out.println("Server failed to start: " + e.getMessage()); // Error message displayed to the console if the server fails to start. 
        }
    }

}

/**
 * Thread to handle individual client connections.
 */
class ClientHandler extends Thread {
    private Socket clientSocket; // Socket for communication with the connected client. 

    /**
     * Constructor assigns the client socket for this handler.
     */
    public ClientHandler(Socket socket) {
        this.clientSocket = socket; // Assign the socket from the parameter to the clientSocket. 
    }

    /**
     * Runs the client handler which reads and writes messages to the client.
     */
    @Override
    public void run() {
        try {
            DataInputStream input = new DataInputStream(clientSocket.getInputStream()); // Stream to read data from the client. 
            DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream()); // Stream to send data to the client. 
            output.writeUTF("You are connected to the server."); // Send a message to the client. 
            String clientMessage = input.readUTF(); // Read a message sent by the client. 
            System.out.println("Message from client: " + clientMessage); // Print the message from the client. 
            clientSocket.close(); // Close the connection with the client. 
        } catch (IOException e) {
            System.out.println("Error handling client data: " + e.getMessage()); // Error message displayed to the console if there is an issue with client data handling. 
        }
    }
}

