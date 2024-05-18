package mhds;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * RESTful web service class for managing order data.
 */
@Path("orderService")
public class OrderService {
    private DatabaseAccess dbAccess;

    /**
     * Constructor that accepts a database access object for database operations.
     * @param dbAccess An instance of DatabaseAccess for database interactions.
     */
    public OrderService(DatabaseAccess dbAccess) {
        this.dbAccess = dbAccess;
    }

    /**
     * Endpoint to create and save an order in the database.
     * This endpoint expects a POST request with order details as application/json.
     * @param orderDetails Details of the order to be saved, expected in JSON format.
     * @return Response indicating success or failure.
     */
    @POST
    @Path("/createOrder")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createOrder(String orderDetails) {
        try {
            String sql = "INSERT INTO orders (details) VALUES ('" + orderDetails + "')";
            dbAccess.executeUpdate(sql);
            return Response.ok("Order created and saved successfully: " + orderDetails).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Failed to create order: " + e.getMessage()).build();
        }
    }
}
