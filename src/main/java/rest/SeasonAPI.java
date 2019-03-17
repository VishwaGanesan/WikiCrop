package rest;

import src.Response;
import utils.CropUtil;
import utils.SeasonUtil;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("/season")
public class SeasonAPI {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSeasons() throws  Exception
    {
        List<Object> seasons = SeasonUtil.getSeasons();
        Response rs = new Response();
        rs.setCode(201);
        rs.setResponseObjs(seasons);
        return rs;
    }
    @GET
    @Path("{seasonID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSeasonByID(@PathParam("seasonID") Integer seasonID) throws Exception
    {
        Object season = SeasonUtil.getSeasonByID(seasonID);
        Response rs = new Response();
        rs.setResponseObj(season);
        return rs;
    }
}
