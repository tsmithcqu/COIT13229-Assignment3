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

    public DeliveryScheduleAdmin() {
        super("Delivery Schedule Management");
        this.client = new Client();
        createForm();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
    }

    private void createForm() {
        setLayout(new GridLayout(3, 2));

        add(new JLabel("Postcode:"));
        postcodeField = new JTextField(20);
        add(postcodeField);

        add(new JLabel("Cost:"));
        costField = new JTextField(20);
        add(costField);

        addButton = new JButton("Add Schedule");
        addButton.addActionListener(e -> submitScheduleData());
        add(addButton);

        viewButton = new JButton("View Schedules");
        viewButton.addActionListener(e -> viewSchedules());
        add(viewButton);
    }

    // to do: logic 
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DeliveryScheduleAdmin frame = new DeliveryScheduleAdmin();
            frame.setVisible(true);
        });
    }
}
