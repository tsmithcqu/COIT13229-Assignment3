package mhds;

import java.io.*;
import java.net.Socket;

public class Client {
    private static final String SERVER_ADDRESS = "localhost";  // Server IP address
    private static final int SERVER_PORT = 6969;  // Server port number

    public Client() {
    }

    // Method to handle creating a customer

    // Method to handle deleting a customer

    // Method to handle creating a delivery schedule

    // Method to handle deleting a delivery schedule

    // ADD MORE AS NEEDED.
    // For each of them you need to call the 'handleOperation' method. 

    // Method to handle operations.
    private void handleOperation(Object data, String operationType) {
        try (
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);  // Use the server details specified above. 
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream()); // Output stream to send data to the server.
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream()) // Input stream to read data from the server. 
        ) {
            out.writeObject(data);  // Serialise and send the data object to the server.
            out.flush();  // Flush the stream to ensure all data is sent immediately.

            Object response = in.readObject();  // Deserialise and read the response from the server.
            
            // The below will need to be change - it will need to be displayed to the GUI. Not sure how yet. 
            System.out.println("Response from server: " + response);  // Output the response received from the server.

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error communicating with the server: " + e.getMessage());  // Handle IO and Class Not Found errors.
        }
    }
}
