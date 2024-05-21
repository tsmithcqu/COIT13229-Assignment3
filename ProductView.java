import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ProductView extends JFrame {

    public ProductView() {
        setTitle("Product View");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JTable productTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(productTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        loadProductData(productTable);

        add(panel);
    }

    private void loadProductData(JTable productTable) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mhds", "root", "password");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT name, unit, quantity, price, ingredients FROM products");

            String[] columnNames = {"Name", "Unit", "Quantity", "Price", "Ingredients"};
            Object[][] data = new Object[50][5];
            int rowCount = 0;

            while (resultSet.next()) {
                data[rowCount][0] = resultSet.getString("name");
                data[rowCount][1] = resultSet.getString("unit");
                data[rowCount][2] = resultSet.getInt("quantity");
                data[rowCount][3] = resultSet.getDouble("price");
                data[rowCount][4] = resultSet.getString("ingredients");
                rowCount++;
            }

            productTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ProductView productView = new ProductView();
            productView.setVisible(true);
        });
    }
}
