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
     * Method to handle client requests in a separate thread. 
     */
private void handleClient(Socket clientSocket) {
    try (
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream()); // Open an input stream to receive data from the client. 
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream()) // Open an output stream to send data to the client.
        )

            /**
            * This is created only for registering customers. 
            * Modifications need to occur to perform other functions like working with delivery schedules, etc. 
            */
            Customer customer = (Customer) in.readObject(); // Read customer object from the client.
            System.out.println("Received customer data: " + customer); // Log the received customer data.

// To do: add functionality for processing the customer data in the database. 
    
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
    

        private String handleCustomerRegistration(Customer customer) {
        int result = dbAccess.addCustomer(customer.getName(), customer.getEmail(), customer.getPassword(), customer.getAddress(), customer.getPhoneNumber());
        return result > 0 ? "Customer registration successful" : "Failed to register customer"; // Return success or failure message based on the result.
        }
    }

}

