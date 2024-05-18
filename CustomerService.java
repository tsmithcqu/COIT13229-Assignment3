package mhds;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * RESTful web service class for managing customer data.
 */
@Path("customerService")
public class CustomerService {
    private DatabaseAccess dbAccess;

    /**
     * Constructor that accepts a database access object for database operations.
     * @param dbAccess An instance of DatabaseAccess for database interactions.
     */
    public CustomerService(DatabaseAccess dbAccess) {
        this.dbAccess = dbAccess;
    }

    /**
     * Endpoint to register a new customer and save their data in the database.
     * This endpoint expects a POST request with customer details as application/json.
     * @param customerDetails Details of the customer to register, expected in JSON format.
     * @return Response indicating success or failure.
     */
    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerCustomer(String customerDetails) {
        try {
            String sql = "INSERT INTO customers (details) VALUES ('" + customerDetails + "')";
            dbAccess.executeUpdate(sql);
            return Response.ok("Customer registered successfully: " + customerDetails).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Failed to register customer: " + e.getMessage()).build();
        }
    }

// TO IMPLEMENT: Customer login validation.  

}
