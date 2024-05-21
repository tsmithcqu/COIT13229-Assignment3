import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderScreen extends JFrame {

    public OrderScreen() {
        setTitle("Order Screen");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));

        JLabel lblProductName = new JLabel("Product Name:");
        JTextField txtProductName = new JTextField();

        JLabel lblQuantity = new JLabel("Quantity:");
        JTextField txtQuantity = new JTextField();

        JButton btnAddProduct = new JButton("Add Product");
        JButton btnPlaceOrder = new JButton("Place Order");

        panel.add(lblProductName);
        panel.add(txtProductName);
        panel.add(lblQuantity);
        panel.add(txtQuantity);
        panel.add(btnAddProduct);
        panel.add(btnPlaceOrder);

        btnAddProduct.addActionListener(e -> {
            String productName = txtProductName.getText();
            int quantity = Integer.parseInt(txtQuantity.getText());
            // Add product to the order list
            // order.addProduct(new Product(productName, unit, quantity, price, ingredients));
        });

        btnPlaceOrder.addActionListener(e -> {
            // Place the order and send to server
            // client.sendOrder(order);
        });

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            OrderScreen orderScreen = new OrderScreen();
            orderScreen.setVisible(true);
        });
    }
}
