import javax.swing.*;
import java.awt.*;


public class AdminDashboard extends JFrame {

    public AdminDashboard() {
        setTitle("Maleny Dairy to Home Service - Administrator Functionality");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel headerLabel = new JLabel("Maleny Dairy to Home Service - Administrator Functionality", JLabel.CENTER);
        headerLabel.setOpaque(true);
        headerLabel.setBackground(Color.LIGHT_GRAY);
        headerLabel.setPreferredSize(new Dimension(550, 40));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(headerLabel, gbc);

        JButton btnEnterOrRemoveProduct = new JButton("<html>Enter or remove product<br>(ProductViewAdmin.java)</html>");
        JButton btnCreateDeliverySchedule = new JButton("<html>Create Delivery Schedule<br>(CreateDeliveryScheduleAdmin.java)</html>");
        JButton btnViewCustomers = new JButton("<html>View Customers<br>(CustomerViewAdmin.java)</html>");
        JButton btnViewOrders = new JButton("<html>View Orders<br>(OrderViewAdmin.java)</html>");

        btnEnterOrRemoveProduct.setPreferredSize(new Dimension(250, 50));
        btnCreateDeliverySchedule.setPreferredSize(new Dimension(250, 50));
        btnViewCustomers.setPreferredSize(new Dimension(250, 50));
        btnViewOrders.setPreferredSize(new Dimension(250, 50));

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(btnEnterOrRemoveProduct, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(btnCreateDeliverySchedule, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(btnViewCustomers, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        mainPanel.add(btnViewOrders, gbc);

        btnEnterOrRemoveProduct.addActionListener(e -> new ProductViewAdmin().setVisible(true));
        btnCreateDeliverySchedule.addActionListener(e -> new CreateDeliveryScheduleAdmin().setVisible(true));
        btnViewCustomers.addActionListener(e -> new CustomerViewAdmin().setVisible(true));
        btnViewOrders.addActionListener(e -> new OrderViewAdmin().setVisible(true));

        add(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AdminDashboard adminDashboard = new AdminDashboard();
            adminDashboard.setVisible(true);
        });
    }
}
