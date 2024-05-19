package mdhs;

import java.io.*;
import java.net.Socket;
import java.sql.*;

/**
 * ProductService class to handle product data management.
 * Provides methods for interacting with the product data stored in the MySQL database.
 */
public class ProductService {

    private Socket clientSocket;
    private Connection connection;

    /**
     * Constructor establishes a connection to the server and the database.
     */
    public ProductService() throws IOException, SQLException {
        // Connect to the server at localhost on port 6969
        this.clientSocket = new Socket("localhost", 6969);
        // Establish a connection to the MySQL database
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost/mdhs", "user", "password");
    }

    /**
     * Retrieves product data from the database and sends some information to the server if needed.
     */
    public void retrieveAndProcessProducts() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM products");

            while (resultSet.next()) {
                String productName = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                // Assuming you might need to send some data to the server
                DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());
                output.writeUTF("Product: " + productName + ", Price: " + price);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error accessing product data: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error sending product data to server: " + e.getMessage());
        }
    }
}
