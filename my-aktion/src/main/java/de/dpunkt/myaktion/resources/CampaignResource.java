package de.dpunkt.myaktion.resources;

import de.dpunkt.myaktion.model.Campaign;
import de.dpunkt.myaktion.services.CampaignService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;

@Path("/organizer/campaign")
public class CampaignResource {

    @Inject
    private CampaignService campaignService;

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCampaigns() {
        List<Campaign> allCampaigns = campaignService.getAllCampaigns();
        allCampaigns.forEach(campaign -> {
            campaign.setDonations(null);
            campaign.setOrganizer(null);
        });
        return Response
        		.status(200)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Access-Control-Max-Age", "1209600")
                .entity(allCampaigns)
                .build();
    }

    @DELETE
    @Path("/{campaignId}")
    public void deleteCampaign(@PathParam(value = "campaignId") Long campaignId) {
        campaignService.deleteCampaign(campaignId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    //@Produces(MediaType.APPLICATION_JSON)
    public Response addCampaign(Campaign campaign) {
        campaignService.addCampaign(campaign);
        return Response
        		.status(200)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Access-Control-Max-Age", "1209600")
                .build();
    }

    @PUT
    @Path("/{campaignId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCampaign(@PathParam(value = "campaignId") Long campaignId,
                                   Campaign newCampaign) {
        Campaign campaign = campaignService.getCampaign(campaignId);
        campaign.setName(newCampaign.getName());
        campaign.setDonationMinimum(newCampaign.getDonationMinimum());
        campaign.setTargetAmount(newCampaign.getTargetAmount());
        newCampaign = campaignService.updateCampaign(campaign);
        newCampaign.setDonations(null);
        newCampaign.setOrganizer(null);
        return Response
        		.status(200)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Access-Control-Max-Age", "1209600")
                .build();
    }

}

