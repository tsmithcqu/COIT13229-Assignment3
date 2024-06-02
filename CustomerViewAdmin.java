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
      // To do
    }

    private void submitCustomerData() {
      // To Do
    }
