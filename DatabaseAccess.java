import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;

public class DatabaseAccess {
    private static Connection connection;
    String URL = "jdbc:mysql://localhost:3306/mhds";
    String USERNAME = "root";
    String PASSWORD = "password";
    private PreparedStatement insertNewCustomer = null;

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
        }//end of try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.exit(1);
        }

    }
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
