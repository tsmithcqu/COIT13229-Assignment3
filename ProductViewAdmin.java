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
    }
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
     * Method to handle the button click event for viewing products.
     */
    private void viewProducts() {
        // Implement view products functionality
        
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
