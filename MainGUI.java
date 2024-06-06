package mhds;

import javax.swing.*;
import java.awt.*;

/**
 * Main GUI for navigating between Admin and Customer Dashboards.
 */
public class MainGUI extends JFrame {
    // Declaration of buttons for admin and customer dashboards
    private JButton adminButton, customerButton;

    /**
     * Constructor that initialises the GUI components and sets the frame properties.
     */
    public MainGUI() {
        super("Welcome to MHDS"); // Title of the window.
        initializeComponents(); // Initialise buttons and set layout.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close operation.
        setSize(300, 200); // Window size.
        setLocationRelativeTo(null); // Center the window on the screen.
    }

    /**
     * Initialises the GUI components and their functionalities.
     * Sets the layout and adds action listeners to buttons.
     */
    private void initializeComponents() {
        setLayout(new FlowLayout()); // Set layout manager to FlowLayout.
        adminButton = new JButton("Admin Dashboard"); // Create admin button.
        adminButton.addActionListener(e -> openAdminDashboard()); // Add action listener to open admin dashboard.
        add(adminButton); // Add admin button to JFrame.

        customerButton = new JButton("Customer Dashboard"); // Create customer button.
        customerButton.addActionListener(e -> openCustomerDashboard()); // Add action listener to open customer dashboard.
        add(customerButton); // Add customer button to JFrame.
    }

    /**
     * Opens the AdminDashboard window when the admin button is clicked.
     */
    private void openAdminDashboard() {
        AdminDashboard adminDashboard = new AdminDashboard();
        adminDashboard.setVisible(true); // Set the admin dashboard to be visible.
    }

    /**
     * Opens the CustomerDashboard window when the customer button is clicked.
     */
    private void openCustomerDashboard() {
        CustomerDashboard customerDashboard = new CustomerDashboard();
        customerDashboard.setVisible(true); // Set the customer dashboard to be visible.
    }

    /**
     * Main method to run the GUI.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainGUI frame = new MainGUI();
            frame.setVisible(true);
        });
    }
}
