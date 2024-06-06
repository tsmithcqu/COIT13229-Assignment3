/**
 * 
 * Code throwing errors. Commenting out. 

package mhds;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class OrderScreen extends JFrame {

    private Client client;
    private DefaultListModel<Product> productListModel;
    private JList<Product> productList;
    private JButton addButton, placeOrderButton;
    private JTextField quantityField;
    private List<Product> products;
    private Order order;

     // Constructor initialises the GUI and fetches the product list.
    public OrderScreen(Client client, Customer customer) {
        super("Place Order for MHDS"); // Set the title of the JFrame window.
        this.client = client;
        this.order = new Order(customer);
        initializeComponents(); // Call the method to create the form components.
        fetchProducts(); // Fetch products from the server.
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Set default close operation to dispose the window.
        setSize(600, 400); // Set the initial size of the frame.
        setLocationRelativeTo(null); // Center the frame on the screen.
    }

     // Method to set up the form with labels, text fields, and buttons.
    private void initializeComponents() {
        setLayout(new BorderLayout());  // Set the layout of the JFrame to BorderLayout.

        // Create and add the product list.
        productListModel = new DefaultListModel<>();
        productList = new JList<>(productListModel);
        add(new JScrollPane(productList), BorderLayout.CENTER);

        // Create the panel for quantity input and buttons.
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));

        // Add quantity label and text field.
        panel.add(new JLabel("Quantity:"));
        quantityField = new JTextField();
        panel.add(quantityField);

        // Add button to add product to the order.
        addButton = new JButton("Add to Order");
        addButton.addActionListener(e -> addProductToOrder());
        panel.add(addButton);

        // Add button to place the order.
        placeOrderButton = new JButton("Place Order");
        placeOrderButton.addActionListener(e -> placeOrder());
        panel.add(placeOrderButton);

        add(panel, BorderLayout.SOUTH);
    }


     // Method to fetch products from the server and populate the product list.
    private void fetchProducts() {
        products = client.fetchAllProducts();
        if (products != null) {
            productListModel.clear();
            for (Product product : products) {
                productListModel.addElement(product);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Failed to fetch products", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


     // Method to add a selected product to the order.
    private void addProductToOrder() {
        Product selectedProduct = productList.getSelectedValue();
        if (selectedProduct == null) {
            JOptionPane.showMessageDialog(this, "Please select a product to add", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int quantity = Integer.parseInt(quantityField.getText());
            if (quantity <= 0) {
                throw new NumberFormatException();
            }

            selectedProduct.setQuantity(quantity);
            order.addProduct(selectedProduct);
            JOptionPane.showMessageDialog(this, "Product added to order: " + selectedProduct, "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid quantity", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to place the order by sending it to the server.

    private void placeOrder() {
        boolean success = client.sendOrder(order);

        if (success) {
            JOptionPane.showMessageDialog(this, "Order placed successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            productListModel.clear(); // Clear the order list after placing the order.
        } else {
            JOptionPane.showMessageDialog(this, "Failed to place order", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

*/
