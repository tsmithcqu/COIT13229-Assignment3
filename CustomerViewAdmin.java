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
    private void initializeComponents() {
        setLayout(new GridLayout(6, 2, 5, 5)); // Set layout to grid with 6 rows, 2 columns, and padding.

        // Add name label and text field to the form
        add(new JLabel("Name:")); // Label for name.
        nameField = new JTextField(20); // Text field for entering name.
        add(nameField);

        // Add email label and text field to the form
        add(new JLabel("Email:")); // Label for email.
        emailField = new JTextField(20); // Text field for entering email.
        add(emailField);

        // Add password label and text field to the form
        add(new JLabel("Password:")); // Label for password.
        passwordField = new JTextField(20); // Text field for entering password.
        add(passwordField);

        // Add address label and text field to the form
        add(new JLabel("Address:")); // Label for address.
        addressField = new JTextField(20); // Text field for entering address.
        add(addressField);

        // Add phone number label and text field to the form
        add(new JLabel("Phone Number:")); // Label for phone number.
        phoneNumberField = new JTextField(20); // Text field for entering phone number.
        add(phoneNumberField);

        // Submit button for submitting the customer details.
        submitButton = new JButton("Submit"); // Button to submit the form.
        submitButton.addActionListener(e -> submitCustomerData()); // Set action listener to handle button click.
        add(submitButton);
    }

    /**
    * Gathers data from the form fields and sends it to the server.
    */
    private void submitCustomerData() {
        // Retrieve text from each text fields.
        String name = nameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String address = addressField.getText();
        String phoneNumber = phoneNumberField.getText();

        Customer customer = new Customer(name, email, password, address, phoneNumber); // Create a Customer object with the data
        new Client().sendCustomerData(customer); // Send customer data to the server using a Client instance.
    }
    
    /**
     * Main method to create and show the GUI.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CustomerViewAdmin frame = new CustomerViewAdmin(); // Create an instance of the GUI frame
            frame.setVisible(true); // Make the frame visible
        });
    }
}
