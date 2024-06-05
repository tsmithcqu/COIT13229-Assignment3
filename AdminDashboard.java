package mhds;

import javax.swing.*;
import java.awt.*;

/**
 * Admin dashboard providing access to customer and product management functionalities.
 */
public class AdminDashboard extends JFrame {
    private JButton customerViewButton, productViewButton;

    public AdminDashboard() {
        super("Admin Dashboard");
        initializeComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
    }

    private void initializeComponents() {
        setLayout(new FlowLayout());
        customerViewButton = new JButton("Customer Management");
        customerViewButton.addActionListener(e -> openCustomerViewAdmin());
        add(customerViewButton);

        productViewButton = new JButton("Product Management");
        productViewButton.addActionListener(e -> openProductViewAdmin());
        add(productViewButton);
    }

    private void openCustomerViewAdmin() {
        CustomerViewAdmin customerViewAdmin = new CustomerViewAdmin();
        customerViewAdmin.setVisible(true);
    }

    private void openProductViewAdmin() {
        ProductViewAdmin productViewAdmin = new ProductViewAdmin();
        productViewAdmin.setVisible(true);
    }
}
