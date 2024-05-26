import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.*;

import static java.sql.DriverManager.getConnection;

public class DatabaseAccess {
    private static Connection connection;
    String URL = "jdbc:mysql://localhost:3306/mhds";
    String USERNAME = "root";
    String PASSWORD = "password";

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

        }//end of try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.exit(1);
        }

    }

    //Adds customer to database
    public int addCustomer(String fullName, String phoneNumber, String email, String password, String address)
    {
        int result = 0;
        try {
            insertNewCustomer.setString(1, fullName);
            insertNewCustomer.setString(2, phoneNumber);
            insertNewCustomer.setString(3, email);
            insertNewCustomer.setString(4, password);
            insertNewCustomer.setString(5, address);

            result = insertNewCustomer.executeUpdate();
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return result;
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

    //ADD METHODS TO GET DETAILS BY POSTCODE
    //ADD METHODS TO GET DETAILS BY DAY
    //ADD METHODS TO GET PRODUCTS BY NAME

    public void close()
    {
        try {
            connection.close();
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

}
