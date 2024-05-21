import javax.swing.*;
import java.awt.*;

public class CustomerDashboard extends JFrame {

    public CustomerDashboard() {
        setTitle("Maleny Dairy to Home Service");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel headerLabel = new JLabel("Maleny Dairy to Home Service", JLabel.CENTER);
        headerLabel.setOpaque(true);
        headerLabel.setBackground(Color.LIGHT_GRAY);
        headerLabel.setPreferredSize(new Dimension(550, 40));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        mainPanel.add(headerLabel, gbc);

        JButton btnViewProducts = new JButton("<html>View Products<br>(ProductView.java)</html>");
        JButton btnViewDeliverySchedule = new JButton("<html>View Delivery Schedule<br>(DeliveryScheduleView.java)</html>");
        JButton btnPlaceOrder = new JButton("<html>Place Order<br>(OrderScreen.java)</html>");

        btnViewProducts.setPreferredSize(new Dimension(250, 50));
        btnViewDeliverySchedule.setPreferredSize(new Dimension(250, 50));
        btnPlaceOrder.setPreferredSize(new Dimension(250, 50));

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(btnViewProducts, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(btnViewDeliverySchedule, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        mainPanel.add(btnPlaceOrder, gbc);

        btnViewProducts.addActionListener(e -> new ProductView().setVisible(true));
        btnViewDeliverySchedule.addActionListener(e -> new DeliveryScheduleView().setVisible(true));
        btnPlaceOrder.addActionListener(e -> new OrderScreen().setVisible(true));

        add(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CustomerDashboard customerDashboard = new CustomerDashboard();
            customerDashboard.setVisible(true);
        });
    }
}
