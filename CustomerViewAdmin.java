package mhds;

import javax.swing.*;
import java.awt.*;

// A GUI class extending JFrame for creating and handling the customer registration form.
public class CustomerViewAdmin extends JFrame {
    // Declare components to be used in the form
    private JTextField nameField, emailField, passwordField, addressField, phoneNumberField;
    private JButton addButton;
    private Client client;

    // Constructor initialises the GUI. Basic while getting it working. 
    public CustomerViewAdmin() {
        super("Register New Customer"); // Set the title of the JFrame window.
        this.client = new Client(); // Create a new instance of Client for communication.
        createForm(); // Call the method to create the form components.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation to exit the application.
        setSize(300, 300); // Set the initial size of the frame.
        setLocationRelativeTo(null); // Center the frame on the screen.
    }

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

    private void submitCustomerData() {
      // To Do
    }
    
    // Main method to run the GUI
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CustomerViewAdmin frame = new CustomerViewAdmin(); // Create an instance of the GUI frame
            frame.setVisible(true); // Make the frame visible
        });
    }
}
