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
    private int scheduleID;
    private String postcode;
    private double cost;

    /**
     * Constructor to initialise a new delivery with an ID.
     */
    public DeliverySchedule(int scheduleID, String postcode, double cost) {
        this.scheduleID = scheduleID;
        this.postcode = postcode;
        this.cost = cost;
    }

    /**
     * Constructor to initialise a new delivery.
     */
    public DeliverySchedule(String postcode, double cost) {
        this.postcode = postcode;
        this.cost = cost;
    }

    public int getScheduleID() {
        return scheduleID;
    }

    public String getPostcode() {
        return postcode;
    }

    public double getCost() {
        return cost;
    }

}
