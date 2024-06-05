package mhds;


import java.util.ArrayList;
import java.util.List;

public class Order {
    private Customer customer;
    private List<Product> products;
    private double totalPrice;

    public Order(Customer customer) {
        this.customer = customer;
        this.products = new ArrayList<>();
        this.totalPrice = 0.0;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
        calculateTotalPrice();
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    private void calculateTotalPrice() {
        totalPrice = 0;
        for (Product product : products) {
            totalPrice += product.getPrice() * product.getQuantity();
        }
    }

    @Override
    public String toString() {
        return "Order{" +
                "customer=" + customer +
                ", products=" + products +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
