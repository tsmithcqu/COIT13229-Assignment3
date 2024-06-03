package mhds;

import javax.swing.*;
import java.awt.*;

/**
 * Class for creating and displaying a GUI for registering new customers.
 */
public class CustomerViewAdmin extends JFrame {
    /**
     * Declare form components. 
     */
    private JTextField nameField, emailField, passwordField, addressField, phoneNumberField;
    private JButton submitButton;

    /**
     * Constructor to set up the GUI.
     * Very basic for the moment. This can be updated as needed. 
     */
    public CustomerViewAdmin() {
        super("Register New Customer for MHDS"); // Set the title of the JFrame window.
        initializeComponents(); // Call method to initialise GUI components.
        setSize(300, 220); // Set the initial size of the frame.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation to exit the application.
        setLocationRelativeTo(null); // Center the frame on the screen.
    }

     /**
     * Initialises the components and layouts of the GUI.
     */
    private void createForm() {
        setLayout(new GridLayout(6, 2));  // Set the layout of the JFrame to a grid layout with 6 rows and 2 columns

        // Add name label and text field to the form
        add(new JLabel("Name:"));
        nameField = new JTextField(20);
        add(nameField);

        // Add email label and text field to the form
        add(new JLabel("Email:"));
        emailField = new JTextField(20);
        add(emailField);

        // Add password label and text field to the form
        add(new JLabel("Password:"));
        passwordField = new JTextField(20);
        add(passwordField);

        // Add address label and text field to the form
        add(new JLabel("Address:"));
        addressField = new JTextField(20);
        add(addressField);

        // Add phone number label and text field to the form
        add(new JLabel("Phone Number:"));
        phoneNumberField = new JTextField(20);
        add(phoneNumberField);

        // Add button to submit the form data
        addButton = new JButton("Add Customer");
        addButton.addActionListener(e -> submitCustomerData()); // Set action listener to handle button click
        add(addButton);
    }

    // Method to handle the button click event for submitting customer data
    private void submitCustomerData() {
        // Retrieve text from each text field
        String name = nameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String address = addressField.getText();
        String phoneNumber = phoneNumberField.getText();

        // Create a new Customer object with the data from the form
        Customer customer = new Customer(name, email, password, address, phoneNumber);

        // Use the client to send the customer data for registration
        client.registerCustomer(customer);
    }
    
    // Main method to run the GUI
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CustomerViewAdmin frame = new CustomerViewAdmin(); // Create an instance of the GUI frame
            frame.setVisible(true); // Make the frame visible
        });
    }
}
