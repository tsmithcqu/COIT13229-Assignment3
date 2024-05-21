import javax.swing.*;
import java.awt.*;


public class RegistrationScreen extends JFrame {

    public RegistrationScreen() {
        setTitle("Registration Screen");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));

        JLabel lblFullName = new JLabel("Full Name:");
        JTextField txtFullName = new JTextField();

        JLabel lblPhoneNumber = new JLabel("Phone Number:");
        JTextField txtPhoneNumber = new JTextField();

        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField();

        JLabel lblPassword = new JLabel("Password:");
        JPasswordField txtPassword = new JPasswordField();

        JLabel lblAddress = new JLabel("Delivery Address:");
        JTextField txtAddress = new JTextField();

        JButton btnRegister = new JButton("Register");

        panel.add(lblFullName);
        panel.add(txtFullName);
        panel.add(lblPhoneNumber);
        panel.add(txtPhoneNumber);
        panel.add(lblEmail);
        panel.add(txtEmail);
        panel.add(lblPassword);
        panel.add(txtPassword);
        panel.add(lblAddress);
        panel.add(txtAddress);
        panel.add(new JLabel());
        panel.add(btnRegister);

        btnRegister.addActionListener(e -> {
            String fullName = txtFullName.getText();
            String phoneNumber = txtPhoneNumber.getText();
            String email = txtEmail.getText();
            String password = new String(txtPassword.getPassword());
            String address = txtAddress.getText();

            // Call to server to register the new customer
            // new Client().registerCustomer(new Customer(fullName, phoneNumber, email, password, address));
        });

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RegistrationScreen registrationScreen = new RegistrationScreen();
            registrationScreen.setVisible(true);
        });
    }
}
