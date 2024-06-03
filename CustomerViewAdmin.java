package mhds;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * A GUI class extending JFrame for creating and handling both the customer registration and viewing functionalities.
 */
public class CustomerViewAdmin extends JFrame {
    /**
     * Declare form components. 
     */
    private JTextField nameField, emailField, passwordField, addressField, phoneNumberField;
    private JButton addButton, viewButton;
    private JTable customerTable;
    private JScrollPane scrollPane;
    private Client client;

    /**
     * Constructor to set up the GUI.
     * Very basic for the moment. This can be updated as needed. 
     */
    public CustomerViewAdmin() {
        super("Manage Customers for MHDS"); // Set the title of the JFrame window.
        this.client = new Client(); // Create a new instance of Client for communication
        initializeComponents(); // Call method to initialise GUI components.
        setSize(600, 600); // Set the initial size of the frame.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation to exit the application.
        setLocationRelativeTo(null); // Center the frame on the screen.
    }

    /**
     * Method to set up the form and table within the same frame using labels, text fields, buttons, and a table.
     */
    private void initializeComponents() {
        setLayout(new BorderLayout()); // Use BorderLayout for better arrangement of components

        // Panel for form inputs and buttons
        JPanel formPanel = new JPanel(new GridLayout(7, 2));
        
        // Components for adding customer name.
        formPanel.add(new JLabel("Name:")); // Label for name.
        nameField = new JTextField(20); // Text field for entering name. 
        formPanel.add(nameField);

        // Components for adding customer email.
        formPanel.add(new JLabel("Email:")); // Label for email.
        emailField = new JTextField(20); // Text field for entering email. 
        formPanel.add(emailField);

        // Components for adding customer password.
        formPanel.add(new JLabel("Password:")); // Label for password.
        passwordField = new JTextField(20); // Text field for entering password. 
        formPanel.add(passwordField);

        // Components for adding customer address.
        formPanel.add(new JLabel("Address:")); // Label for address.
        addressField = new JTextField(20); // Text field for entering address. 
        formPanel.add(addressField);

        // Components for adding customer phone number.
        formPanel.add(new JLabel("Phone Number:")); // Label for phone number.
        phoneNumberField = new JTextField(20); // Text field for entering phone number. 
        formPanel.add(phoneNumberField);

        // Button to submit customer data. 
        addButton = new JButton("Add Customer"); // Button to submit customer information. 
        addButton.addActionListener(e -> submitCustomerData()); // Set action listener to handle button click
        formPanel.add(addButton);

        // Button to view customer data in a table. 
        viewButton = new JButton("View Customers"); // Button to view current customers. 
        viewButton.addActionListener(e -> loadCustomerData()); // Set action listener to handle button click
        formPanel.add(viewButton);

        // Adding the form panel to the north section of the BorderLayout.
        add(formPanel, BorderLayout.NORTH);

        // Table setup for displaying customer data.
        customerTable = new JTable();
        scrollPane = new JScrollPane(customerTable); // Scroll pane to contain the table.
        add(scrollPane, BorderLayout.CENTER); // Add the scroll pane to the center of the frame.
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
     * Method to load customer data from the server and populate the table.
     */
    private void loadCustomerData() {
        java.util.List<Customer> customers = client.fetchAllCustomers(); // Fetch all customers.
        String[] columnNames = {"Name", "Email", "Password", "Address", "Phone Number"}; // Define column names for the table.
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0); // Create a new table model.

        for (Customer customer : customers) {
            Object[] rowData = {
                customer.getName(),
                customer.getEmail(),
                customer.getPassword(),
                customer.getAddress(),
                customer.getPhoneNumber()
            };
            tableModel.addRow(rowData); // Add row data to the table.
        }
        customerTable.setModel(tableModel); // Set the table model for displaying data.
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
