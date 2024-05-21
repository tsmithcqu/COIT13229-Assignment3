import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CustomerViewAdmin extends JFrame {

    public CustomerViewAdmin() {
        setTitle("View Customers");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JTable customerTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(customerTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        loadCustomerData(customerTable);

        add(panel);
    }

    private void loadCustomerData(JTable customerTable) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mhds", "root", "password");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM customers");

            String[] columnNames = {"Full Name", "Phone Number", "Email", "Password", "Address"};
            Object[][] data = new Object[50][5];
            int rowCount = 0;

            while (resultSet.next()) {
                data[rowCount][0] = resultSet.getString("fullName");
                data[rowCount][1] = resultSet.getString("phoneNumber");
                data[rowCount][2] = resultSet.getString("email");
                data[rowCount][3] = resultSet.getString("password");
                data[rowCount][4] = resultSet.getString("address");
                rowCount++;
            }

            customerTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CustomerViewAdmin customerViewAdmin = new CustomerViewAdmin();
            customerViewAdmin.setVisible(true);
        });
    }
}
