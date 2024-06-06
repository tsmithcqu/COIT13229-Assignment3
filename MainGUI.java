package mhds;

import javax.swing.*;
import java.awt.*;

/**
 * Main GUI for navigating between Admin and Customer Dashboards.
 */
public class MainGUI extends JFrame {
    private JButton adminButton, customerButton;

    public MainGUI() {
        super("Welcome to MHDS");
        initializeComponents();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
    }

    private void initializeComponents() {
        setLayout(new FlowLayout());
        adminButton = new JButton("Admin Dashboard");
        adminButton.addActionListener(e -> openAdminDashboard());
        add(adminButton);

        customerButton = new JButton("Customer Dashboard");
        customerButton.addActionListener(e -> openCustomerDashboard());
        add(customerButton);
    }

    private void openAdminDashboard() {
        AdminDashboard adminDashboard = new AdminDashboard();
        adminDashboard.setVisible(true);
    }

    private void openCustomerDashboard() {
        CustomerDashboard customerDashboard = new CustomerDashboard();
        customerDashboard.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainGUI frame = new MainGUI();
            frame.setVisible(true);
        });
    }
}
