package mhds;

import java.io.Serializable;

/**
 * The DeliverySchedule class models the data for a customer and is designed to be passed between client and server.
 * Gen AI provided guidance on the usage of serialVersionUID to ensure class structure during serialiation and deserialisation. 
 * Gen AI did not develop any code. 
 */
public class DeliverySchedule implements Serializable {
    private static final long serialVersionUID = 1L; // UID for serialising, ensures class structure consistency during deserialisation.

    /**
     * Private fields to hold customer information.
     */
    private int scheduleID;     // Delivery Schedule ID. 
    private String postcode;    // Delivery postcode. 
    private double cost;        // Delivery cost. 

    /**
     * Constructor to initialise a new delivery with an ID.
     */
    public DeliverySchedule(int scheduleID, String postcode, double cost) {
        this.scheduleID = scheduleID;    // Initialise the scheduleID field.
        this.postcode = postcode;        // Initialise the postcode field.
        this.cost = cost;                // Initialise the cost field.
    }

    /**
     * Constructor to initialise a new delivery.
     */
    public DeliverySchedule(String postcode, double cost) {
        this.postcode = postcode;
        this.cost = cost;
    }

    public int getScheduleID() {
        return scheduleID; // Return the schedule ID.
    }

    public String getPostcode() {
        return postcode; // Return the post code. 
    }

    public double getCost() {
        return cost; // Return the cost. 
    }

    /**
     * Overridden toString method for printing and logging of delivery data.
     */
    @Override
    public String toString() {
        return "DeliverySchedule{" +
                "scheduleID=" + scheduleID +
                ", postcode='" + postcode + '\'' +
                ", cost=" + cost +
                '}';
    }

}
