package mhds; 

import java.sql.Connection; 
import java.sql.SQLException; 

public class CustomerService {
    // Declares and initialises constants for the database connection details.
    private final String url = "jdbc:mysql://localhost:3306/mhds"; // URL of the MySQL database.
    private final String user = "username"; // Username for database login.
    private final String password = "password"; // Password for database login.

// Method to register a new customer. 
    public String registerCustomer(Customer customer) {
        // SQL statement for inserting new customer data into the database.
        String sql = "INSERT INTO customers (name, email, password, address, phoneNumber) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password); // Attempts to establish a database connection.
             PreparedStatement ps = conn.prepareStatement(sql))  // Creates a PreparedStatement for executing SQL commands.
          
// TO DO: NEED TO DO PREPARED STATEMENTS

//TO DO: IF ELSE FOR ENSURING CUSTOMER HAS BEEN REGISTERED
      
// NEED TO DO CATCH
  
}
}
