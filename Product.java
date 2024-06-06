package mhds;

import java.io.Serializable;

/**
 * A class representing a product in the system.
 * Gen AI provided guidance on the usage of serialVersionUID to ensure class structure during serialiation and deserialisation. 
 * No code was developed by Gen AI. 
 */
public class Product implements Serializable {
    private static final long serialVersionUID = 1L; // UID for serialising, ensures class structure consistency during deserialisation.
    private int productId;         // Product ID.
    private String name;           // Product name. 
    private double price;          // Product price. 
    private String ingredients;    // Product ingredients. 
    private String unit;           // Product unit. 
    private int quantity;          // Product quantity. 

    /**
     * Constructor to initialise a new product.
     */
    public Product(String name, double price, String ingredients, String unit, int quantity) {
        this.name = name;                    // Initialise the name field. 
        this.price = price;                  // Initialise the price field. 
        this.ingredients = ingredients;      // Initialise the ingredients field. 
        this.unit = unit;                    // Initialise the unit field. 
        this.quantity = quantity;            // Initialise the quantity field. 
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
        return productId; // Return the product ID. 
    }

    public String getName() {
        return name;    // Return the name. 
    }

    public double getPrice() {
        return price;    // Return the price. 
    }

    public String getIngredients() {
        return ingredients;    // Return the ingredients. 
    }

    public String getUnit() {
        return unit;    // Return the unit. 
    }

    public int getQuantity() {
        return quantity;    // Return the quantity. 
    }
     public void setQuantity(int quantity) {
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
