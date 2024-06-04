package mhds;

import javax.swing.*;
import java.awt.*;

/**
 * A GUI class extending JFrame for registering for the MHDS system. 
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
        setSize(300, 300); // Adjust size to accommodate both form and table
        setLocationRelativeTo(null); // Center the frame on the screen
    }

    /**
     * Method to set up the form and table within the same frame using labels, text fields, buttons, and a table.
     */
    private void initializeComponents() {
        setLayout(new GridLayout(6, 2)); // Use GridLayout for the arrangement of components

        // Adding customer name components
        add(new JLabel("Name:")); // Label for name.
        nameField = new JTextField(20); // Text field for entering name. 
        add(nameField);

        // Adding customer email components
        add(new JLabel("Email:")); // Label for email.
        emailField = new JTextField(20); // Text field for entering email. 
        add(emailField);

        // Adding customer password components
        add(new JLabel("Password:")); // Label for password.
        passwordField = new JTextField(20); // Text field for entering password. 
        add(passwordField);

        // Adding customer address components
        add(new JLabel("Address:")); // Label for address.
        addressField = new JTextField(20); // Text field for entering address. 
        add(addressField);

        // Adding customer phone number components
        add(new JLabel("Phone Number:")); // Label for phone number.
        phoneNumberField = new JTextField(20); // Text field for entering phone number. 
        add(phoneNumberField);

        // Button to submit customer data
        addButton = new JButton("Register"); // Button to submit customer information. 
        addButton.addActionListener(e -> submitCustomerData()); // Set action listener to handle button click
        add(addButton);
    }

    /**
     * Method to handle the button click event for submitting customer data.
     * Copied directly from CustomerViewAdmin.
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
