package mhds;

import javax.swing.*;
import java.awt.*;

/**
 * Admin dashboard providing access to customer and product management functionalities.
 */
public class AdminDashboard extends JFrame {
    // Declare buttons for different functionalities.
    private JButton customerViewButton, productViewButton, manageSchedulesButton;

    public AdminDashboard() {
        super("Admin Dashboard"); // Set the title of the JFrame.
        initializeComponents(); // Call method to initialize GUI components.
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Set the default close operation to dispose the frame on close.
        setSize(300, 200); // Set the size of the frame.
        setLocationRelativeTo(null); // Center the frame on the screen.
    }

    /**
     * Method to initialize the GUI components.
     */
    private void initializeComponents() {
        setLayout(new FlowLayout()); // Set the layout manager to FlowLayout.

        // Initialise and add customer view button.
        customerViewButton = new JButton("Customer Management");
        customerViewButton.addActionListener(e -> openCustomerViewAdmin()); // Add action listener to open customer management screen.
        add(customerViewButton); // Add button to the frame.

        productViewButton = new JButton("Product Management");
        productViewButton.addActionListener(e -> openProductViewAdmin()); // Add action listener to open product management screen.
        add(productViewButton); // Add button to the frame.

        // Initialise and add manage schedules button.
        manageSchedulesButton = new JButton("Manage Delivery Schedules");
        manageSchedulesButton.addActionListener(e -> openDeliveryScheduleAdmin()); // Add action listener to open delivery schedule management screen.
        add(manageSchedulesButton); // Add button to the frame.
    }

    /**
     * Method to open the customer management screen
     */
    private void openCustomerViewAdmin() {
        CustomerViewAdmin customerViewAdmin = new CustomerViewAdmin();
        customerViewAdmin.setVisible(true);
    }

    /**
     * Method to open the product management screen
     */
    private void openProductViewAdmin() {
        ProductViewAdmin productViewAdmin = new ProductViewAdmin();
        productViewAdmin.setVisible(true);
    }

     /**
     * Method to open the delivery schedule view admin screen
     */
    private void openDeliveryScheduleViewAdmin() {
        DeliveryScheduleAdmin deliveryScheduleAdmin = new DeliveryScheduleAdmin(); // Create an instance of DeliveryScheduleAdmin
        deliveryScheduleAdmin.setVisible(true); // Make the delivery schedule admin screen visible
    }

     /**
     * Main method to run the GUI
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> { // Ensure the GUI creation happens on the Event Dispatch Thread
            AdminDashboard frame = new AdminDashboard(); // Create an instance of AdminDashboard
            frame.setVisible(true); // Make the admin dashboard visible
        });
    }
}
