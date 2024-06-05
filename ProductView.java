package mhds;

import javax.swing.*;
import java.awt.*;

/**
 * A GUI class extending JFrame for viewing registered products.
 */
public class ProductView extends JFrame {
    private JButton viewButton;
    private Client client;

    /**
     * Constructor initialises the GUI.
     */
    public ProductView() {
        super("View Products"); // Set the title of the JFrame window
        this.client = new Client(); // Create a new instance of Client for communication
        initializeComponents(); // Call the method to set up the GUI components
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation to exit the application
        setSize(400, 400); // Set the initial size of the frame
        setLocationRelativeTo(null); // Center the frame on the screen
    }

    /**
     * Method to set up the form with a button to view products.
     */
    private void initializeComponents() {
        setLayout(new FlowLayout());  // Use FlowLayout for simple arrangement of components

        // Button to view the registered products
        viewButton = new JButton("View Products");
        viewButton.addActionListener(e -> viewProducts()); // Set action listener to handle button click
        add(viewButton);
    }

    /**
     * Method to handle the button click event for viewing registered products.
     */
    private void viewProducts() {
        // Fetch all products using the client
        java.util.List<Product> products = client.fetchAllProducts();
        if (products != null) {
            // Display the fetched products in a message dialog
            StringBuilder sb = new StringBuilder("Registered Products:\n");
            for (Product product : products) {
                sb.append("Name: ").append(product.getName())
                  .append(", Price: ").append(product.getPrice())
                  .append(", Ingredients: ").append(product.getIngredients())
                  .append(", Unit: ").append(product.getUnit())
                  .append(", Quantity: ").append(product.getQuantity())
                  .append("\n");
            }
            JOptionPane.showMessageDialog(this, sb.toString(), "Product List", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Failed to fetch products", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Main method to run the GUI.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ProductView frame = new ProductView(); // Create an instance of the GUI frame
            frame.setVisible(true); // Make the frame visible
        });
    }
}
