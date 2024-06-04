package mhds;

import javax.swing.*;
import java.awt.*;

/**
 * A GUI class extending JFrame for creating and handling both the customer registration and viewing functionalities.
 */
public class RegistrationScreen extends JFrame {
    private JTextField nameField, emailField, passwordField, addressField, phoneNumberField;
    private JButton addButton;
    private Client client;

    /**
     * Constructor initialises the GUI with necessary components and configurations.
     */
    public RegistrationScreen() {
        super("Register for MHDS");
        this.client = new Client(); // Create a new instance of Client for communication
        initializeComponents(); // Initialise all GUI components
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation to exit the application
        setSize(600, 600); // Adjust size to accommodate both form and table
        setLocationRelativeTo(null); // Center the frame on the screen
    }

    /**
     * Method to set up the form and table within the same frame using labels, text fields, buttons, and a table.
     */
    private void initializeComponents() {
        setLayout(new BorderLayout()); // Use BorderLayout for better arrangement of components

        // Panel for form inputs and buttons
        JPanel formPanel = new JPanel(new GridLayout(7, 2));
        
        // Adding customer name components
        formPanel.add(new JLabel("Name:"));
        nameField = new JTextField(20);
        formPanel.add(nameField);

        // Adding customer email components
        formPanel.add(new JLabel("Email:"));
        emailField = new JTextField(20);
        formPanel.add(emailField);

        // Adding customer password components
        formPanel.add(new JLabel("Password:"));
        passwordField = new JTextField(20);
        formPanel.add(passwordField);

        // Adding customer address components
        formPanel.add(new JLabel("Address:"));
        addressField = new JTextField(20);
        formPanel.add(addressField);

        // Adding customer phone number components
        formPanel.add(new JLabel("Phone Number:"));
        phoneNumberField = new JTextField(20);
        formPanel.add(phoneNumberField);

        // Button to Register
        addButton = new JButton("Register");
        addButton.addActionListener(e -> submitCustomerData()); // Set action listener to handle button click
        formPanel.add(addButton);

    }

    /**
     * Method to handle the button click event for submitting customer data.
     */
    private void submitCustomerData() {
        String name = nameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String address = addressField.getText();
        String phoneNumber = phoneNumberField.getText();

        Customer customer = new Customer(name, email, password, address, phoneNumber); // Create a new Customer object
        client.sendCustomerData(customer); // Use the client to send the customer data for registration
    }

    /**
     * Main method to run the GUI application, ensuring the frame is visible.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RegistrationScreen frame = new RegistrationScreen(); // Create an instance of the frame
            frame.setVisible(true); // Make the frame visible
        });
    }
}
