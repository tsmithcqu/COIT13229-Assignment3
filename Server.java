package mhds;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;

public class Server {
    private static final int PORT = 6969; // Port number where the server will listen for incoming connections from the Client.    
    
     /**
     * Main method to start the server. 
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
                     * No code was developed by Gen AI.
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
        )   {
            String action = (String) in.readObject(); // Read the action string from the client.

            /**
             * Gen AI suggested using Boolean for database interactions, to provide a simpler way of identifying if the database action was performed. 
             * Gen AI provided general guidance on using switch-case, rather than if-else. 
             * No code was developed by Gen AI. 
             */   
            switch (action) {

                /**
                * Add the customer using the DatabaseAccess instance.
                */
                case "ADD_CUSTOMER": // Case to handle adding a new customer.
                Customer customer = (Customer) in.readObject(); // Read customer object from the client.
                System.out.println("Received customer data: " + customer); // Log the received customer data.
                try (DatabaseAccess dbAccess = new DatabaseAccess()) {
                    boolean success = dbAccess.addCustomer(customer); // Attempt to add customer to the database.
                    String response = success ? "Customer processed successfully." : "Failed to process customer."; // Prepare response based on the operation's success
                    out.writeObject(response); // Send the response back to the client to be displayed by the GUI. 
                } catch (SQLException e) {
                    out.writeObject("Database error: " + e.getMessage()); // Send an error message if there is a database issue. 
                    System.err.println("Database access error: " + e.getMessage()); // Display a message in the console that there is a database access error. 
                    e.printStackTrace();
                }
                break;
            
                /**
                * View customers using the DatabaseAccess instance.
                */
                case "VIEW_CUSTOMERS": // Case to handle viewing all customers.
                    try (DatabaseAccess dbAccess = new DatabaseAccess()) {
                        List<Customer> customers = dbAccess.getAllCustomers(); // Retrieve all customers from the database.
                        out.writeObject(customers); // Send the list of customers back to the client.
                    } catch (SQLException e) {
                        out.writeObject("Database error: " + e.getMessage()); // Send an error message if there is a database issue.
                        System.err.println("Database access error: " + e.getMessage()); // Display a message in the console that there is a database access error.
                        e.printStackTrace();
                    }
                    break; 

                    /**
                    * Add the products data using the DatabaseAccess instance.
                    */
                    case "ADD_PRODUCT": // Case to handle adding a new product.
                    Product product = (Product) in.readObject(); // Read product object from the client.
                    System.out.println("Received product data: " + product); // Log the received product data.

                    try (DatabaseAccess dbAccess = new DatabaseAccess()) {
                        boolean success = dbAccess.addProduct(product); // Attempt to add product to the database.
                        String response = success ? "Product processed successfully." : "Failed to process product."; // Prepare response based on the operation's success
                        out.writeObject(response); // Send the response back to the client to be displayed by the GUI. 
                    } catch (SQLException e) {
                        out.writeObject("Database error: " + e.getMessage()); // Send an error message if there is a database issue. 
                        System.err.println("Database access error: " + e.getMessage()); // Display a message in the console that there is a database access error. 
                        e.printStackTrace();
                    }
                    break;

                    /**
                    * View the products using the DatabaseAccess instance.
                    */
                    case "VIEW_PRODUCTS": // Case to handle viewing all products.
                        try (DatabaseAccess dbAccess = new DatabaseAccess()) {
                        List<Product> products = dbAccess.getAllProducts(); // Retrieve all products from the database.
                        out.writeObject(products); // Send the list of products back to the client.
                    } catch (SQLException e) {
                        out.writeObject("Database error: " + e.getMessage()); // Send an error message if there is a database issue.
                        System.err.println("Database access error: " + e.getMessage()); // Display a message in the console that there is a database access error.
                        e.printStackTrace();
                    }
                    break;

                    /**
                    * Add the schedule using the DatabaseAccess instance.
                    */
                    case "ADD_SCHEDULE":
                    DeliverySchedule schedule = (DeliverySchedule) in.readObject();
                    try (DatabaseAccess dbAccess = new DatabaseAccess()) {
                        boolean success = dbAccess.addSchedule(schedule);
                        String response = success ? "Schedule processed successfully." : "Failed to process schedule.";
                        out.writeObject(response);
                    } catch (SQLException e) {
                        out.writeObject("Database error: " + e.getMessage());
                        e.printStackTrace();
                    }
                    break;

                    /**
                    * View the schedule using the DatabaseAccess instance.
                    */
                    case "VIEW_SCHEDULES":
                    try (DatabaseAccess dbAccess = new DatabaseAccess()) {
                        List<DeliverySchedule> schedules = dbAccess.getAllSchedules();
                        out.writeObject(schedules);
                    } catch (SQLException e) {
                        out.writeObject("Database error: " + e.getMessage());
                        e.printStackTrace();
                    }
                    break;

                /**
                Non-functioning, future functionality code below. Uncomment as needed. 

                case "LOGIN":// Case to handle login.
                String[] credentials = (String[]) in.readObject(); // Read credentials from the client.
                System.out.println("Received login request for: " + credentials[0]); // Log the received login request.
                boolean loginSuccess = authenticateUser(credentials[0], credentials[1]); // Attempt to authenticate the user.
                String loginResponse = loginSuccess ? "Login successful." : "Login failed."; // Prepare response based on the operation's success.
                out.writeObject(loginResponse); // Send the response back to the client to be displayed by the GUI.
                break;
                    
                 // add the schedule data using the DatabaseAccess instance.
                case "ADD_SCHEDULE": // Case to handle adding a new delivery schedule.
                    DeliverySchedule deliverySchedule = (DeliverySchedule) in.readObject(); // Read delivery object from the client.
                    System.out.println("Received delivery data: " + deliverySchedule); // Log the received delivery data.

                    try (DatabaseAccess dbAccess = new DatabaseAccess()) {
                        boolean success = dbAccess.addAdminSchedule(deliverySchedule); // Attempt to add delivery schedule to the database.
                        String response = success ? "Delivery Schedule processed successfully." : "Failed to process delivery schedule."; // Prepare response based on the operation's success
                        out.writeObject(response); // Send the response back to the client to be displayed by the GUI.
                    } catch (SQLException e) {
                        out.writeObject("Database error: " + e.getMessage()); // Send an error message if there is a database issue.
                        System.err.println("Database access error: " + e.getMessage()); // Display a message in the console that there is a database access error.
                        e.printStackTrace();
                    }
                    break;


                 // update the schedule data using the DatabaseAccess instance.
                case "UPDATE_SCHEDULE": // Case to handle updating a new delivery schedule.
                    DeliverySchedule deliverySchedule = (DeliverySchedule) in.readObject(); // Read product object from the client.
                    System.out.println("Received delivery data: " + deliverySchedule); // Log the received delivery data.

                    try (DatabaseAccess dbAccess = new DatabaseAccess()) {
                        boolean success = dbAccess.updateAdminSchedule(deliverySchedule); // Attempt to update delivery schedule to the database.
                        String response = success ? "Delivery Schedule processed successfully." : "Failed to process delivery schedule."; // Prepare response based on the operation's success
                        out.writeObject(response); // Send the response back to the client to be displayed by the GUI.
                    } catch (SQLException e) {
                        out.writeObject("Database error: " + e.getMessage()); // Send an error message if there is a database issue.
                        System.err.println("Database access error: " + e.getMessage()); // Display a message in the console that there is a database access error.
                        e.printStackTrace();
                    }
                    break;


                 // View the deliveries using the DatabaseAccess instance by postcode.
                case "VIEW_DELIVERYBYPOSTCODE": // Case to handle viewing all deliveries.
                    try (DatabaseAccess dbAccess = new DatabaseAccess()) {
                        List<DeliverySchedule> deliverySchedules = dbAccess.getDetailsByPostcode(); // Retrieve all deliveries from the database.
                        out.writeObject(deliverySchedules); // Send the list of deliveries back to the client.
                    } catch (SQLException e) {
                        out.writeObject("Database error: " + e.getMessage()); // Send an error message if there is a database issue.
                        System.err.println("Database access error: " + e.getMessage()); // Display a message in the console that there is a database access error.
                        e.printStackTrace();
                    }
                    break;


                 // View the deliveries using the DatabaseAccess instance by day.
                case "VIEW_DELIVERYBYDAY": // Case to handle viewing all deliveries.
                    try (DatabaseAccess dbAccess = new DatabaseAccess()) {
                        List<DeliverySchedule> deliverySchedules = dbAccess.getDetailsByDay(); // Retrieve all deliveries from the database.
                        out.writeObject(deliverySchedules); // Send the list of deliveries back to the client.
                    } catch (SQLException e) {
                        out.writeObject("Database error: " + e.getMessage()); // Send an error message if there is a database issue.
                        System.err.println("Database access error: " + e.getMessage()); // Display a message in the console that there is a database access error.
                        e.printStackTrace();
                    }
                    break;

                    
                 // add the order data using the DatabaseAccess instance.
                case "ADD_ORDER": // Case to handle adding a new order.
                    Order orders = (Order) in.readObject(); // Read order object from the client.
                    System.out.println("Received order data: " + orders); // Log the received order data.

                    try (DatabaseAccess dbAccess = new DatabaseAccess()) {
                        boolean success = dbAccess.addOrder(orders); // Attempt to add order schedule to the database.
                        String response = success ? "Order processed successfully." : "Failed to process order schedule."; // Prepare response based on the operation's success
                        out.writeObject(response); // Send the response back to the client to be displayed by the GUI.
                    } catch (SQLException e) {
                        out.writeObject("Database error: " + e.getMessage()); // Send an error message if there is a database issue.
                        System.err.println("Database access error: " + e.getMessage()); // Display a message in the console that there is a database access error.
                        e.printStackTrace();
                    }
                    break;

                    */

                // Add more switch-case here for different functions of the server application (view users, etc).
                        
            }
            out.flush(); // Flush the output stream to ensure all data is sent

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error handling client data: " + e.getMessage()); // Display any errors during communication.
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close(); // Close the client socket to free resources.
            } catch (IOException e) {
                System.err.println("Error closing client socket: " + e.getMessage()); // Display an error if closing the socket fails.
            }
        }
    }
}
