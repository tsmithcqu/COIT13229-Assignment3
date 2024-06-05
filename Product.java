package mhds;

import java.io.Serializable;

/**
 * A class representing a product in the system.
 * Gen AI provided guidance on the usage of serialVersionUID to ensure class structure during serialiation and deserialisation. 
 * No code was developed by Gen AI. 
 */
public class Product implements Serializable {
    private static final long serialVersionUID = 1L; // UID for serialising, ensures class structure consistency during deserialisation.
    private int productId;
    private String name;
    private double price;
    private String ingredients;
    private String unit;
    private int quantity;

    /**
     * Constructor to initialise a new product.
     */
    public Product(String name, double price, String ingredients, String unit, int quantity) {
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
        this.unit = unit;
        this.quantity = quantity;
    }

    /**
     * Constructor to initialise a product with an ID.
     */
    public Product(int productId, String name, double price, String ingredients, String unit, int quantity) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
        this.unit = unit;
        this.quantity = quantity;
    }

    /**
     * Getters for product properties.
     */
    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getUnit() {
        return unit;
    }

    public int getQuantity() {
        return quantity;
    }

     /**
     * Overridden toString method for printing and logging of product data.
     */
    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", ingredients='" + ingredients + '\'' +
                ", unit='" + unit + '\'' +
                ", quantity=" + quantity +
                '}';
    }

}
