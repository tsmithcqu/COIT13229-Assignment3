package mhds;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


/**
 * Class to handle database operations, implements AutoCloseable to ensure resources are freed properly.
 * Gen AI provided guidance on implementing AutoCloseable, as system resources were not being freed previously. 
 */
public class DatabaseAccess implements AutoCloseable {
    private Connection connection; // Connection object to manage the database connection.
    private static final String URL = "jdbc:mysql://localhost:3306/mhds"; // Database URL. 
    private static final String USERNAME = "root"; // Database username. 
    private static final String PASSWORD = "Blu3b3rry"; // Database password (Tyson's database). 

    /**
     * Constructor to initialise and open a database connection.
     */
    public DatabaseAccess() throws SQLException {
        this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); // Attempt to establish a database connection using provided credentials.
    }

     /**
     * New methods will need to be created for different functions of the application. 
     * Gen AI suggested using Boolean for database interactions, to provide a simpler way of identifying if the database action was performed. 
     */

     /**
     * Method to add a new customer to the database.
     */
    public boolean addCustomer(Customer customer) {
        String sql = "INSERT INTO Users (Username, Password, Email, Address, PhoneNumber) VALUES (?, ?, ?, ?, ?)"; // SQL statement to insert a new user into the Users table.
        try (PreparedStatement ps = connection.prepareStatement(sql)) { 
            /*
             * Set the values for the PreparedStatement from the customer object.
             */
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getPassword());
            ps.setString(3, customer.getEmail());
            ps.setString(4, customer.getAddress());
            ps.setString(5, customer.getPhoneNumber());

            /*
             * Execute the update and return true if the update affected at least one row.
             */
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Print stack trace if an SQLException occurs.
            return false; // Return false if there is an SQL error.
        }
    }

    /**
     * Method to retrieve all customers from the database.
     */
    public List<Customer> getAllCustomers() throws SQLException {
        String sql = "SELECT * FROM Users"; // SQL query to select all users from the Users table.
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

             List<Customer> customers = new ArrayList<>(); // Create a list to store the retrieved customers.
             while (rs.next()) { // Iterate through the result set.
                /*
                 * Create a new Customer object for each row in the result set and add it to the list.
                 */
                customers.add(new Customer(
                        rs.getString("Username"),
                        rs.getString("Email"),
                        rs.getString("Password"),
                        rs.getString("Address"),
                        rs.getString("PhoneNumber")
                ));
            }
            return customers; // Return the list of customers.
        }
    }

    //ADMIN FUNCTIONS
    //ADMIN UPDATED

    /**
     * Method to add a new product to the database.
     */
    public boolean addProduct(Product product) {
        String sql = "INSERT INTO Products (ProductName, Price, Ingredients, Unit, Quantity) VALUES (?, ?, ?, ?, ?)"; // SQL statement to insert a new product into the Products table.
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            /**
             * Set the values for the PreparedStatement from the product object.
             */
            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setString(3, product.getIngredients());
            ps.setString(4, product.getUnit());
            ps.setInt(5, product.getQuantity());

            /**
             * Execute the update and return true if the update affected at least one row.
             */
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Print stack trace if an SQLException occurs.
            return false; // Return false if there is an SQL error.
        }
    }
    
    // Add a schedule (admin function).
    public boolean addAdminSchedule(mdhs.DeliverySchedule DeliverySchedule) {
        String sql = "INSERT INTO delivery_schedules (postcode, deliveryDay, deliveryCost) VALUES (?, ?, ?)"; // SQL statement to insert a new user into the Users table.
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            /*
             * Set the values for the PreparedStatement from the DeliverySchedule object.
             */
            ps.setString(1, DeliverySchedule.getPostcode());
            ps.setString(2, DeliverySchedule.getDeliveryDay());
            ps.setDouble(3, DeliverySchedule.getDeliveryCost());

            /*
             * Execute the update and return true if the update affected at least one row.
             */
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Print stack trace if an SQLException occurs.
            return false; // Return false if there is an SQL error.
        }
    }


    // Update a schedule (admin function).
    public boolean updateAdminSchedule(mdhs.DeliverySchedule DeliverySchedule) {
        String sql = "UPDATE delivery_schedules SET postcode = ?, delivery_cost = ? WHERE delivery_day = ? "; // SQL statement to insert a new user into the Users table.
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            /*
             * Set the values for the PreparedStatement from the DeliverySchedule object.
             */
            ps.setString(1, DeliverySchedule.getPostcode());
            ps.setString(2, DeliverySchedule.getDeliveryDay());
            ps.setDouble(3, DeliverySchedule.getDeliveryCost());

            /*
             * Execute the update and return true if the update affected at least one row.
             */
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Print stack trace if an SQLException occurs.
            return false; // Return false if there is an SQL error.
        }
    }


    /**
     * Method to retrieve all products from the database.
     */
    public List<Product> getAllProducts() throws SQLException {
        String sql = "SELECT * FROM Products"; // SQL query to select all products by name from the Products table.
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            List<Product> products = new ArrayList<>(); // Create a list to store the retrieved products.
            while (rs.next()) { // Iterate through the result set.
                /*
                 * Create a new Product object for each row in the result set and add it to the list.
                 */
                products.add(new Product(
                        rs.getString("ProductID"),
                        rs.getString("ProductName"),
                        rs.getDouble("Price"),
                        rs.getString("Ingredients"),
                        rs.getString("Unit"),
                        rs.getInt("Quantity")
                ));
            }
            return products; // Return the list of products.
        }
    }


    //METHODS TO GET DELIVERIES BY POSTCODE
    public List<mdhs.DeliverySchedule> getDetailsByPostcode() throws SQLException {
        String sql = "SELECT * FROM delivery_schedules where postcode =?"; // SQL query to select all delivery schedules by name from the delivery_schedules table.
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            List<mdhs.Product> postcodes = new ArrayList<>(); // Create a list to store the retrieved postcodes.
            while (rs.next()) { // Iterate through the result set.
                /*
                 * Create a new delivery schedules object for each row in the result set and add it to the list.
                 */
                postcodes.add(new mdhs.Product(
                        rs.getString("postcode"),
                        rs.ggetString("deliveryDay"),
                        rs.getDouble("deliveryCost")
                ));
            }
            return postcodes; // Return the list of delivery schedules by postcode.
        }
    }

    //METHODS TO GET DETAILS BY DAY
    public List<mdhs.DeliverySchedule> getDetailsByDay() throws SQLException {
        String sql = "SELECT * FROM delivery_schedules where delivery_day =?"; // SQL query to select all delivery schedules by name from the delivery_schedules table.
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            List<mdhs.Product> postcodes = new ArrayList<>(); // Create a list to store the retrieved postcodes.
            while (rs.next()) { // Iterate through the result set.
                /*
                 * Create a new delivery schedules object for each row in the result set and add it to the list.
                 */
                postcodes.add(new mdhs.Product(
                        rs.getString("postcode"),
                        rs.ggetString("deliveryDay"),
                        rs.getDouble("deliveryCost")
                ));
            }
            return postcodes; // Return the list of delivery schedules by postcode.
        }
    }

    //Method to Add Orders
    public boolean addOrder(Order order) {
        String sql = "INSERT INTO orders" + "(product_id, quantity)" + "VALUES (?, ?)"; // SQL statement to insert a new order into the Orders table.
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            /*
             * Set the values for the PreparedStatement from the order object.
             */
            ps.setString(1, order.getProductID());
            ps.setString(2, order.getQuantity());

            /*
             * Execute the update and return true if the update affected at least one row.
             */
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Print stack trace if an SQLException occurs.
            return false; // Return false if there is an SQL error.
        }
    }



    /**



     // Tyson to do: Build out the ability for admins to add products to the database, and list products from the database.

    
    // Add more database unteractions here. Such as viewing etc. 

    /**
     * Method to close the database connection.
     */
    @Override
    public void close() throws SQLException {
        if (this.connection != null && !this.connection.isClosed()) { // Check if the connection is still open.
            this.connection.close(); // Close the connection to free resources.
        }
    }
}


/**
OLD CODE BELOW. Leaving it here to grab things from if needed. 
The below needs to be completely overhauled. Currently not functioning.
    */

    // TODO add Delete a schedule (admin function).
