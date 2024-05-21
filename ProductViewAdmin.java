import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ProductViewAdmin extends JFrame {

    private JTextField txtName;
    private JTextField txtUnit;
    private JTextField txtQuantity;
    private JTextField txtPrice;
    private JTextField txtIngredients;
    private JTable productTable;

    public ProductViewAdmin() {
        setTitle("Product Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        txtName = new JTextField();
        txtUnit = new JTextField();
        txtQuantity = new JTextField();
        txtPrice = new JTextField();
        txtIngredients = new JTextField();

        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(txtName);
        inputPanel.add(new JLabel("Unit:"));
        inputPanel.add(txtUnit);
        inputPanel.add(new JLabel("Quantity:"));
        inputPanel.add(txtQuantity);
        inputPanel.add(new JLabel("Price:"));
        inputPanel.add(txtPrice);
        inputPanel.add(new JLabel("Ingredients:"));
        inputPanel.add(txtIngredients);

        JButton btnAdd = new JButton("Add Product");
        JButton btnUpdate = new JButton("Update Product");
        JButton btnDelete = new JButton("Delete Product");

        btnAdd.addActionListener(e -> addProduct());
        btnUpdate.addActionListener(e -> updateProduct());
        btnDelete.addActionListener(e -> deleteProduct());

        inputPanel.add(btnAdd);
        inputPanel.add(btnUpdate);
        inputPanel.add(btnDelete);

        panel.add(inputPanel, BorderLayout.NORTH);

        productTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(productTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        loadProductData(productTable);

        add(panel);
    }

    private void loadProductData(JTable productTable) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mhds", "root", "password");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, name, unit, quantity, price, ingredients FROM products");

            String[] columnNames = {"ID", "Name", "Unit", "Quantity", "Price", "Ingredients"};
            Object[][] data = new Object[50][6];
            int rowCount = 0;

            while (resultSet.next()) {
                data[rowCount][0] = resultSet.getInt("id");
                data[rowCount][1] = resultSet.getString("name");
                data[rowCount][2] = resultSet.getString("unit");
                data[rowCount][3] = resultSet.getInt("quantity");
                data[rowCount][4] = resultSet.getDouble("price");
                data[rowCount][5] = resultSet.getString("ingredients");
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

    private void addProduct() {
        String name = txtName.getText();
        String unit = txtUnit.getText();
        int quantity = Integer.parseInt(txtQuantity.getText());
        double price = Double.parseDouble(txtPrice.getText());
        String ingredients = txtIngredients.getText();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mhds", "root", "password");
            PreparedStatement ps = connection.prepareStatement("INSERT INTO products (name, unit, quantity, price, ingredients) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, name);
            ps.setString(2, unit);
            ps.setInt(3, quantity);
            ps.setDouble(4, price);
            ps.setString(5, ingredients);
            ps.executeUpdate();
            ps.close();
            connection.close();

            loadProductData(productTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateProduct() {
        int selectedRow = productTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Select a product to update.");
            return;
        }

        int productId = (int) productTable.getValueAt(selectedRow, 0);
        String name = txtName.getText();
        String unit = txtUnit.getText();
        int quantity = Integer.parseInt(txtQuantity.getText());
        double price = Double.parseDouble(txtPrice.getText());
        String ingredients = txtIngredients.getText();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mhds", "root", "password");
            PreparedStatement ps = connection.prepareStatement("UPDATE products SET name = ?, unit = ?, quantity = ?, price = ?, ingredients = ? WHERE id = ?");
            ps.setString(1, name);
            ps.setString(2, unit);
            ps.setInt(3, quantity);
            ps.setDouble(4, price);
            ps.setString(5, ingredients);
            ps.setInt(6, productId);
            ps.executeUpdate();
            ps.close();
            connection.close();

            loadProductData(productTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteProduct() {
        int selectedRow = productTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Select a product to delete.");
            return;
        }

        int productId = (int) productTable.getValueAt(selectedRow, 0);

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mhds", "root", "password");
            PreparedStatement ps = connection.prepareStatement("DELETE FROM products WHERE id = ?");
            ps.setInt(1, productId);
            ps.executeUpdate();
            ps.close();
            connection.close();

            loadProductData(productTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ProductViewAdmin productViewAdmin = new ProductViewAdmin();
            productViewAdmin.setVisible(true);
        });
    }
}
