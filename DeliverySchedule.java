package mdhs;

public class DeliverySchedule {
    private String postcode;
    private String deliveryDay;
    private double deliveryCost;

    public DeliverySchedule(String postcode, String deliveryDay, double deliveryCost) {
        this.postcode = postcode;
        this.deliveryDay = deliveryDay;
        this.deliveryCost = deliveryCost;
    }

    // Getters and setters
    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getDeliveryDay() {
        return deliveryDay;
    }

    public void setDeliveryDay(String deliveryDay) {
        this.deliveryDay = deliveryDay;
    }

    public double getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(double deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    @Override
    public String toString() {
        return "DeliverySchedule{" +
                "postcode='" + postcode + '\'' +
                ", deliveryDay='" + deliveryDay + '\'' +
                ", deliveryCost=" + deliveryCost +
                '}';
    }
}
