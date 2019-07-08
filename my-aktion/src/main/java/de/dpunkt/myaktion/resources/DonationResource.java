package de.dpunkt.myaktion.resources;

import de.dpunkt.myaktion.model.Account;
import de.dpunkt.myaktion.model.Donation;
import de.dpunkt.myaktion.model.Donation.Status;
import de.dpunkt.myaktion.services.DonationService;
import de.dpunkt.myaktion.services.exceptions.ObjectNotFoundException;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/")
public class DonationResource {

    @Inject
    private DonationService donationService;

    @GET
    @Path("/organizer/donation/list/{campaignId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDonationList(@PathParam(value = "campaignId") Long campaignId) {
        List<Donation> donations = donationService.getDonationList(campaignId);
        donations.forEach(donation -> donation.setCampaign(null));
        return Response
        		.status(200)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Access-Control-Max-Age", "1209600")
                .entity(donations)
                .build();
    }

    @POST
    @Path("/donation/{campaignId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addDonation(@PathParam(value = "campaignId") Long campaignId, Donation donation) {
              
        donation.setStatus(Status.IN_PROCESS);
        donationService.addDonation(campaignId, donation);
        return Response
        		.status(200)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Access-Control-Max-Age", "1209600")
                .build();
    }

    @GET
    @Path("/donation/list/{campaignId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDonationListPublic(@PathParam(value = "campaignId") Long campaignId) {
        List<Donation> donations;
        try {
            donations = donationService.getDonationListPublic(campaignId);
            return Response.ok(donations).build();
        } catch (ObjectNotFoundException e) {
            return Response
            		.status(javax.ws.rs.core.Response.Status.NOT_FOUND)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                    .header("Access-Control-Allow-Credentials", "true")
                    .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                    .header("Access-Control-Max-Age", "1209600")
                    
                    .build();
            		
        }
    }

}
