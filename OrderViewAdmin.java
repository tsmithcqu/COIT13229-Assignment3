import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class OrderViewAdmin extends JFrame {

    public OrderViewAdmin() {
        setTitle("View Orders");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JTable orderTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(orderTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        loadOrderData(orderTable);

        add(panel);
    }

    private void loadOrderData(JTable orderTable) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mhds", "root", "password");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM orders");

            String[] columnNames = {"Order ID", "Customer Email", "Product ID", "Quantity", "Total Price"};
            Object[][] data = new Object[50][5];
            int rowCount = 0;

            while (resultSet.next()) {
                data[rowCount][0] = resultSet.getInt("order_id");
                data[rowCount][1] = resultSet.getString("customer_email");
                data[rowCount][2] = resultSet.getString("product_id");
                data[rowCount][3] = resultSet.getInt("quantity");
                data[rowCount][4] = resultSet.getDouble("total_price");
                rowCount++;
            }

            orderTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            OrderViewAdmin orderViewAdmin = new OrderViewAdmin();
            orderViewAdmin.setVisible(true);
        });
    }
}
