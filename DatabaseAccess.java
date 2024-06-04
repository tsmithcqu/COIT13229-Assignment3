package mhds;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;



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
     * Method to add a new customer to the database.
     * New methods will need to be created for different functions of the application. 
     * Gen AI suggested using Boolean for database interactions, to provide a simpler way of identifying if the database action was performed. 
     */
    public boolean addCustomer(Customer customer) {
        String sql = "INSERT INTO Users (Username, Password, Email, Address, PhoneNumber) VALUES (?, ?, ?, ?, ?)"; // SQL statement to insert a new user into the Users table.
        try (PreparedStatement ps = connection.prepareStatement(sql)) { 
            /**
             * Set the values for the PreparedStatement from the customer object.
             */
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getPassword());
            ps.setString(3, customer.getEmail());
            ps.setString(4, customer.getAddress());
            ps.setString(5, customer.getPhoneNumber());

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

    /**
     * Method to retrieve all customers from the database.
     */
    public List<Customer> getAllCustomers() throws SQLException {
        String sql = "SELECT * FROM Users"; // SQL query to select all users from the Users table.
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

             List<Customer> customers = new ArrayList<>(); // Create a list to store the retrieved customers.
             while (rs.next()) { // Iterate through the result set.
                /**
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
    // Add a schedule (admin function).
    public boolean addAdminSchedule(mdhs.DeliverySchedule DeliverySchedule) {
        String sql = "INSERT INTO delivery_schedules (postcode, deliveryDay, deliveryCost) VALUES (?, ?, ?)"; // SQL statement to insert a new user into the Users table.
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            /**
             * Set the values for the PreparedStatement from the DeliverySchedule object.
             */
            ps.setString(1, DeliverySchedule.getPostcode());
            ps.setString(2, DeliverySchedule.getDeliveryDay());
            ps.setDouble(3, DeliverySchedule.getDeliveryCost());

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


    // Update a schedule (admin function).
    public boolean updateAdminSchedule(mdhs.DeliverySchedule DeliverySchedule) {
        String sql = "UPDATE delivery_schedules SET postcode = ?, delivery_cost = ? WHERE delivery_day = ? "; // SQL statement to insert a new user into the Users table.
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            /**
             * Set the values for the PreparedStatement from the DeliverySchedule object.
             */
            ps.setString(1, DeliverySchedule.getPostcode());
            ps.setString(2, DeliverySchedule.getDeliveryDay());
            ps.setDouble(3, DeliverySchedule.getDeliveryCost());

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

import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;


import static java.sql.DriverManager.getConnection;
    
    //Customer Statements
    private PreparedStatement insertNewCustomer = null;

    //Delivery Statements
    private PreparedStatement selectDetailsByPostcode = null;
    private PreparedStatement selectDetailsByDay = null;

    //Order Statements
    private PreparedStatement insertNewOrder = null;

    //Product Statements
    private PreparedStatement selectProductByName = null;


    //Prepares database statements
    public DatabaseAccess() {
        // Save customer to the database
        try {
            //establish connection
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            //create insert to add new customer to database
            insertNewCustomer = connection.prepareStatement(
                    "INSERT INTO customer"
                            + "(fullName, phoneNumber, email, password, address)"
                            + "VALUES(?,?,?,?,?)");


            //select delivery details by postcode, day and cost
            selectDetailsByPostcode = connection.prepareStatement(
                    "SELECT * FROM delivery_schedules where postcode =?"
            );

            selectDetailsByDay = connection.prepareStatement(
                    "SELECT * FROM delivery_schedules where delivery_day =?"
            );


            //create new order
            insertNewOrder = connection.prepareStatement(
                    "INSERT INTO orders"
                            + "(product_id, quantity)"
                            + "VALUES (?, ?)");


            //create new product
            selectProductByName = connection.prepareStatement(
                    "SELECT * FROM products where name =?");

            //ADMIN Schedule
            insertNewSchedule = connection.prepareStatement(
                    "INSERT INTO delivery_schedules"
                            + "(postcode, deliveryDay, deliveryCost)"
                            + "VALUES (?, ?, ?)");

            updateExistingSchedule = connection.prepareStatement(
                    "UPDATE delivery_schedules SET postcode = ?, delivery_cost = ? WHERE delivery_day = ? "
            );


        }//end of try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.exit(1);
        }

    }



    //Inserts order to database
    public int processAndSaveOrder(String productID, int quantity)
    {
        int result = 0;
        try {
            insertNewOrder.setString(1, productID);
            insertNewOrder.setInt(2, quantity);

            result = insertNewOrder.executeUpdate();

        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return result;
    }

    //METHODS TO GET DETAILS BY POSTCODE
    public List<mdhs.DeliverySchedule> getDetailsByPostcode(String postcode){
        List<mdhs.DeliverySchedule> postcodeList = null;
        ResultSet resultSet = null;

        try{
            selectDetailsByPostcode.setString(1, postcode);
            resultSet = selectDetailsByPostcode.executeQuery();
            postcodeList = new ArrayList<mdhs.DeliverySchedule>();
            while (resultSet.next()){
                postcodeList.add(new mdhs.DeliverySchedule(
                        resultSet.getString("postcode"),
                        resultSet.getString("deliveryDay"),
                        resultSet.getDouble("deliveryCost")));
            }//end - while
        }//end - try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }// end - catch
        finally {
            try {
                resultSet.close();
            }//end - try
            catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }//end - finally
        return postcodeList;
    }


    //ADD METHODS TO GET DETAILS BY DAY
    public List<mdhs.DeliverySchedule> getDetailsByDay(String deliveryDay){
        List<mdhs.DeliverySchedule> deliveryDayList = null;
        ResultSet resultSet = null;

        try{
            selectDetailsByDay.setString(1, deliveryDay);
            resultSet = selectDetailsByDay.executeQuery();
            deliveryDayList = new ArrayList<mdhs.DeliverySchedule>();
            while (resultSet.next()){
                deliveryDayList.add(new mdhs.DeliverySchedule(
                        resultSet.getString("postcode"),
                        resultSet.getString("deliveryDay"),
                        resultSet.getDouble("deliveryCost")));
            }//end - while
        }//end - try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }// end - catch
        finally {
            try {
                resultSet.close();
            }//end - try
            catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }//end - finally
        return deliveryDayList;
    }


    //ADD METHODS TO GET PRODUCTS BY NAME
    public List<mdhs.Product> getProductByName(String name){
        List<mdhs.Product> productNameList = null;
        ResultSet resultSet = null;

        try{
            selectProductByName.setString(1, name);
            resultSet = selectProductByName.executeQuery();
            productNameList = new ArrayList<mdhs.Product>();
            while (resultSet.next()){
                productNameList.add(new mdhs.Product(
                        resultSet.getString("name"),
                        resultSet.getString("unit"),
                        resultSet.getInt("quantity"),
                        resultSet.getDouble("price"),
                        resultSet.getString("ingredients")));
            }//end - while

        }//end - try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }// end - catch
        finally {
            try {
                resultSet.close();
            }//end - try
            catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }//end - finally
        return productNameList;
    }


    */

    // TODO add Delete a schedule (admin function).
