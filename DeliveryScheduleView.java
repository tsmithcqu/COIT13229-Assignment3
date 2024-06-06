package mhds;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * A GUI class extending JFrame for viewing registered delivery schedules.
 */
public class DeliveryScheduleView extends JFrame {
    private JButton viewButton;
    private Client client;

    /**
     * Constructor initialises the GUI.
     */
    public DeliveryScheduleView() {
        super("View Delivery Schedules"); // Set the title of the JFrame window
        this.client = new Client(); // Create a new instance of Client for communication
        initializeComponents(); // Call the method to set up the GUI components
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation to exit the application
        setSize(400, 400); // Set the initial size of the frame
        setLocationRelativeTo(null); // Center the frame on the screen
    }

    /**
     * Method to set up the form with a button to view delivery schedules.
     */
    private void initializeComponents() {
        setLayout(new FlowLayout());  // Use FlowLayout for simple arrangement of components

        // Button to view the registered delivery schedules
        viewButton = new JButton("View Delivery Schedules");
        viewButton.addActionListener(e -> viewSchedules()); // Set action listener to handle button click
        add(viewButton);
    }

    /**
     * Method to handle the button click event for viewing registered delivery schedules.
     */
    private void viewSchedules() {
        // Fetch all delivery schedules using the client
        List<DeliverySchedule> schedules = client.fetchAllSchedules();
        if (schedules != null) {
            // Display the fetched delivery schedules in a message dialog
            StringBuilder sb = new StringBuilder("Registered Delivery Schedules:\n");
            for (DeliverySchedule schedule : schedules) {
                sb.append("Schedule ID: ").append(schedule.getScheduleID())
                  .append(", Postcode: ").append(schedule.getPostcode())
                  .append(", Cost: ").append(schedule.getCost())
                  .append("\n");
            }
            JOptionPane.showMessageDialog(this, sb.toString(), "Delivery Schedule List", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Failed to fetch delivery schedules", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Main method to run the GUI.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DeliveryScheduleView frame = new DeliveryScheduleView(); // Create an instance of the GUI frame
            frame.setVisible(true); // Make the frame visible
        });
    }
}
