package mhds;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * A GUI class extending JFrame for creating and handling the product management form.
 */
public class ProductViewAdmin extends JFrame {

     /**
     * Declare components to be used in the form.
     */
    private JTextField nameField, priceField, ingredientsField, unitField, quantityField;
    private JButton addButton, viewButton,orderButton;
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
    }
        /**
     * Method to set up the form with labels, text fields, and buttons.
     */
     private void initializeComponents() {
        setLayout(new GridLayout(8, 2));  // Set the layout of the JFrame to a grid layout with 6 rows and 2 columns.

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

         // Add button to place an order.
         orderButton = new JButton("Place Order");
         orderButton.addActionListener(e -> openOrderScreen()); // Set action listener to open order screen
         add(orderButton);
    }

      /**
     * Method to handle the button click event for submitting product data.
     */
    private void submitProductData() {
        /**
         * Retrieve text from each text field.
         */
        String name = nameField.getText();
        double price = Double.parseDouble(priceField.getText());
        String ingredients = ingredientsField.getText();
        String unit = unitField.getText();
        int quantity = Integer.parseInt(quantityField.getText());

        /**
         * Create a new Product object with the data from the form.
         */
        Product product = new Product(name, price, ingredients, unit, quantity);

        /**
         * Use the client to send the product data for registration.
         */
        client.sendProductData(product);
    }

    /**
     * Method to handle the button click event for viewing registered products.
     */
    private void viewProducts() {
        // Implement view products functionality
        List<Product> productList = client.fetchAllProducts();
        if (productList != null) {
            StringBuilder sb = new StringBuilder();
            for (Product product : productList) {
                sb.append(product).append("\n");
            }
            JOptionPane.showMessageDialog(this, sb.toString(), "Registered Products", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Failed to fetch products", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Method to open the order screen.
     */
    private void openOrderScreen() {
        // Assuming you have a Customer object to pass to the mhds.OrderScreen
        Customer customer = new Customer("Test Customer", "test@mail.com", "test1234", "123 Queen Street", "12345689");
        SwingUtilities.invokeLater(() -> {
            OrderScreen orderScreen = new OrderScreen(client, customer);
            orderScreen.setVisible(true);
        });
    }

     /**
     * Main method to run the GUI.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ProductViewAdmin frame = new ProductViewAdmin();
            frame.setVisible(true);
        });
    }
}
