package mdhs; 

/**
 * Product class to hold details about a product.
 */
public class Product {
    private String name; // Variable to store the name of the product.
    private String unit; // Variable to store the unit of measure for the product.
    private int quantity; // Variable to store the quantity of the product.
    private double price; // Variable to store the price of the product.
    private String ingredients; // Variable to store the ingredients of the product.

    // Constructor to initialise the product object with given values.
    public Product(String name, String unit, int quantity, double price, String ingredients) {
        this.name = name; // Assigns the name passed in the constructor to the name variable.
        this.unit = unit; // Assigns the unit passed in the constructor to the unit variable.
        this.quantity = quantity; // Assigns the quantity passed in the constructor to the quantity variable.
        this.price = price; // Assigns the price passed in the constructor to the price variable.
        this.ingredients = ingredients; // Assigns the ingredients passed in the constructor to the ingredients variable.
    }

    // Getters and Setters for each variable
    public String getName() { return name; } // Getter for the name.
    public void setName(String name) { this.name = name; } // Setter for the name.
    
    public String getUnit() { return unit; } // Getter for the unit.
    public void setUnit(String unit) { this.unit = unit; } // Setter for the unit.
    
    public int getQuantity() { return quantity; } // Getter for the quantity.
    public void setQuantity(int quantity) { this.quantity = quantity; } // Setter for the quantity.
    
    public double getPrice() { return price; } // Getter for the price.
    public void setPrice(double price) { this.price = price; } // Setter for the price.
    
    public String getIngredients() { return ingredients; } // Getter for the ingredients.
    public void setIngredients(String ingredients) { this.ingredients = ingredients; } // Setter for the ingredients.
}
