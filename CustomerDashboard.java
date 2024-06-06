package mhds;

import javax.swing.*;
import java.awt.*;

/**
 * Customer dashboard providing access to registration and product viewing functionalities.
 */
public class CustomerDashboard extends JFrame {
    // Declare buttons for different functionalities
    private JButton registrationButton, productViewButton, viewSchedulesButton;

    public CustomerDashboard() {
        super("Customer Dashboard"); // Set the title of the JFrame.
        initializeComponents(); // Call method to initialise GUI components.
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Set the default close operation to dispose the frame on close.
        setSize(300, 200); // Set the size of the frame.
        setLocationRelativeTo(null); // Center the frame on the screen.
    }

    /**
     * Method to initialise the GUI components.
     */
    private void initializeComponents() {
        setLayout(new FlowLayout()); // Set the layout manager to FlowLayout.

        // Initialise and add registration button.
        registrationButton = new JButton("Register");
        registrationButton.addActionListener(e -> openRegistrationScreen()); // Add action listener to open registration screen.
        add(registrationButton); // Add button to the frame.

        // Initialise and add product view button.
        productViewButton = new JButton("View Products"); 
        productViewButton.addActionListener(e -> openProductView()); // Add action listener to open product view.
        add(productViewButton); // Add button to the frame.

        // Initialise and add view schedules button.
        viewSchedulesButton = new JButton("View Delivery Schedules");
        viewSchedulesButton.addActionListener(e -> openDeliveryScheduleView()); // Add action listener to open delivery schedule view
        add(viewSchedulesButton); // Add button to the frame
    }

    /**
     * Method to open the registration screen
     */
    private void openRegistrationScreen() {
        RegistrationScreen registrationScreen = new RegistrationScreen();
        registrationScreen.setVisible(true);
    }

    /**
     * Method to open the product view screen
     */
    private void openProductView() {
        ProductView productView = new ProductView();
        productView.setVisible(true);
    }

    /**
     * Method to open the delivery schedule view screen
     */
    private void openDeliveryScheduleView() {
        DeliveryScheduleView deliveryScheduleView = new DeliveryScheduleView(); // Create an instance of DeliveryScheduleView
        deliveryScheduleView.setVisible(true); // Make the delivery schedule view screen visible
    }

    /**
     * Main method to run the GUI
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> { // Ensure the GUI creation happens on the Event Dispatch Thread
            CustomerDashboard frame = new CustomerDashboard(); // Create an instance of CustomerDashboard
            frame.setVisible(true); // Make the customer dashboard visible
        });
    }
    
}
