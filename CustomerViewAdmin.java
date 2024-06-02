package mhds;

import javax.swing.*;
import java.awt.*;

public class CustomerViewAdmin extends JFrame {
    private JTextField nameField, emailField, passwordField, addressField, phoneNumberField;
    private JButton addButton;
    private Client client;

    public CustomerViewAdmin() {
        super("Register New Customer");
        this.client = new Client();
        createForm();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLocationRelativeTo(null);
    }

    private void createForm() {
      // To do
    }

    private void submitCustomerData() {
      // To Do
    }
