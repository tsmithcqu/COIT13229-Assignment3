package mdhs;

import java.io.*;
import java.net.Socket;
import java.sql.*;

/**
 * OrderService class to handle order data management.
 * Manages the creation and storage of client orders in the database.
 */
public class OrderService {

    private Socket clientSocket;
    private Connection connection;

    /**
     * Constructor establishes a connection to the server and the database.
     */
    public OrderService() throws IOException, SQLException {
        this.clientSocket = new Socket("localhost", 6969);
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost/mdhs", "user", "password");
    }

    /**
     * Processes and saves order data received from the client.
     */
    public void processAndSaveOrder(String productID, int quantity) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO orders (product_id, quantity) VALUES (?, ?)");
            ps.setString(1, productID);
            ps.setInt(2, quantity);
            ps.executeUpdate();
            ps.close();

            System.out.println("Order processed and saved: Product ID: " + productID + " Quantity: " + quantity);
        } catch (SQLException e) {
            System.out.println("Error processing order data: " + e.getMessage());
        }
    }
}
