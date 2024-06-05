package mhds;

import javax.swing.*;
import java.awt.*;

/**
 * Customer dashboard providing access to registration and product viewing functionalities.
 */
public class CustomerDashboard extends JFrame {
    private JButton registrationButton, productViewButton;

    public CustomerDashboard() {
        super("Customer Dashboard");
        initializeComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
    }

    private void initializeComponents() {
        setLayout(new FlowLayout());
        registrationButton = new JButton("Register");
        registrationButton.addActionListener(e -> openRegistrationScreen());
        add(registrationButton);

        productViewButton = new JButton("View Products");
        productViewButton.addActionListener(e -> openProductView());
        add(productViewButton);
    }

    private void openRegistrationScreen() {
        RegistrationScreen registrationScreen = new RegistrationScreen();
        registrationScreen.setVisible(true);
    }

    private void openProductView() {
        ProductView productView = new ProductView();
        productView.setVisible(true);
    }
}
