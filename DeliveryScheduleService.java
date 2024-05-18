package mhds;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * RESTful web service class for managing delivery schedules.
 */
@Path("deliveryService")
public class DeliveryScheduleService {
    private DatabaseAccess dbAccess;

    /**
     * Constructor that accepts a database access object for database operations.
     * @param dbAccess An instance of DatabaseAccess for database interactions.
     */
    public DeliveryScheduleService(DatabaseAccess dbAccess) {
        this.dbAccess = dbAccess;
    }

    /**
     * Endpoint to save delivery details into the database.
     * This endpoint expects a POST request with delivery details as application/json.
     * @param deliveryDetails Details of the delivery schedule to be saved, expected in JSON format.
     * @return Response indicating success or failure.
     */
    @POST
    @Path("/saveDeliveryDetails")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveDeliveryDetails(String deliveryDetails) {
        try {
            String sql = "INSERT INTO delivery_schedule (details) VALUES ('" + deliveryDetails + "')";
            dbAccess.executeUpdate(sql);
            return Response.ok("Delivery details saved successfully: " + deliveryDetails).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Failed to save delivery details: " + e.getMessage()).build();
        }
    }
}
