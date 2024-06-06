package mhds;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an order made by a customer.
 */
public class Order {
    private Customer customer;
    private List<Product> products;
    private double totalPrice;

    /**
     * Constructs an Order with a specific customer.
     */
    public Order(Customer customer) {
        this.customer = customer;
        this.products = new ArrayList<>();
        this.totalPrice = 0.0;
    }

    // Returns the customer. 
    public Customer getCustomer() {
        return customer;
    }

    // Sets the customer. 
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    // Returns the product. 
    public List<Product> getProducts() {
        return products;
    }

    // Gets the product. 
    public void addProduct(Product product) {
        this.products.add(product);
        calculateTotalPrice();
    }

    // Returns the total price. 
    public double getTotalPrice() {
        return totalPrice;
    }

    // Calculates the total price. 
    private void calculateTotalPrice() {
        totalPrice = 0;
        for (Product product : products) {
            totalPrice += product.getPrice() * product.getQuantity();
        }
    }

    /**
     * Overridden toString method for printing and logging of order data.
     */
    @Override
    public String toString() {
        return "Order{" +
                "customer=" + customer +
                ", products=" + products +
                ", totalPrice=" + totalPrice +
                '}';
    }
}

