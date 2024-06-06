package mhds;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * A GUI class extending JFrame for viewing and adding delivery schedules.
 */
public class DeliveryScheduleAdmin extends JFrame {
    private JTextField postcodeField, costField;
    private JButton addButton, viewButton;
    private Client client;

     /**
     * Constructor initialises the GUI.
     */
    public DeliveryScheduleAdmin() {
        super("Delivery Schedule Management");
        this.client = new Client(); // Create a new instance of Client for communication.
        createForm(); // Initialise all GUI components.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation to exit the application.
        setSize(300, 200); // Adjust size.
        setLocationRelativeTo(null); // Center the frame on the screen.
    }

    /**
     * Method to set up the form with a button to view delivery schedules.
     */
    private void createForm() {
        setLayout(new GridLayout(3, 2)); // Use GridLayout with 3 rows and 2 columns. 

        // Adding postcode components. 
        add(new JLabel("Postcode:")); // Label for postcode. 
        postcodeField = new JTextField(20); // Text field for postcode. 
        add(postcodeField);

        add(new JLabel("Cost:")); // Label for cost. 
        costField = new JTextField(20); // Text field for cost. 
        add(costField);

        // Button to add schedule. 
        addButton = new JButton("Add Schedule"); // Button to submit schedule information. 
        addButton.addActionListener(e -> submitScheduleData()); // Set action listener to handle button click
        add(addButton);

        // Button to view schedules. 
        viewButton = new JButton("View Schedules"); // Button to view schedule information. 
        viewButton.addActionListener(e -> viewSchedules()); // Set action listener to handle button click
        add(viewButton);
    }

    private void submitScheduleData() {
        String postcode = postcodeField.getText();
        double cost = Double.parseDouble(costField.getText());
        DeliverySchedule schedule = new DeliverySchedule(postcode, cost); // Use the new constructor
        client.sendScheduleData(schedule);
    }

    private void viewSchedules() {
        List<DeliverySchedule> schedules = client.fetchAllSchedules();
        if (schedules != null) {
            StringBuilder sb = new StringBuilder();
            for (DeliverySchedule schedule : schedules) {
                sb.append(schedule).append("\n");
            }
            JOptionPane.showMessageDialog(this, sb.toString(), "Registered Schedules", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Failed to fetch schedules", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Main method to run the GUI.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DeliveryScheduleAdmin frame = new DeliveryScheduleAdmin(); // Create an instance of the GUI frame.
            frame.setVisible(true); // Make the frame visible.
        });
    }
}
