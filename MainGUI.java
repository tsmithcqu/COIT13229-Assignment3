import javax.swing.*;
import java.awt.*;


public class MainGUI extends JFrame {

    public MainGUI() {
        setTitle("Welcome to the Maleny Dairy to Home Service (MHDS)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel headerLabel = new JLabel("Welcome to the Maleny Dairy to Home Service (MHDS)", JLabel.CENTER);
        headerLabel.setOpaque(true);
        headerLabel.setBackground(Color.LIGHT_GRAY);
        headerLabel.setPreferredSize(new Dimension(550, 40));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(headerLabel, gbc);

        JButton btnRegister = new JButton("<html>Register<br>(RegistrationScreen.java)</html>");
        JButton btnLogin = new JButton("<html>Login<br>(LoginScreen.java)</html>");
        JButton btnAdminLogin = new JButton("<html>Admin Login<br>(AdminLogin.java)</html>");

        btnRegister.setPreferredSize(new Dimension(250, 50));
        btnLogin.setPreferredSize(new Dimension(250, 50));
        btnAdminLogin.setPreferredSize(new Dimension(250, 50));

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(btnRegister, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(btnLogin, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        mainPanel.add(btnAdminLogin, gbc);

        btnRegister.addActionListener(e -> new RegistrationScreen().setVisible(true));
        btnLogin.addActionListener(e -> new LoginScreen().setVisible(true));
        btnAdminLogin.addActionListener(e -> new LoginScreen().setVisible(true)); // Admin login is also handled by LoginScreen

        add(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainGUI mainGUI = new MainGUI();
            mainGUI.setVisible(true);
        });
    }
}
