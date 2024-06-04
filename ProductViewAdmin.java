package mhds;

import javax.swing.*;
import java.awt.*;

/**
 * A GUI class extending JFrame for creating and handling the product management form.
 */
public class ProductViewAdmin extends JFrame {

     /**
     * Declare components to be used in the form.
     */
    private JTextField nameField, priceField, ingredientsField, unitField, quantityField;
    private JButton addButton, viewButton;
    private Client client;

     /**
     * Constructor initialises the GUI.
     */
    public ProductViewAdmin() {
        super("Manage Products for MHDS"); // Set the title of the JFrame window.
        this.client = new Client(); // Create a new instance of Client for communication.
        initializeComponents(); // Call the method to create the form components.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation to exit the application.
        setSize(400, 400); // Set the initial size of the frame.
        setLocationRelativeTo(null); // Center the frame on the screen.

        /**
     * Method to set up the form with labels, text fields, and buttons.
     */
     private void initializeComponents() {
        setLayout(new GridLayout(6, 2));  // Set the layout of the JFrame to a grid layout with 6 rows and 2 columns.

        /**
         * Add product name label and text field to the form.
         */
        add(new JLabel("Product Name:")); // Label for product name.
        nameField = new JTextField(20); // Text field for entering product name. 
        add(nameField);

        /**
         * Add product price label and text field to the form.
         */
        add(new JLabel("Price:")); // Label for product price.
        priceField = new JTextField(20); // Text field for entering product price. 
        add(priceField);

        /**
         * Add product ingredients label and text field to the form.
         */
        add(new JLabel("Ingredients:")); // Label for product ingredients.
        ingredientsField = new JTextField(20); // Text field for entering product ingredients. 
        add(ingredientsField);

        /**
         * Add product unit label and text field to the form.
         */
        add(new JLabel("Unit:")); // Label for product unit.
        unitField = new JTextField(20); // Text field for entering product unit. 
        add(unitField);

        /**
         * Add product quantity label and text field to the form.
         */
        add(new JLabel("Quantity:")); // Label for product quantity.
        quantityField = new JTextField(20); // Text field for entering product quantity. 
        add(quantityField);

        /**
         * Add button to submit the form data.
         */
        addButton = new JButton("Add Product"); // Button to add product to the database.
        addButton.addActionListener(e -> submitProductData()); // Set action listener to handle button click
        add(addButton);

        /**
         * Add button to view the registered products.
         */
        viewButton = new JButton("View Products");
        viewButton.addActionListener(e -> viewProducts()); // Set action listener to handle button click
        add(viewButton);
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
