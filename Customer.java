package mhds;

import java.io.Serializable;

/**
* The Customer class models the data for a customer and is designed to be passed between client and server.
*/
public class Customer implements Serializable {

    /**
    * Private fields to hold customer information.
    */
    private String name;        // Customer's name.
    private String email;       // Customer's email address.
    private String password;    // Customer's password. This is in plaintext - need to work out how to encrypt this. 
    private String address;     // Customer's physical address.
    private String phoneNumber; // Customer's phone number.

    /**
    * Constructor to initialise a new customer with all attributes.
    */
    public Customer(String name, String email, String password, String address, String phoneNumber) {
        this.name = name;               // Initialise the name field.
        this.email = email;             // Initialise the email field.
        this.password = password;       // Initialise the password field.
        this.address = address;         // Initialise the address field.
        this.phoneNumber = phoneNumber; // Initialise the phone number field.
    }

     /**
     * Getter methods for each of the customer's attributes.
     */
    public String getName() {
        return name; // Return the customer's name.
    }

    public String getEmail() {
        return email; // Return the customer's email.
    }

    public String getPassword() {
        return password; // Return the customer's password.
    }
    
    public String getPhoneNumber() {
        return phoneNumber; // Return the customer's phone number.
    }
}
