import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseAccess {
    private static Connection connection = MySQLDatabase.getConnection();

    public static synchronized void saveCustomer(Customer customer) {
        // Save customer to the database
    }

    // Implement other database interaction methods for products, orders after this..
}
