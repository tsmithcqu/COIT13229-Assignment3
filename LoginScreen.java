
/** 

Not currently in use.

import javax.swing.*;
import java.awt.*;


public class LoginScreen extends JFrame {

    public LoginScreen() {
        setTitle("Login Screen");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));

        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField();

        JLabel lblPassword = new JLabel("Password:");
        JPasswordField txtPassword = new JPasswordField();

        JButton btnLogin = new JButton("Login");

        panel.add(lblEmail);
        panel.add(txtEmail);
        panel.add(lblPassword);
        panel.add(txtPassword);
        panel.add(new JLabel());
        panel.add(btnLogin);

        btnLogin.addActionListener(e -> {
            String email = txtEmail.getText();
            String password = new String(txtPassword.getPassword());

            // Call to server to authenticate the user
            // new Client().login(email, password);
        });

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginScreen loginScreen = new LoginScreen();
            loginScreen.setVisible(true);
        });
    }
}

*/
