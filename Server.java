package mdhs;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class Server {
    private static final int PORT = 6969; // Port number where the server will listen for incoming connections from the Client.    
    
    /**
     * Main method to start the server. 
     * Gen AI provided general guidance on this method of starting the server. 
     * The method used in Tyson's Assignment 2 was originally used, however during testing, it would have trouble starting in different versions of Netbeans and different computing hardware. 
     * Gen AI provided guidance that this method could be used, and it appeared to work across different Netbeans versions and computers. 
     */
    public static void main(String[] args) {
        new Server().startServer(); // Create a server instance and start it. 
    }

    /**
     * Start the server and listen for incoming connections from the Client.
     */
    public void startServer() {
        System.out.println("Server is starting..."); // Display a message to the console that the server is starting. 
        try (ServerSocket serverSocket = new ServerSocket(PORT)) { // Open a server socket on the specified port.
            System.out.println("Server is running on port " + PORT); // Display a message that the server is running.

            while (true) { // Infinite loop to continuously accept new client connections.
                try {
                    Socket clientSocket = serverSocket.accept(); // Accept a client connection.
                    System.out.println("Client connected"); // Display a message to the console that a client has connected. 
                    
                    /**
                     * Start a new thread to handle the client, ensuring each client is handled concurrently.
                     * This method of thread handling was pulled from Tyson's Assignment 1 assignment. 
                     * The method of having ClientHandler extend Thread was not functioning correctly, and was dropping the connection between Client and Server.
                     * Gen AI originally provided assistance with this in Assignment 1. 
                     */
                    new Thread(() -> handleClient(clientSocket)).start();
                } catch (IOException e) {
                    System.err.println("Error accepting client connection: " + e.getMessage()); // Display an error in the console if the connection cannot be accepted.
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.err.println("Server failed to start: " + e.getMessage()); // Log an error if the server socket cannot be opened. 
            e.printStackTrace();
        }
    }
        
    /**
     * Constructor initialises the server on a specified port.
     */
    public Server() throws IOException {
        serverSocket = new ServerSocket(port); // Initialises the ServerSocket on the specified port.
        dbAccess = new DatabaseAccess();  // Initialise the DatabaseAccess to handle database operations.
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
 * Thread to handle individual client connections.
 */
class ClientHandler extends Thread {
    private final Socket clientSocket; // Socket to manage the connection to the client.
    private final DatabaseAccess dbAccess; // Reference to DatabaseAccess to perform database operations.

    /**
     * Constructor assigns the client socket for this handler.
     */
    public ClientHandler(Socket socket) {
        this.clientSocket = socket; // Initialise the client socket.
        this.dbAccess = dbAccess;  // Initialise DatabaseAccess for handling database operations.
    }

    /**
     * Runs the client handler which reads and writes messages to the client.
     */
    @Override
    public void run() {
            try (ObjectInputStream input = new ObjectInputStream(clientSocket.getInputStream());  // Receive data from the client. 
                 ObjectOutputStream output = new ObjectOutputStream(clientSocket.getOutputStream())) { // Send data back to the client. 

                String operationType = (String) input.readObject();  // Read and store the type of operation requested by the client.
                String response = "Operation failed"; // Response if operation does not complete successfully.

                switch (operationType) {
                    case "registerCustomer" -> {
                        Customer customer = (Customer) input.readObject(); // Read the Customer object from the client.
                        response = handleCustomerRegistration(customer); // Handle customer registration and get the response.
                    }
                    case "addSchedule" -> {
                        // to do
                    }
                }
                
                output.writeObject(response);  // Send the response back to the client

            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error handling client data: " + e.getMessage());
            } finally {
                try {
                    clientSocket.close();  // Ensure the client socket is closed after processing. 
                } catch (IOException e) {
                    System.out.println("Error closing client socket: " + e.getMessage());
                }
            }
        }

        private String handleCustomerRegistration(Customer customer) {
        int result = dbAccess.addCustomer(customer.getName(), customer.getEmail(), customer.getPassword(), customer.getAddress(), customer.getPhoneNumber());
        return result > 0 ? "Customer registration successful" : "Failed to register customer"; // Return success or failure message based on the result.
        }
    }

}

