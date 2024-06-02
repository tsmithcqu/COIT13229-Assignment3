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
    private final int port = 6969; // Port number where the server will listen for incoming connections from the Client. 
    private final ServerSocket serverSocket; // ServerSocket variable for managing client connections.
    private final DatabaseAccess dbAccess;  // DatabaseAccess instance for database operations.

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

