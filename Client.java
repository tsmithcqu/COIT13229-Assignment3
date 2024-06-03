package mhds;

import java.io.*;
import java.net.Socket;
import java.util.List;
import javax.swing.JOptionPane; 

/**
 * Class to handle communication with the server. 
 */
public class Client {
     /**
     * Set the server details as constants. 
     */
    private static final String SERVER_ADDRESS = "localhost";  // The IP address of the server, in this instance is on the same computer as the Server. In a network environment, this will need to be changed. 
    private static final int SERVER_PORT = 6969;  // The port number on which the server is listening. 

    /**
     * Method to send customer data to the server. 
     * More methods will need to be added for different functions of the program. 
     */
     public void sendCustomerData(Customer customer) {
          sendRequest("ADD_CUSTOMER", customer); // Send request to add a customer.
     }

     /**
     * Method to fetch all registered customers from the database.
     */
     public List<Customer> fetchAllCustomers() {
         return sendRequest("VIEW_CUSTOMERS", null); // Send request to view all customers.
     }

     // Tyson to do: Build out the ability for admins to add products to the database, and list products from the database. 

     // ADD MORE FUNCTIONS HERE.
     
         /**
         * Establish a connection with the server and open streams.
         * Gen AI provided general guidance on the usage of the generic type parameter <T> for the expandabilitiy and reusability of the sendRequest method. 
         */
     private <T> T sendRequest(String action, Object data) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);  // Connect to the server at the specified address and port.
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());  // Create an ObjectOutputStream to send data to the server.
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {  // Create an ObjectInputStream to receive data from the server.

            out.writeObject(action);  // Serialise and send the Customer object to the server.
            if (data != null) { // Ensures that only valid, non-null data is serialised and sent to the server.
                out.writeObject(data);  // Serialie and send the data object to the server, if any.
            }
            out.flush();  // Flush the output stream to ensure all data is sent.
             
            T response = (T) in.readObject(); // Wait for a response from the server and read it.
            return response; // Return the response received from the server.

        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error communicating with the server: " + e.getMessage(), "Connection Error", JOptionPane.ERROR_MESSAGE); // Show an error dialog if there is a problem communicating with the server.
            return null; // Return null in case of an error.
        }
    }
}

/**

OLD CODE BELOW

    public void createDeliverySchedule(DeliverySchedule schedule) {
        new Thread(() -> handleOperation(schedule, "createDeliverySchedule")).start();
    }

    public void registerCustomer(Customer customer) {
    new Thread(() -> handleOperation(customer, "createCustomer")).start();
    }
    */
    
    // ADD MORE AS NEEDED.
    // For each of them you need to call the 'handleOperation' method. 

    // Method to handle operations.
    
    /**
    MOVING DATA STREAMING TO THE INDIVIDUAL CLASSES
    
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
            */
