package mdhs;

import java.io.*;
import java.net.Socket;
import java.sql.*;

/**
 * DeliveryScheduleService class to manage delivery schedules.
 * Handles the management and storage of delivery details in the database.
 */
public class DeliveryScheduleService {

    private Socket clientSocket;
    private Connection connection;

    /**
     * Constructor establishes a connection to the server and the database.
     */
    public DeliveryScheduleService() throws IOException, SQLException {
        this.clientSocket = new Socket("localhost", 6969);
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost/mdhs", "user", "password");
    }

    /**
     * Reads and manages delivery schedule details from the database.
     */
    public void manageDeliveryDetails() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM delivery_schedules");
            while (resultSet.next()) {
                System.out.println("Postcode: " + resultSet.getString("postcode") + ", Delivery Day: " + resultSet.getString("delivery_day") + ", Delivery Cost: " + resultSet.getDouble("delivery_cost"));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error managing delivery details: " + e.getMessage());
        }
    }
}
