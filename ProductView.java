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


// to do - logic
